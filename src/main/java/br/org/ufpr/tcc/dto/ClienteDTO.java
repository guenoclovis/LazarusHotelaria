package br.org.ufpr.tcc.dto;

import java.util.Date;

import br.org.ufpr.tcc.util.DataUtil;

public class ClienteDTO {

	private Integer codCliente;
    private String nome;
    private String dtNasc;
    private Integer sexo;
    private String nacionalidade;
    private String telefone1;
    private String telefone2;
    private String email1;
    private String email2;
    private String cpf;
    private String rg;
    private String passaporte;
    private String endRua;
    private Integer endNro;
    private String endBairro;
    private String endCidade;
    private String endUf;
    private String endCompl;
    private String senhaAcesso;
    private Integer status;
    
    private String sexoExibicao;
    private String dataExibicao;
    
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
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
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

	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSexoExibicao() {
		if(getSexo() != null){
			if(getSexo().equals(1)){
				this.sexoExibicao = "Masculino";
			}
			if(getSexo().equals(2)){
				this.sexoExibicao = "Feminino";
			}
			if(getSexo().equals(0)){
				this.sexoExibicao = "Outro";
			}
			
		} else {
			this.sexoExibicao = "-";
		}
		
		return this.sexoExibicao;
	}
	public void setSexoExibicao(String sexoExibicao) {
		this.sexoExibicao = sexoExibicao;
	}
	public String getDataExibicao() {
		return this.dataExibicao;
	}
	public void setDataExibicao(String dataExibicao) {
		this.dataExibicao = dataExibicao;
	}
    

	
	
}
