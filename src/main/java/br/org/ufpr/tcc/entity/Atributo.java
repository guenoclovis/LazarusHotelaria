package br.org.ufpr.tcc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

//import java.util.Date;
@Entity
@Table(name = "atributos", schema="public")

public class Atributo {

public static final String NOME = "nome";
	
	@Id
    @Column(name = "cod_atributo")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATRIBUTO")
    @SequenceGenerator(name = "SEQ_ATRIBUTO", schema="public", sequenceName = "atributos_cod_atributo_seq", allocationSize = 1)
	private Integer codAtributo;
	
	@Column(name="tipo")
	private Integer tipo;
	
	@Size(min=3, max=200)
	@NotNull
	@NotEmpty
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="status")
	private Integer status;

	public Integer getCodAtributo() {
		return codAtributo;
	}

	public void setCodAtributo(Integer codAtributo) {
		this.codAtributo = codAtributo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAtributo == null) ? 0 : codAtributo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Atributo other = (Atributo) obj;
		if (codAtributo == null) {
			if (other.codAtributo != null)
				return false;
		} else if (!codAtributo.equals(other.codAtributo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atributo [codAtributo=" + codAtributo + ", tipo=" + tipo + ", nome=" + nome + ", descricao=" + descricao
				+ ", status=" + status + "]";
	}

}
