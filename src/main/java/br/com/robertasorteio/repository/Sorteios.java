package br.com.robertasorteio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.model.Sorteio;

public class Sorteios implements Serializable {
	
	private static final long serialVersionUID = -6866696652763822928L;
	
	@Inject
	EntityManager manager;
	
	public void inserir(Sorteio sorteio ) {
	  manager.persist(sorteio);
	}
	 
	public Sorteio porId(int id) {
		return manager.find(Sorteio.class, id);
	}
	
	public List<Sorteio> porGanhador(Participante participante){
		return manager.createQuery("FROM Sorteio WHERE ganhador = :participante", Sorteio.class).
				setParameter("participante", participante).getResultList();
	}
}
