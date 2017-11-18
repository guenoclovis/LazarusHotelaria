package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.util.DataUtil;

public class QuartoToDTO {

	FotoToDTO converterFoto = new FotoToDTO();
	
	public QuartoDTO convert(Quarto quarto){
		QuartoDTO dto = new QuartoDTO();

		dto.setCodQuarto(quarto.getCodQuarto());
		dto.setCodQuarto(quarto.getCodFilial());
		dto.setIdQuarto(quarto.getIdQuarto());
		dto.setCodQuarto(quarto.getCodTipoQuarto());
		dto.setNrCamas(quarto.getNrCamas());
		dto.setDescricao(quarto.getDescricao());
		dto.setStatus(quarto.getStatus());
		dto.setFoto(converterFoto.convert(quarto.getFoto(), true, false));

		return dto;
	}
	
}
