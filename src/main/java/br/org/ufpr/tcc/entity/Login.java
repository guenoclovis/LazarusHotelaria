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
@Table(name = "logins", schema="public")

public class Login {

public static final String NOME = "nome";
	
	@Id
    @Column(name = "cod_login")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATRIBUTO")
    @SequenceGenerator(name = "SEQ_ATRIBUTO", schema="public", sequenceName = "logins_cod_login_seq", allocationSize = 1)
	private Integer codLogin;
	
	@Column(name="tipo")
	private String tipo;
	
	@Size(min=3, max=200)
	//@NotNulldescricao
	//@NotEmpty
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="status")
	private char status;	
	
	@Override
	public String toString() {
		return "Login [codLogin=" + codLogin + ", tipo=" + tipo + ", nome=" + nome + ", descricao=" + descricao
				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codLogin == null) ? 0 : codLogin.hashCode());
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
		Login other = (Login) obj;
		if (codLogin == null) {
			if (other.codLogin != null)
				return false;
		} else if (!codLogin.equals(other.codLogin))
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
	public Integer getCodLogin() {
		return codLogin;
	}
	public void setCodLogin(Integer codLogin) {
		this.codLogin = codLogin;
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
