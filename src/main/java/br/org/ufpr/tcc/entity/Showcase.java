package br.org.ufpr.tcc.entity;

import java.util.Date;

public class Showcase {

	private Integer codShowcase;
	private String nome;
	private String email;
	private String descricao;
	private String exibirSite;
	private char status;
	
	@Override
	public String toString() {
		return "Showcase [codShowcase=" + codShowcase + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao
				+ ", exibirSite=" + exibirSite + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codShowcase == null) ? 0 : codShowcase.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((exibirSite == null) ? 0 : exibirSite.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showcase other = (Showcase) obj;
		if (codShowcase == null) {
			if (other.codShowcase != null)
				return false;
		} else if (!codShowcase.equals(other.codShowcase))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (exibirSite == null) {
			if (other.exibirSite != null)
				return false;
		} else if (!exibirSite.equals(other.exibirSite))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	public Integer getCodShowcase() {
		return codShowcase;
	}
	public void setCodShowcase(Integer codShowcase) {
		this.codShowcase = codShowcase;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getExibirSite() {
		return exibirSite;
	}
	public void setExibirSite(String exibirSite) {
		this.exibirSite = exibirSite;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	}
