package br.org.ufpr.tcc.converter;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Quarto;

public class QuartoToDTO {

	FotoToDTO converterFoto = new FotoToDTO();
	AtributoToDTO converterAtributo = new AtributoToDTO();

	public QuartoDTO convert(Quarto quarto) {
		QuartoDTO dto = new QuartoDTO();

		dto.setCodQuarto(quarto.getCodQuarto());
		dto.setCodQuarto(quarto.getCodFilial());
		dto.setIdQuarto(quarto.getIdQuarto());
		dto.setCodQuarto(quarto.getCodTipoQuarto());
		dto.setNrCamas(quarto.getNrCamas());
		dto.setDescricao(quarto.getDescricao());
		dto.setCodFilial(quarto.getCodFilial());
		dto.setStatus(quarto.getStatus());
		dto.setFoto(converterFoto.convert(quarto.getFoto(), true, false));

		if (quarto.getAtributos() != null) {

			List<AtributoDTO> listaAtributos = new ArrayList<AtributoDTO>();

			AtributoDTO aDTO = null;
			for (Atributo a : quarto.getAtributos()) {
				aDTO = converterAtributo.convert(a);

				listaAtributos.add(aDTO);
			}

			dto.setAtributos(listaAtributos);

		}

		return dto;
	}

}
