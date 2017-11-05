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
	@NotEmpty
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

	@Column(name = "status")
    private char status;
	
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
	
	

}
