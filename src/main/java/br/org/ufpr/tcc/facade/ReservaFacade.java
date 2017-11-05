package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.ReservaBC;
import br.org.ufpr.tcc.converter.ReservaToDTO;
import br.org.ufpr.tcc.converter.DTOtoReserva;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.entity.Pagina;

public class ReservaFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private ReservaBC bc = new ReservaBC();    

    public ReservaDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca do reserva id[%d]" + id;
        
        log.info(logMsg);

        Reserva c = bc.obter(id.intValue());

        logMsg = "Busca do reserva finalizada";
        log.info(logMsg);

        //CONVERTER
        ReservaToDTO converter = new ReservaToDTO();
        ReservaDTO reservaDTO = converter.convert(c);
    	
        return reservaDTO;
    }

    public ResultadoPaginadoDTO<ReservaDTO> listar(ReservaFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Reserva Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Reserva> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
        for(Reserva c : listagem.getEntidades()){
        	ReservaToDTO converter = new ReservaToDTO();
        	ReservaDTO reservaDTO = converter.convert(c);
            
        	reservasDTO.add(reservaDTO);
        }
		
		ResultadoPaginadoDTO<ReservaDTO> responseDTO = new ResultadoPaginadoDTO<ReservaDTO>(reservasDTO, new Pagina());

        logMsg = "Finalizando listagem de Reserva";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(ReservaDTO dto) {
        String logMsg = "Iniciando a persistência de reserva";
        log.info(logMsg);

        //CONVERTER
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);

        return responseDTO;
    }
    
    public ResponseDTO remover(Long... ids) {
    	ResponseDTO retorno = new ResponseDTO();
    	
    	for(Long id : ids){
    		ResponseDTO aux = bc.remover(id.intValue());
    		retorno.getMensagens().addAll(aux.getMensagens());
    	}
    	
    	return retorno;
    	 
    }

	public ResponseDTO inserir(ReservaDTO dto) {
		String logMsg = "Iniciando a persistência de Reserva";
        log.info(logMsg);

        //CONVERTER
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
