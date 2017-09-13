package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.entity.Filial;

public class FilialToDTO {

	public FilialDTO convert(Filial filial){
		FilialDTO dto = new FilialDTO();
		
		dto.setCodFilial(filial.getCodFilial());
		dto.setNome(filial.getNome());
		dto.setDescricao(filial.getDescricao());
		dto.setExibirSite(filial.getExibirSite());
		dto.setStatus(filial.getStatus());
		
		

		return dto;
	}

}
