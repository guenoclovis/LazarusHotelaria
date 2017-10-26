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

@Entity
@Table(name = "filiais", schema="public")
public class Filial {

	public static final String NOME = "nome";
	
	@Id
    @Column(name = "cod_filial")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", schema="public", sequenceName = "filiais_cod_filial_seq", allocationSize = 1)
	private Integer codFilial; 
	
	@Size(min=3, max=200)
	@NotNull
	@NotEmpty
	@Column(name="nome")	
	private String nome;
	
	@Column(name="email")
	private String email;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="exibir_site")
	private String exibirSite;
	
	@Column(name="status")
	private char status;
	
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


	@Override
	public String toString() {
		return "Filial [codFilial=" + codFilial + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao
				+ ", exibirSite=" + exibirSite + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFilial == null) ? 0 : codFilial.hashCode());
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

}
