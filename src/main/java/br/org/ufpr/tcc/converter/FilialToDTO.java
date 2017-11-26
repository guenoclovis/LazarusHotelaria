package br.org.ufpr.tcc.converter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Filial;

public class FilialToDTO {

	FotoToDTO converterFoto = new FotoToDTO();
	AtributoToDTO converterAtributo = new AtributoToDTO();

	
	public FilialDTO convert(Filial filial){
		FilialDTO dto = new FilialDTO();
		
		dto.setCodFilial(filial.getCodFilial());
		dto.setNome(filial.getNome());
		dto.setEmail(filial.getEmail());
		dto.setDescricao(filial.getDescricao());
		if(filial.getExibirSite() != null){
			dto.setExibirSite(Integer.valueOf(filial.getExibirSite()));
		}
		dto.setStatus(filial.getStatus());
		
		dto.setFoto(converterFoto.convert(filial.getFoto(), true, false));
		
		if (filial.getAtributos() != null) {

			List<AtributoDTO> listaAtributos = new ArrayList<AtributoDTO>();

			AtributoDTO aDTO = null;
//			Hibernate.initialize(filial.getAtributos());
//			for (Atributo a : filial.getAtributos()) {
//				aDTO = converterAtributo.convert(a);
//
//				listaAtributos.add(aDTO);
//			}

			dto.setAtributos(listaAtributos);

		}

		return dto;
	}

}
