package br.org.ufpr.tcc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "filiais", schema="public")
public class Filial {

	public static final String NOME = "nome";
	public static final String ID = "codFilial";
	
	@Id
    @Column(name = "cod_filial")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FILIAL")
    @SequenceGenerator(name = "SEQ_FILIAL", schema="public", sequenceName = "filiais_cod_filial_seq", allocationSize = 1)
	private Integer codFilial; 
	
	@Size(min=3, max=200)
	@NotNull
	@NotEmpty
	@Column(name="nome")	
	private String nome;
	
	@NotNull
	@Column(name="email")
	private String email;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="exibir_site")
	private Integer exibirSite;
	
	@Column(name="status")
	private Integer status;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH })
	@JoinColumn(name = "COD_FOTO", nullable = true)
	@Fetch(FetchMode.SELECT)
	private Foto foto;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FILIAL_ATRIBUTO", joinColumns = @JoinColumn(name = "COD_FILIAL"),
    inverseJoinColumns = @JoinColumn(name = "COD_ATRIBUTO"))
	private List<Atributo> atributos = new ArrayList();

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

	public Integer getExibirSite() {
		return exibirSite;
	}

	public void setExibirSite(Integer exibirSite) {
		this.exibirSite = exibirSite;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atributos == null) ? 0 : atributos.hashCode());
		result = prime * result + ((codFilial == null) ? 0 : codFilial.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((exibirSite == null) ? 0 : exibirSite.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (atributos == null) {
			if (other.atributos != null)
				return false;
		} else if (!atributos.equals(other.atributos))
			return false;
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
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
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
		return true;
	}

	@Override
	public String toString() {
		return "Filial [codFilial=" + codFilial + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao
				+ ", exibirSite=" + exibirSite + ", status=" + status + ", foto=" + foto + ", atributos=" + atributos
				+ "]";
	}
	
}
