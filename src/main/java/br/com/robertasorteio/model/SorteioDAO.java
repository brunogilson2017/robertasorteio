package br.com.robertasorteio.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.robertasorteio.model.PO.Sorteio;

public class SorteioDAO {
	
	@Inject
	EntityManager em;
	
	public void inserir(Sorteio sorteio ){
		em.persist(sorteio);
	}
	 
	public Sorteio porId(int id){
		return em.find(Sorteio.class, id);
	}
}
