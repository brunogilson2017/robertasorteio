package br.com.robertasorteio.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
