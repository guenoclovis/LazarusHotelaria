package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.FilialBC;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Filial;
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
		
		if(reserva.getQuarto() != null){
			QuartoToDTO converterQuarto = new QuartoToDTO();
			QuartoDTO qDTO = converterQuarto.convert(reserva.getQuarto());
			dto.setQuarto(qDTO);
			
			if(qDTO.getCodFilial() != null){
				dto.setCodFilial(qDTO.getCodFilial());
				
				FilialBC filialBC = new FilialBC();
				
				Filial filial = filialBC.obter(qDTO.getCodFilial());
				
				if(filial != null){
					dto.setNomeFilial(filial.getNome());
				}
			}
		}

		return dto;
	}
	
}
