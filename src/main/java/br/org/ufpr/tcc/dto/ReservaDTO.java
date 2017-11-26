package br.org.ufpr.tcc.dto;

public class ReservaDTO {

	private Integer codReserva;
    private String dataEntrada;
    private String dataSaida;
    private String dtReserva;
    private long preco;
    private Integer status;
    private Integer codCliente;
    
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    
    private QuartoDTO quarto;
    
	public Integer getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(Integer codReserva) {
		this.codReserva = codReserva;
	}
	public String getDtEntrada() {
		return dataEntrada;
	}
	public void setDtEntrada(String dtEntrada) {
		this.dataEntrada = dtEntrada;
	}
	public String getDtSaida() {
		return dataSaida;
	}
	public void setDtSaida(String dtSaida) {
		this.dataSaida = dtSaida;
	}
	public String getDtReserva() {
		return dtReserva;
	}
	public void setDtReserva(String dtReserva) {
		this.dtReserva = dtReserva;
	}
	public long getPreco() {
		return preco;
	}
	public void setPreco(long preco) {
		this.preco = preco;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	public QuartoDTO getQuarto() {
		return quarto;
	}
	public void setQuarto(QuartoDTO quarto) {
		this.quarto = quarto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	
}

