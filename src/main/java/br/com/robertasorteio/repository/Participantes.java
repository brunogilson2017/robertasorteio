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
	
	public Participante porId(int id) {
		return manager.find(Participante.class, id);
	}
	
	public List<Participante> todos() {
		List<Participante> participantes = manager.createQuery("FROM Participante", Participante.class).getResultList();
		return participantes;
	}

}
