package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.FotoBC;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.entity.Foto;

public class DTOtoFoto {
	
	public Foto convert(FotoDTO dto){
		Foto atributo = new Foto();

		if(dto.getCodFoto() != null){
			atributo = new FotoBC().obter(dto.getCodFoto());
		}
		
		atributo.setCodFoto(dto.getCodFoto());
		atributo.setPath(dto.getPath());		
		atributo.setLegenda(dto.getLegenda());

		return atributo;
	}

}
