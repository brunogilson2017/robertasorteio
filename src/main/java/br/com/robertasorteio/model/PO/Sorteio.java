package br.com.robertasorteio.model.PO;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sorteio")
public class Sorteio implements Serializable {

	private static final long serialVersionUID = -1319683823195521880L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	
	private LocalDateTime data;
	
	@JoinColumn(name="idParticipante")
	@ManyToOne
	private Participante ganhador;
	
	private String premio;

	
	/*
	 * Getters and Setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Participante getGanhador() {
		return ganhador;
	}

	public void setGanhador(Participante ganhador) {
		this.ganhador = ganhador;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}
	

	
}
