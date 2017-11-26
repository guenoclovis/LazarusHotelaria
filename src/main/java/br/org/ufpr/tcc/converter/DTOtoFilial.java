package br.org.ufpr.tcc.converter;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.bc.FilialBC;
import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.enuns.StatusEnum;

public class DTOtoFilial {
	
	DTOtoFoto converterFoto = new DTOtoFoto();
	DTOtoAtributo converterAtributo = new DTOtoAtributo();

	
	public Filial convert(FilialDTO dto){
		Filial filial = new Filial();

		if(dto.getCodFilial() != null){
			filial = new FilialBC().obter(dto.getCodFilial());
		}
		
		filial.setCodFilial(dto.getCodFilial());
		filial.setNome(dto.getNome());
		filial.setEmail(dto.getEmail());
		filial.setDescricao(dto.getDescricao());
		filial.setExibirSite(Integer.valueOf(dto.getExibirSite()));
		filial.setStatus(Integer.valueOf(dto.getStatus()));
	
		if(dto.getFoto() != null){
			filial.setFoto(converterFoto.convert(dto.getFoto()));
		}
		
		if(dto.getAtributos() != null){
			List<Atributo> listaAtributos = new ArrayList<Atributo>();
			
			Atributo a = null;
			for(AtributoDTO aDto : dto.getAtributos()){
				a = converterAtributo.convert(aDto);
				
				listaAtributos.add(a);
			}
			
			filial.setAtributos(listaAtributos);
		}

		return filial;
	}

}
