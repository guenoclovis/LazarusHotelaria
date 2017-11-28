package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.TipoQuartoDTO;
import br.org.ufpr.tcc.entity.TipoQuarto;

public class TipoQuartoToDTO {

	public TipoQuartoDTO convert(TipoQuarto tipoquarto){
		TipoQuartoDTO dto = new TipoQuartoDTO();
		
		dto.setCodTipoQuarto(tipoquarto.getCodTipoQuarto());
		dto.setTipo(tipoquarto.getTipo().toString());		
		dto.setNome(tipoquarto.getNome());
		dto.setDescricao(tipoquarto.getDescricao());		
		dto.setStatus(String.valueOf(tipoquarto.getStatus()));
		
		return dto;
	}

}
