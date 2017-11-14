package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.FotoBC;
import br.org.ufpr.tcc.converter.DTOtoFoto;
import br.org.ufpr.tcc.converter.FotoToDTO;
import br.org.ufpr.tcc.dao.FotoDAO;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Pagina;



public class FotoFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private FotoBC bc = new FotoBC();    

    public FotoDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de foto id[%d]" + id;
        
        log.info(logMsg);

        Foto f = bc.obter(id.intValue());

        logMsg = "Busca de foto finalizada";
        log.info(logMsg);

        //CONVERTER
        FotoToDTO converter = new FotoToDTO();
        FotoDTO fotoDTO = converter.convert(f);
    	
        return fotoDTO;
    }

    public ResultadoPaginadoDTO<FotoDTO> listar(FotoFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagem de Foto Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Foto> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<FotoDTO> fotosDTO = new ArrayList<FotoDTO>();
        for(Foto f : listagem.getEntidades()){
        	FotoToDTO converter = new FotoToDTO();
        	FotoDTO fotoDTO = converter.convert(f);
        	fotosDTO.add(fotoDTO);
        }
		
		ResultadoPaginadoDTO<FotoDTO> responseDTO = new ResultadoPaginadoDTO<FotoDTO>(fotosDTO, new Pagina());

        logMsg = "Finalizando listagem de Foto";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(FotoDTO dto) {
        String logMsg = "Iniciando a persistencia de Foto";
        log.info(logMsg);

        //CONVERTER
        DTOtoFoto converter = new DTOtoFoto();
        Foto foto = converter.convert(dto);
        
		Foto fotoSalva = bc.persistir(foto);

        logMsg = "Registro de Foto persistido";
        log.info(logMsg);

        return new ResponseDTO(Long.valueOf(fotoSalva.getCodFoto()));
    }
    
    public ResponseDTO remover(Long... ids) {
    	ResponseDTO retorno = new ResponseDTO();
    	
    	for(Long id : ids){
    		ResponseDTO aux = bc.remover(id.intValue());
    		retorno.getMensagens().addAll(aux.getMensagens());
    	}
    	
    	return retorno;
    	 
    }

	public FotoDTO inserir(FotoDTO dto) {
		String logMsg = "Iniciando a persistencia de Foto";
        log.info(logMsg);

        //CONVERTER
        DTOtoFoto converter = new DTOtoFoto();
        Foto foto = converter.convert(dto);
        
		Foto fotoSalva = bc.persistir(foto);

        logMsg = "Registro de Foto persistido";
        log.info(logMsg);

        FotoToDTO toDTOConverter = new FotoToDTO();
        FotoDTO fotoSalvaDTO = toDTOConverter.convert(fotoSalva);
        
        return fotoSalvaDTO;
	}

}
