package br.org.ufpr.tcc.converter;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.bc.TipoQuartoBC;
import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.entity.TipoQuarto;

public class QuartoToDTO {

	FotoToDTO converterFoto = new FotoToDTO();
	AtributoToDTO converterAtributo = new AtributoToDTO();
	ReservaToDTO converterReserva = new ReservaToDTO();

	public QuartoDTO convert(Quarto quarto) {
		QuartoDTO dto = new QuartoDTO();

		dto.setCodQuarto(quarto.getCodQuarto());
		dto.setIdQuarto(quarto.getIdQuarto());
		dto.setCodQuarto(quarto.getCodTipoQuarto());
		dto.setNrCamas(quarto.getNrCamas());
		dto.setDescricao(quarto.getDescricao());
		dto.setCodFilial(quarto.getCodFilial());
		dto.setStatus(quarto.getStatus());
		dto.setFoto(converterFoto.convert(quarto.getFoto(), true, false));
		
		setDescricaoTipoQuarto(quarto, dto);
		

		if (quarto.getAtributos() != null) {

			List<AtributoDTO> listaAtributos = new ArrayList<AtributoDTO>();

			AtributoDTO aDTO = null;
			for (Atributo a : quarto.getAtributos()) {
				aDTO = converterAtributo.convert(a);

				listaAtributos.add(aDTO);
			}

			dto.setAtributos(listaAtributos);

		}
		
		if(quarto.getReservas() != null){
//			List<ReservaDTO> listaReservas = new ArrayList<ReservaDTO>();
//
//			ReservaDTO aDTO = null;
//			for (Reserva a : quarto.getReservas()) {
//				aDTO = converterReserva.convert(a);
//
//				listaReservas.add(aDTO);
//			}
//
//			dto.setReservas(listaReservas);
		}

		return dto;
	}

	private void setDescricaoTipoQuarto(Quarto quarto, QuartoDTO dto) {
		TipoQuartoBC tipoQuartoBC = new TipoQuartoBC();
		TipoQuarto tipoQuarto = tipoQuartoBC.obter(quarto.getCodTipoQuarto());
		dto.setDescricaoTipoQuarto(tipoQuarto.getDescricao());
	}

}
