package br.org.ufpr.tcc.converter;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.bc.QuartoBC;
import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoQuarto {
	
	DTOtoFoto converterFoto = new DTOtoFoto();
	DTOtoAtributo converterAtributo = new DTOtoAtributo();
	DTOtoReserva converterReserva = new DTOtoReserva();
	
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

		if(dto.getAtributos() != null){
			List<Atributo> listaAtributos = new ArrayList<Atributo>();
			
			Atributo a = null;
			for(AtributoDTO aDto : dto.getAtributos()){
				a = converterAtributo.convert(aDto);
				
				listaAtributos.add(a);
			}
			
			quarto.setAtributos(listaAtributos);
		}
		
		if(dto.getReservas() != null){
			List<Reserva> listaReservas = new ArrayList<Reserva>();

			Reserva a = null;
			for (ReservaDTO aDTO : dto.getReservas()) {
				a = converterReserva.convert(aDTO);

				listaReservas.add(a);
			}

			quarto.setReservas(listaReservas);
		}
		
		return quarto;
	}	

}
