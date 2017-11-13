package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.entity.Foto;

public class FotoToDTO {

	public FotoDTO convert(Foto atributo){
		FotoDTO dto = new FotoDTO();
		
		dto.setCodFoto(atributo.getCodFoto());
		dto.setPath(atributo.getPath());		
		dto.setLegenda(atributo.getLegenda());
		
		return dto;
	}

}
