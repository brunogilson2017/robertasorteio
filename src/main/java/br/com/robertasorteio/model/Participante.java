package br.com.robertasorteio.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "participante")
public class Participante implements Serializable {

	private static final long serialVersionUID = -6913684685104067530L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 150, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	private String logradouro;
	
	@Column(length = 20)
	private String numero;
	
	private String complemento;
	
	@Column(length = 100)
	private String bairro;
	
	@Column(length = 100)
	private String cidade;
	
	@Column(length = 5)
	private String uf;
	
	@Column(length = 15)
	private String cep;
	
	@Column(length = 50)
	private String pais;
	
	@Column(nullable = false)
	private boolean participandoSorteio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isParticipandoSorteio() {
		return participandoSorteio;
	}
	public void setParticipandoSorteio(boolean participandoSorteio) {
		this.participandoSorteio = participandoSorteio;
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
		Participante other = (Participante) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
