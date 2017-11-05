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
		ganhador = gerarGanhador();
		try {
			//delay para a tela de loading
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if((ganhador.getId() != 0) && validarGanhador(ganhador)){
			salvarSorteio(ganhador);
		}else{
			ganhador = new Participante();
		}
	}
	
	public Participante gerarGanhador(){
		List<Participante> listParticipante = participantes.listarSemGanhador();
		int tamanho = listParticipante.size();
		if(tamanho > 0){
			Random random = new Random();
			int Sorteado = random.nextInt(tamanho);
			ganhador = listParticipante.get(Sorteado);
//			System.out.println(ganhador.getNome());
		}else{
			System.out.println("ERRO : Nenhum participante disponível!");
		}
		return ganhador;
	}
	
	public boolean validarGanhador(Participante ganhador){
		return (dao.porGanhador(ganhador)).isEmpty();
	}
	
	public void salvarSorteio(Participante ganhador){
		sorteio = new Sorteio();
		sorteio.setData(LocalDateTime.now());
		sorteio.setGanhador(ganhador);
		sorteio.setPremio("Celular");
		dao.inserir(sorteio);
		System.out.println("salvo");
	}
	
	public Participante getGanhador() {
		return ganhador;
	}
	public void setGanhador(Participante ganhador) {
		this.ganhador = ganhador;
	}
	
}
