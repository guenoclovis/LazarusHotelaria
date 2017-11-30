package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.ReservaBC;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoReserva {

	
	public Reserva convert(ReservaDTO dto) {
		Reserva reserva = new Reserva();

		if (dto.getCodReserva() != null) {
			reserva = new ReservaBC().obter(dto.getCodReserva());
		}
		if (dto.getDtEntrada() != null) {
			reserva.setDtEntrada(DataUtil.converterData(dto.getDtEntrada()));
		}
		if (dto.getDtSaida() != null) {
			reserva.setDtSaida(DataUtil.converterData(dto.getDtSaida()));
		}
		if (dto.getDtReserva() != null) {
			reserva.setDtReserva(DataUtil.converterData(dto.getDtReserva()));
		}

		reserva.setPreco(dto.getPreco());
		if(dto.getStatus() != null){
			reserva.setStatus(Integer.valueOf(dto.getStatus()));
		}
		reserva.setCodCliente(dto.getCodCliente());
		
		if(dto.getQuarto() != null){
			DTOtoQuarto converterQuarto = new DTOtoQuarto();
			Quarto quarto = converterQuarto.convert(dto.getQuarto());
			reserva.setQuarto(quarto);
		}

		return reserva;
	}
	
	

}
