package br.com.robertasorteio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
	
<<<<<<< HEAD
	public Participante porId(int id) {
		return manager.find(Participante.class, id);
=======
	@SuppressWarnings("unchecked")
	public List<Participante> listarSemGanhador() {
		List<Participante> lista = null;
		lista = manager.createNativeQuery("SELECT p.* FROM Participante p LEFT JOIN Sorteio s"
				+ " ON p.id = s.idParticipante WHERE s.id is null", Participante.class).getResultList();
		
		return lista;
	}
	
	public void cadastrar(List<Participante> lista) {
		for (Participante participante : lista) {
			manager.persist(participante);
		}
>>>>>>> 7323421f7ffd370e9df50b3b7b1dfe7c50b89810
	}
	
	public List<Participante> todos() {
		List<Participante> participantes = manager.createQuery("FROM Participante", Participante.class).getResultList();
		return participantes;
	}

}
