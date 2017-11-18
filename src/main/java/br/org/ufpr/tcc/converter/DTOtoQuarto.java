package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.QuartoBC;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoQuarto {
	
	DTOtoFoto converterFoto = new DTOtoFoto();
	
	public Quarto convert(QuartoDTO dto) {
		Quarto quarto = new Quarto();

		if (dto.getCodQuarto() != null) {
			quarto = new QuartoBC().obter(dto.getCodQuarto());
		}

		quarto.setCodQuarto(dto.getCodQuarto());

		quarto.setCodFilial(dto.getCodFilial());
		quarto.setIdQuarto(dto.getIdQuarto());
		quarto.setCodTipoQuarto(dto.getCodTipoQuarto());
		quarto.setNrCamas(dto.getNrCamas());
		quarto.setDescricao(dto.getDescricao());
		quarto.setStatus(dto.getStatus());

		if(dto.getFoto() != null){
			quarto.setFoto(converterFoto.convert(dto.getFoto()));
		}

		
		return quarto;
	}	

}
