package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.enuns.StatusEnum;

public class AtributoToDTO {

	public AtributoDTO convert(Atributo atributo){
		AtributoDTO dto = new AtributoDTO();
		
		dto.setCodAtributo(atributo.getCodAtributo());
		if(atributo.getTipo() != null){
			dto.setTipo(atributo.getTipo().toString());
		}
		dto.setNome(atributo.getNome());
		dto.setDescricao(atributo.getDescricao());		
		dto.setCodStatus(atributo.getStatus());
		if(atributo.getStatus() != null){
			dto.setDescricaoStatus(StatusEnum.fromCodigo(atributo.getStatus()).getDescricao());
		}
		
		return dto;
	}

}
