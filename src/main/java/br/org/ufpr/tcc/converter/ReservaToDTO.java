package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.ClienteBC;
import br.org.ufpr.tcc.bc.FilialBC;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.enuns.StatusReservaEnum;
import br.org.ufpr.tcc.util.DataUtil;

public class ReservaToDTO {

	public ReservaDTO convert(Reserva reserva) {

		ReservaDTO dto = new ReservaDTO();
		if (reserva != null) {

			dto.setCodReserva(reserva.getCodReserva());
			dto.setDtEntrada(DataUtil.fromDateToString(reserva.getDtEntrada()));
			dto.setDtSaida(DataUtil.fromDateToString(reserva.getDtSaida()));
			dto.setDtReserva(DataUtil.fromDateToString(reserva.getDtReserva()));
			dto.setPreco(reserva.getPreco());
			dto.setStatus(reserva.getStatus());
			
			if(reserva.getStatus() != null){
				dto.setStatusDescricao(StatusReservaEnum.fromCodigo(reserva.getStatus()).getDescricao());
			}
			
			dto.setCodCliente(reserva.getCodCliente());

			if (reserva.getCodCliente() != null) {
				ClienteBC clienteBC = new ClienteBC();
				Cliente cliente = clienteBC.obter(reserva.getCodCliente());

				dto.setNome(cliente.getNome());
				dto.setTelefone(cliente.getTelefone1());
				dto.setCpf(cliente.getCpf());
				dto.setEmail(cliente.getEmail1());
			}

			if (reserva.getQuarto() != null) {
				QuartoToDTO converterQuarto = new QuartoToDTO();
				QuartoDTO qDTO = converterQuarto.convert(reserva.getQuarto());
				dto.setQuarto(qDTO);

				if (qDTO.getCodFilial() != null) {
					dto.setCodFilial(qDTO.getCodFilial());

					FilialBC filialBC = new FilialBC();

					Filial filial = filialBC.obter(qDTO.getCodFilial());

					if (filial != null) {
						dto.setNomeFilial(filial.getNome());
					}
				}
			}
		}

		return dto;
	}

}
