package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.UsuarioBC;
import br.org.ufpr.tcc.converter.UsuarioToDTO;
import br.org.ufpr.tcc.converter.DTOtoUsuario;
import br.org.ufpr.tcc.dto.UsuarioDTO;
import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.entity.Pagina;

public class UsuarioFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private UsuarioBC bc = new UsuarioBC();    

    public UsuarioDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca do Usuario id[%d]" + id;
        
        log.info(logMsg);

        Usuario u = bc.obter(id.intValue());

        logMsg = "Busca do Usuario finalizada";
        log.info(logMsg);

        //CONVERTER
        UsuarioToDTO converter = new UsuarioToDTO();
        UsuarioDTO usuarioDTO = converter.convert(u);
    	
        return usuarioDTO;
    }

    public ResultadoPaginadoDTO<UsuarioDTO> listar(UsuarioFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Usuario Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Usuario> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        for(Usuario u : listagem.getEntidades()){
        	UsuarioToDTO converter = new UsuarioToDTO();
        	UsuarioDTO usuarioDTO = converter.convert(u);
            
            usuariosDTO.add(usuarioDTO);
        }
		
		ResultadoPaginadoDTO<UsuarioDTO> responseDTO = new ResultadoPaginadoDTO<UsuarioDTO>(usuariosDTO, new Pagina());

        logMsg = "Finalizando listagem de Usuario";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(UsuarioDTO dto) {
        String logMsg = "Iniciando a persistência de Usuario";
        log.info(logMsg);

        //CONVERTER
        DTOtoUsuario converter = new DTOtoUsuario();
        Usuario usuario = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(usuario);

        logMsg = "Registro de Usuario persistido";
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

	public ResponseDTO inserir(UsuarioDTO dto) {
		String logMsg = "Iniciando a persistência de Usuario";
        log.info(logMsg);

        //CONVERTER
        DTOtoUsuario converter = new DTOtoUsuario();
        Usuario usuario = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(usuario);

        logMsg = "Registro de Usuario persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
