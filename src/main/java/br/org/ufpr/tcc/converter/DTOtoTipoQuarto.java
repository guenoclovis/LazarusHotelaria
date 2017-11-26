package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.TipoQuartoBC;
import br.org.ufpr.tcc.dto.TipoQuartoDTO;
import br.org.ufpr.tcc.entity.TipoQuarto;

public class DTOtoTipoQuarto {
	
	public TipoQuarto convert(TipoQuartoDTO dto){
		TipoQuarto tipoquarto = new TipoQuarto();

		if(dto.getCodTipoQuarto() != null){
			tipoquarto = new TipoQuartoBC().obter(dto.getCodTipoQuarto());
		}
		
		tipoquarto.setCodTipoQuarto(dto.getCodTipoQuarto());
		tipoquarto.setTipo("1");		
		tipoquarto.setNome(dto.getNome());
		tipoquarto.setDescricao(dto.getDescricao());		
		tipoquarto.setStatus(Integer.valueOf(dto.getStatus()));

		return tipoquarto;
	}

}
