package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.FilialBC;
import br.org.ufpr.tcc.converter.DTOtoFilial;
import br.org.ufpr.tcc.converter.FilialToDTO;
import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.dto.FilialFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.exception.handler.NegocioException;



public class FilialFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private FilialBC bc = new FilialBC();    

    public FilialDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de filial id[%d]" + id;
        
        log.info(logMsg);

        Filial f = bc.obter(id.intValue());

        logMsg = "Busca de filial finalizada";
        log.info(logMsg);

        //CONVERTER
        FilialToDTO converter = new FilialToDTO();
        FilialDTO filialDTO = converter.convert(f);
    	
        return filialDTO;
    }

    public ResultadoPaginadoDTO<FilialDTO> listar(FilialFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Filial Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Filial> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<FilialDTO> filiaisDTO = new ArrayList<FilialDTO>();
        for(Filial f : listagem.getEntidades()){
        	FilialToDTO converter = new FilialToDTO();
        	FilialDTO filialDTO = converter.convert(f);
            
            filiaisDTO.add(filialDTO);
        }
		
		ResultadoPaginadoDTO<FilialDTO> responseDTO = new ResultadoPaginadoDTO<FilialDTO>(filiaisDTO, listagem.getPagina(), listagem.getMensagens());

        logMsg = "Finalizando listagem de Filial";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(FilialDTO dto) {
        String logMsg = "Iniciando a persistencia de Filial";
        log.info(logMsg);

        //CONVERTER
        DTOtoFilial converter = new DTOtoFilial();
        Filial filial = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(filial);

        logMsg = "Registro de Filial persistido";
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

	public ResponseDTO inserir(FilialDTO dto) {
		String logMsg = "Iniciando a persistência de Filial";
        log.info(logMsg);

        //CONVERTER
        DTOtoFilial converter = new DTOtoFilial();
        Filial filial = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(filial);

        logMsg = "Registro de Filial persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
