package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.FotoBC;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.entity.Foto;

public class DTOtoFoto {
	
	public Foto convert(FotoDTO dto){
		Foto foto = new Foto();

		if(dto.getCodFoto() != null){
			foto = new FotoBC().obter(dto.getCodFoto());
		}
				
		foto.setNomeFotoOriginal(dto.getNomeFotoOriginal());		
		foto.setNomeFotoMiniatura(dto.getNomeFotoMiniatura());
		foto.setLegenda(dto.getLegenda());

		return foto;
	}

}
