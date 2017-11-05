package br.org.ufpr.tcc.dto;

import java.util.Date;

public class ReservaDTO {

	private Integer codReserva;
    private Date dtEntrada;
    private Date dtSaida;
    private Date dtReserva;
    private long preco;
    private char status;
    private Integer codCliente;
    private Integer codQuarto;
    
	public Integer getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(Integer codReserva) {
		this.codReserva = codReserva;
	}
	public Date getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public Date getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(Date dtSaida) {
		this.dtSaida = dtSaida;
	}
	public Date getDtReserva() {
		return dtReserva;
	}
	public void setDtReserva(Date dtReserva) {
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
	public Integer getCodQuarto() {
		return codQuarto;
	}
	public void setCodQuarto(Integer codQuarto) {
		this.codQuarto = codQuarto;
	}
    
    
	
}
