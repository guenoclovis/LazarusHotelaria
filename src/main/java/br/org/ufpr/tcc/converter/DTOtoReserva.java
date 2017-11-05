package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.ReservaBC;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoReserva {

	public Reserva convert(ReservaDTO dto) {
		Reserva reserva = new Reserva();

		if (dto.getCodReserva() != null) {
			reserva = new ReservaBC().obter(dto.getCodReserva());
		}
		if (dto.getDtEntrada() != null) {
			reserva.setDtEntrada(
					DataUtil.toDate(dto.getDtEntrada().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
		}
		if (dto.getDtSaida() != null) {
			reserva.setDtSaida(
					DataUtil.toDate(dto.getDtSaida().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
		}
		if (dto.getDtReserva() != null) {
			reserva.setDtReserva(
					DataUtil.toDate(dto.getDtReserva().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
		}

		reserva.setPreco(dto.getPreco());
		reserva.setStatus(dto.getStatus());
		reserva.setCodCliente(dto.getCodCliente());
		reserva.setCodQuarto(dto.getCodQuarto());

		return reserva;
	}

}
