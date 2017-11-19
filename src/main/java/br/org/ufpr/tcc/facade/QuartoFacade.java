package br.org.ufpr.tcc.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.QuartoBC;
import br.org.ufpr.tcc.converter.QuartoToDTO;
import br.org.ufpr.tcc.converter.DTOtoQuarto;
import br.org.ufpr.tcc.dto.FilialDTO;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.QuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.util.Constantes;
import br.org.ufpr.tcc.util.ImageUtil;
import br.org.ufpr.tcc.entity.Pagina;

public class QuartoFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private QuartoBC bc = new QuartoBC(); 
    private FotoFacade fotoFacade = new FotoFacade();

    public QuartoDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca do quarto id[%d]" + id;
        
        log.info(logMsg);

        Quarto c = bc.obter(id.intValue());

        logMsg = "Busca do quarto finalizada";
        log.info(logMsg);

        //CONVERTER
        QuartoToDTO converter = new QuartoToDTO();
        QuartoDTO quartoDTO = converter.convert(c);
    	
        return quartoDTO;
    }

    public ResultadoPaginadoDTO<QuartoDTO> listar(QuartoFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Quarto Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Quarto> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<QuartoDTO> quartosDTO = new ArrayList<QuartoDTO>();
        for(Quarto c : listagem.getEntidades()){
        	QuartoToDTO converter = new QuartoToDTO();
        	QuartoDTO quartoDTO = converter.convert(c);
            
        	quartosDTO.add(quartoDTO);
        }
		
		ResultadoPaginadoDTO<QuartoDTO> responseDTO = new ResultadoPaginadoDTO<QuartoDTO>(quartosDTO, new Pagina());

        logMsg = "Finalizando listagem de Quarto";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(QuartoDTO dto) {
        String logMsg = "Iniciando a persistência de Quarto";
        log.info(logMsg);

        //CONVERTER
        DTOtoQuarto converter = new DTOtoQuarto();
        Quarto quarto = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(quarto);
		
		fotoFacade.moverFotosPastaTMPParaPastaDefinitiva(dto.getFoto());

        logMsg = "Registro de Quarto persistido";
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
    
	public ResponseDTO inserir(QuartoDTO dto) {
		String logMsg = "Iniciando a persistência de Quarto";
        log.info(logMsg);

        //CONVERTER
        DTOtoQuarto converter = new DTOtoQuarto();
        Quarto quarto = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(quarto);

        logMsg = "Registro de Quarto persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
