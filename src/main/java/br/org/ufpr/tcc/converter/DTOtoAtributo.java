package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.AtributoBC;
import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.entity.Atributo;

public class DTOtoAtributo {
	
	public Atributo convert(AtributoDTO dto){
		Atributo atributo = new Atributo();

		if(dto.getCodAtributo() != null){
			atributo = new AtributoBC().obter(dto.getCodAtributo());
		}
		
		atributo.setCodAtributo(dto.getCodAtributo());
		atributo.setTipo(dto.getTipo());		
		atributo.setNome(dto.getNome());
		atributo.setDescricao(dto.getDescricao());		
		atributo.setStatus(dto.getStatus());

		return atributo;
	}

}
