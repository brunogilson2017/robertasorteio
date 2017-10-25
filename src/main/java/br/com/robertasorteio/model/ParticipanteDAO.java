package br.com.robertasorteio.model;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.robertasorteio.model.PO.Participante;

public class ParticipanteDAO {
	
	@Inject
	EntityManager em;

	public List<Participante> listar(){
		
		List<Participante> lista = null;
		lista = em.createQuery("FROM participante", Participante.class).getResultList();
		return lista;
	}
	
	public void cadastrar(List<Participante> lista){
		for (Participante participante : lista) {
			em.persist(participante);
		}
	}
	
	public void cadastrar(Participante participante){
			em.persist(participante);
	}

}
