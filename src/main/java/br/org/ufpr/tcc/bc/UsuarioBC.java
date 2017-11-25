package br.org.ufpr.tcc.bc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.UsuarioDAO;
import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.exception.handler.AuthenticationException;
import br.org.ufpr.tcc.validator.FilialValidator;
import br.org.ufpr.tcc.validator.UsuarioValidator;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class UsuarioBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private UsuarioDAO dao = new UsuarioDAO();
    private UsuarioValidator validator = new UsuarioValidator();

    public Usuario obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Usuario> listar(UsuarioFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Usuario BC";
        log.info(logMsg);

        List<Usuario> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Usuario>(lista, new Pagina());
    }

    public ResponseDTO persistir(Usuario usuario) {

    	validator.validateAndThrow(usuario);
    	
    	
    	//Criptografar senha
    	try {
			CriptografiaBC criptoBC = new CriptografiaBC();
			byte[] bytesSenhaEncriptada = criptoBC.criptografar(usuario.getSenha().getBytes("utf-8"));
			usuario.setSenha(new String(bytesSenhaEncriptada));
    	} catch (Exception e) {
			e.printStackTrace();
			log.info("ERRO na criptografia");
			
			Mensagem m = new Mensagem(Mensagem.ERRO, "Erro no processo de encriptar/desencriptar");
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			throw new AuthenticationException(mensagens);
    	}
    	
        if (usuario.getCodUsuario() == null) {
            log.info("Inicia a persistência de um novo usuario.");
            dao.persistir(usuario);
            log.info("Persistiu novo usuario na base de dados.");

        } else {
            log.info("Inicia a atualização do usuario [id=%d]" + usuario.getCodUsuario());

            try {
                //TODO: PENDENTE
                dao.persistir(usuario);
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
    		dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir usuario com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
