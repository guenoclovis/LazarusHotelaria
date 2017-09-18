package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.ShowcaseBC;
import br.org.ufpr.tcc.converter.DTOtoShowcase;
import br.org.ufpr.tcc.converter.ShowcaseToDTO;
import br.org.ufpr.tcc.dto.ShowcaseDTO;
import br.org.ufpr.tcc.dto.ShowcaseFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Showcase;
import br.org.ufpr.tcc.entity.Pagina;



public class ShowcaseFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private ShowcaseBC bc = new ShowcaseBC();    

    public ShowcaseDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de showcase id[%d]" + id;
        
        log.info(logMsg);

        Showcase f = bc.obter(id.intValue());

        logMsg = "Busca de showcase finalizada";
        log.info(logMsg);

        //CONVERTER
        ShowcaseToDTO converter = new ShowcaseToDTO();
        ShowcaseDTO showcaseDTO = converter.convert(f);
    	
        return showcaseDTO;
    }

    public ResultadoPaginadoDTO<ShowcaseDTO> listar(ShowcaseFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Showcase Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Showcase> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<ShowcaseDTO> filiaisDTO = new ArrayList<ShowcaseDTO>();
        for(Showcase f : listagem.getEntidades()){
        	ShowcaseToDTO converter = new ShowcaseToDTO();
        	ShowcaseDTO showcaseDTO = converter.convert(f);
            
            filiaisDTO.add(showcaseDTO);
        }
		
		ResultadoPaginadoDTO<ShowcaseDTO> responseDTO = new ResultadoPaginadoDTO<ShowcaseDTO>(filiaisDTO, new Pagina());

        logMsg = "Finalizando listagem de Showcase";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(ShowcaseDTO dto) {
        String logMsg = "Iniciando a persistencia de Showcase";
        log.info(logMsg);

        //CONVERTER
        DTOtoShowcase converter = new DTOtoShowcase();
        Showcase showcase = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(showcase);

        logMsg = "Registro de Showcase persistido";
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

	public ResponseDTO inserir(ShowcaseDTO dto) {
		String logMsg = "Iniciando a persistência de Showcase";
        log.info(logMsg);

        //CONVERTER
        DTOtoShowcase converter = new DTOtoShowcase();
        Showcase showcase = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(showcase);

        logMsg = "Registro de Showcase persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
