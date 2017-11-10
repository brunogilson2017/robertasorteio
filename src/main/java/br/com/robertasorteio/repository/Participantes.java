package br.com.robertasorteio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.util.jpa.Transactional;

public class Participantes implements Serializable {

	private static final long serialVersionUID = 7000719596042216797L;

	@Inject
	EntityManager manager;

	@Transactional
	public void inserir(Participante participante) {
		manager.persist(participante);
	}
	
	@Transactional
	public void atualizar(Participante participante) {
		manager.merge(participante);
	}
	
	public Participante porId(int id) {
		return manager.find(Participante.class, id);
	}	
	
	public List<Participante> todos() {
		List<Participante> participantes = manager.createQuery("FROM Participante", Participante.class).getResultList();
		return participantes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Participante> listarSemGanhador() {
		List<Participante> lista = null;
		lista = manager.createNativeQuery("SELECT p.* FROM participante p LEFT JOIN sorteio s"
				+ " ON p.id = s.idParticipante WHERE s.id is null and p.participandoSorteio = true", Participante.class).getResultList();
		
		return lista;
	}
	
	public Participante porEmail(String email) {
		Participante participante = null;

		try {
			participante = manager.createQuery("FROM Participante WHERE LOWER(email) = :email", Participante.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum participante encontrado com o e-mail informado.
		}

		return participante;
	}
	
}
