package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.entity.Atributo;

public class AtributoToDTO {

	public AtributoDTO convert(Atributo atributo){
		AtributoDTO dto = new AtributoDTO();
		
		dto.setCodAtributo(atributo.getCodAtributo());
		dto.setTipo(atributo.getTipo());		
		dto.setNome(atributo.getNome());
		dto.setDescricao(atributo.getDescricao());		
		dto.setStatus(atributo.getStatus());
		
		return dto;
	}

}
