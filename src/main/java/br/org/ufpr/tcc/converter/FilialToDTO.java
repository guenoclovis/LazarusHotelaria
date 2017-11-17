package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.entity.Filial;

public class FilialToDTO {

	FotoToDTO converterFoto = new FotoToDTO();
	
	public FilialDTO convert(Filial filial){
		FilialDTO dto = new FilialDTO();
		
		dto.setCodFilial(filial.getCodFilial());
		dto.setNome(filial.getNome());
		dto.setEmail(filial.getEmail());
		dto.setDescricao(filial.getDescricao());
		dto.setExibirSite(filial.getExibirSite());
		dto.setStatus(String.valueOf(filial.getStatus()));
		
		dto.setFoto(converterFoto.convert(filial.getFoto(), true, false));

		return dto;
	}

}
