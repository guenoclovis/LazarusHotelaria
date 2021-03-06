package br.org.ufpr.tcc.entity;

import java.util.Date;

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
@Table(name = "usuarios", schema="public")
public class Usuario {

	public static final String NOME = "nome";
	public static final String LOGIN = "login";

	@Id
    @Column(name = "cod_usuario")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", schema="public", sequenceName = "usuarios_cod_usuario_seq", allocationSize = 1)
	private Integer codUsuario;

	@Size(min=3, max=200)
	@NotNull
	@NotEmpty
	@Column(name="nome")
    private String nome;
	
	@Column(name="ativo")
    private Integer ativo;
	
	@Column(name="dt_nasc")
    private Date dtNasc;
	
	@Column(name="sexo")
    private Integer sexo;
	
	@Column(name="nacionalidade")
    private String nacionalidade;
	
	@Column(name="perfil")
    private String perfil;
	
	@Column(name="telefone")
    private String telefone;
	
	@Column(name="email")
    private String email;
	
	@Column(name="cpf")
    private String cpf;
	
	@Column(name="rg")
    private String rg;
	
	@Column(name="passaporte")
    private String passaporte;
	
	@Column(name="end_rua")
    private String endRua;
	
	@Column(name="end_nro")
    private Integer endNro;
	
	@Column(name="end_bairro")
    private String endBairro;
	
	@Column(name="end_cidade")
    private String endCidade;
	
	@Column(name="end_uf")
    private String endUf;
	
	@Column(name="end_compl")
    private String endCompl;
	
	@Column(name="login")
    private String login;
	
	@Column(name="senha")
    private String senha;
	
	@Column(name="status")
    private Integer status;

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getEndRua() {
		return endRua;
	}

	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}

	public Integer getEndNro() {
		return endNro;
	}

	public void setEndNro(Integer endNro) {
		this.endNro = endNro;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndUf() {
		return endUf;
	}

	public void setEndUf(String endUf) {
		this.endUf = endUf;
	}

	public String getEndCompl() {
		return endCompl;
	}

	public void setEndCompl(String endCompl) {
		this.endCompl = endCompl;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((codUsuario == null) ? 0 : codUsuario.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dtNasc == null) ? 0 : dtNasc.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endBairro == null) ? 0 : endBairro.hashCode());
		result = prime * result + ((endCidade == null) ? 0 : endCidade.hashCode());
		result = prime * result + ((endCompl == null) ? 0 : endCompl.hashCode());
		result = prime * result + ((endNro == null) ? 0 : endNro.hashCode());
		result = prime * result + ((endRua == null) ? 0 : endRua.hashCode());
		result = prime * result + ((endUf == null) ? 0 : endUf.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((passaporte == null) ? 0 : passaporte.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (codUsuario == null) {
			if (other.codUsuario != null)
				return false;
		} else if (!codUsuario.equals(other.codUsuario))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dtNasc == null) {
			if (other.dtNasc != null)
				return false;
		} else if (!dtNasc.equals(other.dtNasc))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endBairro == null) {
			if (other.endBairro != null)
				return false;
		} else if (!endBairro.equals(other.endBairro))
			return false;
		if (endCidade == null) {
			if (other.endCidade != null)
				return false;
		} else if (!endCidade.equals(other.endCidade))
			return false;
		if (endCompl == null) {
			if (other.endCompl != null)
				return false;
		} else if (!endCompl.equals(other.endCompl))
			return false;
		if (endNro == null) {
			if (other.endNro != null)
				return false;
		} else if (!endNro.equals(other.endNro))
			return false;
		if (endRua == null) {
			if (other.endRua != null)
				return false;
		} else if (!endRua.equals(other.endRua))
			return false;
		if (endUf == null) {
			if (other.endUf != null)
				return false;
		} else if (!endUf.equals(other.endUf))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (passaporte == null) {
			if (other.passaporte != null)
				return false;
		} else if (!passaporte.equals(other.passaporte))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", nome=" + nome + ", ativo=" + ativo + ", dtNasc=" + dtNasc
				+ ", sexo=" + sexo + ", nacionalidade=" + nacionalidade + ", perfil=" + perfil + ", telefone="
				+ telefone + ", email=" + email + ", cpf=" + cpf + ", rg=" + rg + ", passaporte=" + passaporte
				+ ", endRua=" + endRua + ", endNro=" + endNro + ", endBairro=" + endBairro + ", endCidade=" + endCidade
				+ ", endUf=" + endUf + ", endCompl=" + endCompl + ", login=" + login + ", senha=" + senha + ", status="
				+ status + "]";
	}

}
