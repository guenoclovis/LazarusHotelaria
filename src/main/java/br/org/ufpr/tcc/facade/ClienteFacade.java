package br.org.ufpr.tcc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.ClienteBC;
import br.org.ufpr.tcc.converter.ClienteToDTO;
import br.org.ufpr.tcc.converter.DTOtoCliente;
import br.org.ufpr.tcc.dto.ClienteDTO;
import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Pagina;

public class ClienteFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private ClienteBC bc = new ClienteBC();    

    public ClienteDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca do cliente id[%d]" + id;
        
        log.info(logMsg);

        Cliente c = bc.obter(id.intValue());

        logMsg = "Busca do cliente finalizada";
        log.info(logMsg);

        //CONVERTER
        ClienteToDTO converter = new ClienteToDTO();
        ClienteDTO clienteDTO = converter.convert(c);
    	
        return clienteDTO;
    }

    public ResultadoPaginadoDTO<ClienteDTO> listar(ClienteFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Cliente Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Cliente> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
        for(Cliente c : listagem.getEntidades()){
            ClienteToDTO converter = new ClienteToDTO();
            ClienteDTO clienteDTO = converter.convert(c);
            
            clientesDTO.add(clienteDTO);
        }
		
		ResultadoPaginadoDTO<ClienteDTO> responseDTO = new ResultadoPaginadoDTO<ClienteDTO>(clientesDTO, new Pagina());

        logMsg = "Finalizando listagem de Cliente";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(ClienteDTO dto) {
        String logMsg = "Iniciando a persistência de Cliente";
        log.info(logMsg);

        //CONVERTER
        DTOtoCliente converter = new DTOtoCliente();
        Cliente cliente = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(cliente);

        logMsg = "Registro de Cliente persistido";
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

	public ResponseDTO inserir(ClienteDTO dto) {
		String logMsg = "Iniciando a persistência de Cliente";
        log.info(logMsg);

        //CONVERTER
        DTOtoCliente converter = new DTOtoCliente();
        Cliente cliente = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(cliente);

        logMsg = "Registro de Cliente persistido";
        log.info(logMsg);

        return responseDTO;
	}

}
