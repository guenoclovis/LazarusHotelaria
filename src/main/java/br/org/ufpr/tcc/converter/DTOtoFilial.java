package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.FilialBC;
import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.entity.Filial;

public class DTOtoFilial {
	
	public Filial convert(FilialDTO dto){
		Filial filial = new Filial();

		if(dto.getCodFilial() != null){
			filial = new FilialBC().obter(dto.getCodFilial());
		}
		
		filial.setCodFilial(dto.getCodFilial());
		filial.setNome(dto.getNome());
		filial.setEmail(dto.getEmail());
		filial.setDescricao(dto.getDescricao());
		filial.setExibirSite(dto.getExibirSite());
		filial.setStatus(dto.getStatus());

		return filial;
	}

}
