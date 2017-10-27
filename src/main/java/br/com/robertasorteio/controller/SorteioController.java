package br.com.robertasorteio.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.model.Sorteio;
import br.com.robertasorteio.repository.Participantes;
import br.com.robertasorteio.repository.Sorteios;
import br.com.robertasorteio.util.jpa.Transactional;

@Named
@ViewScoped
public class SorteioController implements Serializable { 

	private static final long serialVersionUID = -1820026883470099394L;
	
	@Inject
	private Participantes participantes;
	@Inject
	private Participante ganhador;
	@Inject
	private Sorteio sorteio;
	@Inject
	private Sorteios dao;
	
	@Transactional
	public void sortear() {
		List<Participante> listParticipante = participantes.listar();
		int tamanho = listParticipante.size();
		Random random = new Random();
		int Sorteado = random.nextInt(tamanho);
		ganhador = listParticipante.get(Sorteado);
		System.out.println(ganhador.getNome());
		
		sorteio = new Sorteio();
		sorteio.setData(LocalDateTime.now());
		sorteio.setGanhador(ganhador);
		sorteio.setPremio("Celular");
		
		dao.inserir(sorteio);
	
	}
	
	public Participante getGanhador() {
		return ganhador;
	}
	public void setGanhador(Participante ganhador) {
		this.ganhador = ganhador;
	}
	
}
