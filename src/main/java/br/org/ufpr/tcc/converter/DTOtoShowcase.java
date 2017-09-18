package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.ShowcaseBC;
import br.org.ufpr.tcc.dto.ShowcaseDTO;
import br.org.ufpr.tcc.entity.Showcase;

public class DTOtoShowcase {
	
	public Showcase convert(ShowcaseDTO dto){
		Showcase showcase = new Showcase();

		if(dto.getCodShowcase() != null){
			showcase = new ShowcaseBC().obter(dto.getCodShowcase());
		}
		
		showcase.setCodShowcase(dto.getCodShowcase());
		showcase.setNome(dto.getNome());
		showcase.setEmail(dto.getEmail());
		showcase.setDescricao(dto.getDescricao());
		showcase.setExibirSite(dto.getExibirSite());
		showcase.setStatus(dto.getStatus());

		return showcase;
	}

}
