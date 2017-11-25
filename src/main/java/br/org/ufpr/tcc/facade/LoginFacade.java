package br.org.ufpr.tcc.facade;

import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.LoginBC;
import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;



public class LoginFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private LoginBC bc = new LoginBC();

	public ResponseDTO login(LoginDTO loginDTO) {
		return bc.login(loginDTO);
	}
    
    

    /*
    public LoginDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca de login id[%d]" + id;
        
        log.info(logMsg);

        Login f = bc.obter(id.intValue());

        logMsg = "Busca de login finalizada";
        log.info(logMsg);

        //CONVERTER
        LoginToDTO converter = new LoginToDTO();
        LoginDTO loginDTO = converter.convert(f);
    	
        return loginDTO;
    }

    public ResultadoPaginadoDTO<LoginDTO> listar(LoginFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagem de Login Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Login> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<LoginDTO> loginsDTO = new ArrayList<LoginDTO>();
        for(Login f : listagem.getEntidades()){
        	LoginToDTO converter = new LoginToDTO();
        	LoginDTO loginDTO = converter.convert(f);
        	loginsDTO.add(loginDTO);
        }
		
		ResultadoPaginadoDTO<LoginDTO> responseDTO = new ResultadoPaginadoDTO<LoginDTO>(loginsDTO, new Pagina());

        logMsg = "Finalizando listagem de Login";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(LoginDTO dto) {
        String logMsg = "Iniciando a persistencia de Login";
        log.info(logMsg);

        //CONVERTER
        DTOtoLogin converter = new DTOtoLogin();
        Login login = converter.convert(dto);
        
		bc.persistir(login);

        logMsg = "Registro de Login persistido";
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

	public ResponseDTO inserir(LoginDTO dto) {
		String logMsg = "Iniciando a persistencia de Login";
        log.info(logMsg);

        //CONVERTER
        DTOtoLogin converter = new DTOtoLogin();
        Login login = converter.convert(dto);
        
		bc.persistir(login);

        logMsg = "Registro de Login persistido";
        log.info(logMsg);

        return new ResponseDTO();
	}
	*/
}
