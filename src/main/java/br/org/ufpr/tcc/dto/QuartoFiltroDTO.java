package br.org.ufpr.tcc.dto;

import java.util.Date;

public class QuartoFiltroDTO extends PesquisaPaginaDTO {

	private Long codFilial;
	private Date dataEntrada;
	private Date dataSaida;
	
	public Long getCodFilial() {
		return codFilial;
	}
	public void setCodFilial(Long codFilial) {
		this.codFilial = codFilial;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
}
