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
		
		if(tipoquarto.getPreco() != null){
			String aux = tipoquarto.getPreco().toString();
			if(aux.endsWith(".0")){
				dto.setPreco(aux.replace(".0", ",00"));				
			} else
			
			if(aux.endsWith(".00")){
				dto.setPreco(aux.replace(".00", ",00"));				
			} else {
				dto.setPreco(aux.replace(".", ","));				
			}
			
			
		}
		
		return dto;
	}

}
