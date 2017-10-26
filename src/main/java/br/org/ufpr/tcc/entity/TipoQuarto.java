package br.org.ufpr.tcc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import java.util.Date;

@Entity
@Table(name = "TipoQuarto", schema="public")
public class TipoQuarto {

	public static final String NOME = "nome";
	
	@Id
    @Column(name = "cod_tipo_quarto")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", schema="public", sequenceName = "tipos_de_quarto_cod_tipos_quarto_seq", allocationSize = 1)
	private Integer codTipoQuarto;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "status")
	private char status;	
	
	@Override
	public String toString() {
		return "TipoQuarto [codTipoQuarto=" + codTipoQuarto + ", tipo=" + tipo + ", nome=" + nome + ", descricao=" + descricao
				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTipoQuarto == null) ? 0 : codTipoQuarto.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + status;
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
		TipoQuarto other = (TipoQuarto) obj;
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	public Integer getCodTipoQuarto() {
		return codTipoQuarto;
	}
	public void setCodTipoQuarto(Integer codTipoQuarto) {
		this.codTipoQuarto = codTipoQuarto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}	
	
}
