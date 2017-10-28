package br.com.robertasorteio.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "sorteio")
public class Sorteio implements Serializable {

	private static final long serialVersionUID = -1319683823195521880L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;

	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(name = "idParticipante")
	private Participante ganhador;

	@Column(length = 150)
	private String premio;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Sorteio other = (Sorteio) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
