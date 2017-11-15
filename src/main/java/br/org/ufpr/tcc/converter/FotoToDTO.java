package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.entity.Foto;

public class FotoToDTO {

	public FotoDTO convert(Foto foto){
		
		FotoDTO dto = new FotoDTO();
		
		if(foto != null){
		
			dto.setCodFoto(foto.getCodFoto());
			dto.setNomeFotoOriginal(foto.getNomeFotoOriginal());		
			dto.setNomeFotoMiniatura(foto.getNomeFotoMiniatura());
			dto.setLegenda(foto.getLegenda());
		
		}
		
		return dto;
	}

}
