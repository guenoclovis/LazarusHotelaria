package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.ClienteDAO;
import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class ClienteBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private ClienteDAO dao = new ClienteDAO();

    public Cliente obter(Integer id) {
        return dao.obter(id);
    }

    public ResultadoPaginadoDTO<Cliente> listar(ClienteFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Cliente BC";
        log.info(logMsg);

        List<Cliente> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Cliente>(lista, new Pagina());
    }

    public ResponseDTO persistir(Cliente cliente) {
        String descricaoOperacao = "";

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (cliente.getCodCliente() == null) {
            log.info("Inicia a persistência de um novo cliente.");
            dao.inserir(cliente);
            log.info("Persistiu novo cliente na base de dados.");

        } else {
            log.info("Inicia a atualização do cliente [id=%d]" + cliente.getCodCliente());

            try {
                //TODO: PENDENTE
                dao.alterar(cliente);
            } catch (Exception ex) {
                Logger.getLogger(ClienteBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou cliente na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.excluir(id);
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir cliente com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
