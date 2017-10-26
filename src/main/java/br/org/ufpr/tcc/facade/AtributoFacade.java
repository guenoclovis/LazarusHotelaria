package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.AtributoBC;
import br.org.ufpr.tcc.converter.DTOtoAtributo;
import br.org.ufpr.tcc.converter.AtributoToDTO;
import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.AtributoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Pagina;



public class AtributoFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private AtributoBC bc = new AtributoBC();    

    public AtributoDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de atributo id[%d]" + id;
        
        log.info(logMsg);

        Atributo f = bc.obter(id.intValue());

        logMsg = "Busca de atributo finalizada";
        log.info(logMsg);

        //CONVERTER
        AtributoToDTO converter = new AtributoToDTO();
        AtributoDTO atributoDTO = converter.convert(f);
    	
        return atributoDTO;
    }

    public ResultadoPaginadoDTO<AtributoDTO> listar(AtributoFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagem de Atributo Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Atributo> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<AtributoDTO> atributosDTO = new ArrayList<AtributoDTO>();
        for(Atributo f : listagem.getEntidades()){
        	AtributoToDTO converter = new AtributoToDTO();
        	AtributoDTO atributoDTO = converter.convert(f);
        	atributosDTO.add(atributoDTO);
        }
		
		ResultadoPaginadoDTO<AtributoDTO> responseDTO = new ResultadoPaginadoDTO<AtributoDTO>(atributosDTO, new Pagina());

        logMsg = "Finalizando listagem de Atributo";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(AtributoDTO dto) {
        String logMsg = "Iniciando a persistencia de Atributo";
        log.info(logMsg);

        //CONVERTER
        DTOtoAtributo converter = new DTOtoAtributo();
        Atributo atributo = converter.convert(dto);
        
		bc.persistir(atributo);

        logMsg = "Registro de Atributo persistido";
        log.info(logMsg);

        return new ResponseDTO();
    }
    
    public ResponseDTO remover(Long... ids) {
    	ResponseDTO retorno = new ResponseDTO();
    	
    	for(Long id : ids){
    		ResponseDTO aux = bc.remover(id.intValue());
    		retorno.getMensagens().addAll(aux.getMensagens());
    	}
    	
    	return retorno;
    	 
    }

	public ResponseDTO inserir(AtributoDTO dto) {
		String logMsg = "Iniciando a persistencia de Atributo";
        log.info(logMsg);

        //CONVERTER
        DTOtoAtributo converter = new DTOtoAtributo();
        Atributo atributo = converter.convert(dto);
        
		bc.persistir(atributo);

        logMsg = "Registro de Atributo persistido";
        log.info(logMsg);

        return new ResponseDTO();
	}

}
