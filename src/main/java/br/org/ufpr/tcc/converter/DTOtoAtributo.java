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
		if(dto.getTipo().equals("1")){
			atributo.setTipo("H");					
		} else {
			atributo.setTipo("Q");
		}
		atributo.setNome(dto.getNome());
		atributo.setDescricao(dto.getDescricao());
		atributo.setStatus('I');
		
		return atributo;
	}

}
