package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.TipoQuartoDTO;
import br.org.ufpr.tcc.entity.TipoQuarto;

public class TipoQuartoToDTO {

	public TipoQuartoDTO convert(TipoQuarto tipoquarto){
		TipoQuartoDTO dto = new TipoQuartoDTO();
		
		dto.setCodTipoQuarto(tipoquarto.getCodTipoQuarto());
		dto.setTipo(tipoquarto.getTipo());		
		dto.setNome(tipoquarto.getNome());
		dto.setDescricao(tipoquarto.getDescricao());		
		dto.setStatus(tipoquarto.getStatus());
		
		return dto;
	}

}
