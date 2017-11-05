package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.util.DataUtil;

public class QuartoToDTO {

	public QuartoDTO convert(Quarto quarto){
		QuartoDTO dto = new QuartoDTO();

		dto.setCodQuarto(quarto.getCodQuarto());
		dto.setIdQuarto(quarto.getIdQuarto());
		dto.setCodQuarto(quarto.getCodTipoQuarto());
		dto.setNrCamas(quarto.getNrCamas());
		dto.setDescricao(quarto.getDescricao());
		dto.setStatus(quarto.getStatus());

		return dto;
	}
	
}
