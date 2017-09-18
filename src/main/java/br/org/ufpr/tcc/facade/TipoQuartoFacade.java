package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.TipoQuartoBC;
import br.org.ufpr.tcc.converter.DTOtoTipoQuarto;
import br.org.ufpr.tcc.converter.TipoQuartoToDTO;
import br.org.ufpr.tcc.dto.TipoQuartoDTO;
import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.TipoQuarto;
import br.org.ufpr.tcc.entity.Pagina;



public class TipoQuartoFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private TipoQuartoBC bc = new TipoQuartoBC();    

    public TipoQuartoDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de tipoquarto id[%d]" + id;
        
        log.info(logMsg);

        TipoQuarto f = bc.obter(id.intValue());

        logMsg = "Busca de tipoquarto finalizada";
        log.info(logMsg);

        //CONVERTER
        TipoQuartoToDTO converter = new TipoQuartoToDTO();
        TipoQuartoDTO tipoquartoDTO = converter.convert(f);
    	
        return tipoquartoDTO;
    }

    public ResultadoPaginadoDTO<TipoQuartoDTO> listar(TipoQuartoFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagem de TipoQuarto Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<TipoQuarto> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<TipoQuartoDTO> tipoquartosDTO = new ArrayList<TipoQuartoDTO>();
        for(TipoQuarto f : listagem.getEntidades()){
        	TipoQuartoToDTO converter = new TipoQuartoToDTO();
        	TipoQuartoDTO tipoquartoDTO = converter.convert(f);
        	tipoquartosDTO.add(tipoquartoDTO);
        }
		
		ResultadoPaginadoDTO<TipoQuartoDTO> responseDTO = new ResultadoPaginadoDTO<TipoQuartoDTO>(tipoquartosDTO, new Pagina());

        logMsg = "Finalizando listagem de TipoQuarto";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(TipoQuartoDTO dto) {
        String logMsg = "Iniciando a persistencia de TipoQuarto";
        log.info(logMsg);

        //CONVERTER
        DTOtoTipoQuarto converter = new DTOtoTipoQuarto();
        TipoQuarto tipoquarto = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(tipoquarto);

        logMsg = "Registro de TipoQuarto persistido";
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

	public ResponseDTO inserir(TipoQuartoDTO dto) {
		String logMsg = "Iniciando a persistencia de TipoQuarto";
        log.info(logMsg);

        //CONVERTER
        DTOtoTipoQuarto converter = new DTOtoTipoQuarto();
        TipoQuarto tipoquarto = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(tipoquarto);

        logMsg = "Registro de TipoQuarto persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
