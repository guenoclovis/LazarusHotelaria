package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.UsuarioDAO;
import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class UsuarioBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private UsuarioDAO dao = new UsuarioDAO();

    public Usuario obter(Integer id) {
        return dao.obter(id);
    }

    public ResultadoPaginadoDTO<Usuario> listar(UsuarioFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Usuario BC";
        log.info(logMsg);

        List<Usuario> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Usuario>(lista, new Pagina());
    }

    public ResponseDTO persistir(Usuario usuario) {
        String descricaoOperacao = "";

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (usuario.getCodUsuario() == null) {
            log.info("Inicia a persistência de um novo usuario.");
            dao.inserir(usuario);
            log.info("Persistiu novo usuario na base de dados.");

        } else {
            log.info("Inicia a atualização do usuario [id=%d]" + usuario.getCodUsuario());

            try {
                //TODO: PENDENTE
                dao.alterar(usuario);
            } catch (Exception ex) {
                Logger.getLogger(UsuarioBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou usuario na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.excluir(id);
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir usuario com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
