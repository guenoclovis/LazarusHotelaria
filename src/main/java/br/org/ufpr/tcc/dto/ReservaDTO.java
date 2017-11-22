package br.org.ufpr.tcc.dto;

public class ReservaDTO {

	private Integer codReserva;
    private String dtEntrada;
    private String dtSaida;
    private String dtReserva;
    private long preco;
    private char status;
    private Integer codCliente;
    
    private QuartoDTO quarto;
    
	public Integer getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(Integer codReserva) {
		this.codReserva = codReserva;
	}
	public String getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(String dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public String getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(String dtSaida) {
		this.dtSaida = dtSaida;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
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
	
	@Override
	public String toString() {
		return "ReservaDTO [codReserva=" + codReserva + ", dtEntrada=" + dtEntrada + ", dtSaida=" + dtSaida
				+ ", dtReserva=" + dtReserva + ", preco=" + preco + ", status=" + status + ", codCliente=" + codCliente
				+ ", quarto=" + quarto + "]";
	}

}
