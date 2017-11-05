package br.org.ufpr.tcc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.org.ufpr.tcc.validator.validacoes.NaoNulo;
import br.org.ufpr.tcc.validator.validacoes.NaoVazio;

@Entity
@Table(name = "clientes", schema="public")
public class Cliente {

	public static final String ID = "codCliente";
	public static final String NOME = "nome";
	
	@Id
    @Column(name = "cod_cliente")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CLIENTE")
    @SequenceGenerator(name = "SEQ_CLIENTE", schema="public", sequenceName = "clientes_cod_cliente_seq", allocationSize = 1)
	private Integer codCliente;
	
	@NaoNulo(nomeCampo = "Nome")
	@NaoVazio(nomeCampo = "Nome")
	@Size(min=3, max=200)
	@Column(name = "nome")
    private String nome;
	
	@NaoNulo(message = "{ER001}", nomeCampo = "Data de Nascimento")	
	@Column(name = "dt_nasc")
    private Date dtNasc;
	
	@Column(name = "sexo")
    private Integer sexo;
	
	@Column(name = "nacionalidade")
    private String nacionalidade;
	
	@NaoNulo(nomeCampo = "Telefone 1")
	@NaoVazio(nomeCampo = "Telefone 1")
	@Column(name = "telefone1")
    private String telefone1;
	
	@Column(name = "telefone2")
    private String telefone2;
	
	@NaoNulo(nomeCampo = "Email 1")
	@NaoVazio(nomeCampo = "Nome")
	@Column(name = "email1")
    private String email1;
	
	@Column(name = "email2")
    private String email2;
	
	@NaoNulo(nomeCampo = "CPF")
	@NaoVazio(nomeCampo = "CPF")
	@CPF
	@Column(name = "cpf")
    private String cpf;

	@Column(name = "rg")
    private String rg;

	@Column(name = "passaporte")
    private String passaporte;

	@Column(name = "end_rua")
    private String endRua;

	@Column(name = "end_nro")
    private Integer endNro;

	@Column(name = "end_bairro")
    private String endBairro;

	@Column(name = "end_cidade")
    private String endCidade;

	@Column(name = "end_uf")
    private String endUf;

	@Column(name = "end_compl")
    private String endCompl;

	@NaoNulo(nomeCampo = "Senha")
	@NaoVazio(nomeCampo = "Nome")
	@Column(name = "senha_acesso")
    private String senhaAcesso;

	@Column(name = "status")
    private char status;
    
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
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
	public String getSenhaAcesso() {
		return senhaAcesso;
	}
	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dtNasc == null) ? 0 : dtNasc.hashCode());
		result = prime * result + ((email1 == null) ? 0 : email1.hashCode());
		result = prime * result + ((email2 == null) ? 0 : email2.hashCode());
		result = prime * result + ((endBairro == null) ? 0 : endBairro.hashCode());
		result = prime * result + ((endCidade == null) ? 0 : endCidade.hashCode());
		result = prime * result + ((endCompl == null) ? 0 : endCompl.hashCode());
		result = prime * result + ((endNro == null) ? 0 : endNro.hashCode());
		result = prime * result + ((endRua == null) ? 0 : endRua.hashCode());
		result = prime * result + ((endUf == null) ? 0 : endUf.hashCode());
		result = prime * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((passaporte == null) ? 0 : passaporte.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((senhaAcesso == null) ? 0 : senhaAcesso.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + status;
		result = prime * result + ((telefone1 == null) ? 0 : telefone1.hashCode());
		result = prime * result + ((telefone2 == null) ? 0 : telefone2.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codCliente == null) {
			if (other.codCliente != null)
				return false;
		} else if (!codCliente.equals(other.codCliente))
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
		if (email1 == null) {
			if (other.email1 != null)
				return false;
		} else if (!email1.equals(other.email1))
			return false;
		if (email2 == null) {
			if (other.email2 != null)
				return false;
		} else if (!email2.equals(other.email2))
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
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (senhaAcesso == null) {
			if (other.senhaAcesso != null)
				return false;
		} else if (!senhaAcesso.equals(other.senhaAcesso))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (status != other.status)
			return false;
		if (telefone1 == null) {
			if (other.telefone1 != null)
				return false;
		} else if (!telefone1.equals(other.telefone1))
			return false;
		if (telefone2 == null) {
			if (other.telefone2 != null)
				return false;
		} else if (!telefone2.equals(other.telefone2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome + ", dtNasc=" + dtNasc + ", sexo=" + sexo
				+ ", nacionalidade=" + nacionalidade + ", telefone1=" + telefone1 + ", telefone2=" + telefone2
				+ ", email1=" + email1 + ", email2=" + email2 + ", cpf=" + cpf + ", rg=" + rg + ", passaporte="
				+ passaporte + ", endRua=" + endRua + ", endNro=" + endNro + ", endBairro=" + endBairro + ", endCidade="
				+ endCidade + ", endUf=" + endUf + ", endCompl=" + endCompl + ", senhaAcesso=" + senhaAcesso
				+ ", status=" + status + "]";
	}
	

}
