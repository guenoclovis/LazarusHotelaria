package br.org.ufpr.tcc.entity;

import java.util.Date;

public class Filial {

	private Integer codFilial;
	private String nome;
	private String descricao;
	private String exibirSite;
	private char status;
	
	
	public Filial() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Filial(Integer codFilial, String nome, String descricao, String exibirSite, char status) {
		super();
		this.codFilial = codFilial;
		this.nome = nome;
		this.descricao = descricao;
		this.exibirSite = exibirSite;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Filial [codFilial=" + codFilial + ", nome=" + nome + ", descricao=" + descricao + ", exibirSite="
				+ exibirSite + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFilial == null) ? 0 : codFilial.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Filial other = (Filial) obj;
		if (codFilial == null) {
			if (other.codFilial != null)
				return false;
		} else if (!codFilial.equals(other.codFilial))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
	public Integer getCodFilial() {
		return codFilial;
	}
	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
