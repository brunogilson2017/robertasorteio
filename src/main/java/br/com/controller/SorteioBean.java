package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.robertasorteio.model.ParticipanteDAO;
import br.com.robertasorteio.model.PO.Participante;

@ManagedBean
@ViewScoped
public class SorteioBean implements Serializable{

	private static final long serialVersionUID = -1820026883470099394L;
	@Inject
	ParticipanteDAO participanteDAO;
	@Inject
	private Participante ganhador;
	
	public SorteioBean() {
		
	}
	
	public void sortear(){
		ArrayList<Participante> listParticipante = 
				(ArrayList<Participante>) participanteDAO.listar(); 
		int tamanho = listParticipante.size();
		Random random = new Random();
		int Sorteado = random.nextInt(tamanho);
		ganhador = listParticipante.get(Sorteado);
		System.out.println(ganhador.getNome());
	}
	
	/*
	 * Getters and Setters
	 */

	public ParticipanteDAO getParticipanteDAO() {
		return participanteDAO;
	}

	public void setParticipanteDAO(ParticipanteDAO participanteDAO) {
		this.participanteDAO = participanteDAO;
	}

	public Participante getGanhador() {
		return ganhador;
	}

	public void setGanhador(Participante ganhador) {
		this.ganhador = ganhador;
	}
}
