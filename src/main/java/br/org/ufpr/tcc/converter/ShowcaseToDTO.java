package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.ShowcaseDTO;
import br.org.ufpr.tcc.entity.Showcase;

public class ShowcaseToDTO {

	public ShowcaseDTO convert(Showcase showcase){
		ShowcaseDTO dto = new ShowcaseDTO();
		
		dto.setCodShowcase(showcase.getCodShowcase());
		dto.setNome(showcase.getNome());
		dto.setEmail(showcase.getEmail());
		dto.setDescricao(showcase.getDescricao());
		dto.setExibirSite(showcase.getExibirSite());
		dto.setStatus(showcase.getStatus());
		
		

		return dto;
	}

}
