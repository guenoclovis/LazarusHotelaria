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
@Table(name = "quartos", schema = "public")

public class Quarto {

	public static final String ID = "codQuarto";
	public static final String NUMERO_QUARTO = "idQuarto";
	
	@Id
	@Column(name = "cod_quarto")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_QUARTO")
	@SequenceGenerator(name = "SEQ_QUARTO", schema = "public", sequenceName = "quartos_cod_quarto_seq", allocationSize = 1)
	private Integer codQuarto;

	@NotNull
	@Column(name = "cod_filial")
	private Integer codFilial;
	
	@NotNull
	@Column(name = "id_quarto")
	private Integer idQuarto;

	@NotNull
	@Column(name = "cod_tipo_quarto")
	private Integer codTipoQuarto;

	@Column(name = "nr_camas")
	private Integer nrCamas;

	@Size(max=400)
	@Column(name = "descricao")
    private String descricao;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH })
	@JoinColumn(name = "COD_FOTO", nullable = true)
	@Fetch(FetchMode.SELECT)
	private Foto foto;

	@Column(name = "status")
    private char status;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "QUARTO_ATRIBUTO", joinColumns = @JoinColumn(name = "COD_QUARTO"),
    inverseJoinColumns = @JoinColumn(name = "COD_ATRIBUTO"))
	private List<Atributo> atributos = new ArrayList();

	
	public Integer getCodFilial() {
		return codFilial;
	}

	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Integer getCodQuarto() {
		return codQuarto;
	}

	public void setCodQuarto(Integer codQuarto) {
		this.codQuarto = codQuarto;
	}

	public Integer getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(Integer idQuarto) {
		this.idQuarto = idQuarto;
	}

	public Integer getCodTipoQuarto() {
		return codTipoQuarto;
	}

	public void setCodTipoQuarto(Integer codTipoQuarto) {
		this.codTipoQuarto = codTipoQuarto;
	}

	public Integer getNrCamas() {
		return nrCamas;
	}

	public void setNrCamas(Integer nrCamas) {
		this.nrCamas = nrCamas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
		result = prime * result + ((codQuarto == null) ? 0 : codQuarto.hashCode());
		result = prime * result + ((codTipoQuarto == null) ? 0 : codTipoQuarto.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((idQuarto == null) ? 0 : idQuarto.hashCode());
		result = prime * result + ((nrCamas == null) ? 0 : nrCamas.hashCode());
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
		Quarto other = (Quarto) obj;
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
		if (codQuarto == null) {
			if (other.codQuarto != null)
				return false;
		} else if (!codQuarto.equals(other.codQuarto))
			return false;
		if (codTipoQuarto == null) {
			if (other.codTipoQuarto != null)
				return false;
		} else if (!codTipoQuarto.equals(other.codTipoQuarto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (idQuarto == null) {
			if (other.idQuarto != null)
				return false;
		} else if (!idQuarto.equals(other.idQuarto))
			return false;
		if (nrCamas == null) {
			if (other.nrCamas != null)
				return false;
		} else if (!nrCamas.equals(other.nrCamas))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quarto [codQuarto=" + codQuarto + ", codFilial=" + codFilial + ", idQuarto=" + idQuarto
				+ ", codTipoQuarto=" + codTipoQuarto + ", nrCamas=" + nrCamas + ", descricao=" + descricao + ", foto="
				+ foto + ", status=" + status + ", atributos=" + atributos + "]";
	}

}
