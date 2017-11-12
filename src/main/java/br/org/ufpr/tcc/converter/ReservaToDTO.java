package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.DataUtil;

public class ReservaToDTO {

	public ReservaDTO convert(Reserva reserva){
		ReservaDTO dto = new ReservaDTO();

		dto.setCodReserva(reserva.getCodReserva());
		dto.setDtEntrada(DataUtil.fromDateToString(reserva.getDtEntrada()));
		dto.setDtSaida(DataUtil.fromDateToString(reserva.getDtSaida()));
		dto.setDtReserva(DataUtil.fromDateToString(reserva.getDtReserva()));
		dto.setPreco(reserva.getPreco());
		dto.setStatus(reserva.getStatus());
		dto.setCodCliente(reserva.getCodCliente());
		dto.setCodQuarto(reserva.getCodQuarto());

		return dto;
	}
	
}