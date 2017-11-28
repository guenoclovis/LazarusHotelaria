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
			try {
				reserva.setDtEntrada(
						DataUtil.toDate(dto.getDtEntrada().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
			} catch (Exception e) {
				reserva.setDtEntrada(
						DataUtil.toDate(dto.getDtEntrada(), "yyyy-MM-dd HH:mm"));
			}
		}
		if (dto.getDtSaida() != null) {
			try {
				reserva.setDtSaida(
						DataUtil.toDate(dto.getDtSaida().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
			} catch (Exception e) {
				reserva.setDtSaida(
						DataUtil.toDate(dto.getDtSaida(), "yyyy-MM-dd HH:mm"));
			}
		}
		if (dto.getDtReserva() != null) {
			try {
				reserva.setDtReserva(
						DataUtil.toDate(dto.getDtReserva().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
			} catch (Exception e) {
				reserva.setDtReserva(
						DataUtil.toDate(dto.getDtReserva(), "yyyy-MM-dd HH:mm"));
			}
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
