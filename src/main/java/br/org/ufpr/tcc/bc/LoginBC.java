package br.org.ufpr.tcc.bc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.UsuarioDAO;
import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.exception.handler.AuthException;
import br.org.ufpr.tcc.exception.handler.CtxSeguranca;

public class LoginBC {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	public ResponseDTO login(LoginDTO loginDTO) {
		CriptografiaBC criptoBC = new CriptografiaBC();

		Usuario usuarioEncontrado = null;

		try {
			
			String senhaEncriptada = criptoBC.criptografarParaBD(loginDTO.getSenha());			
			loginDTO.setSenha(senhaEncriptada);

			UsuarioDAO usuarioDAO = UsuarioDAO.getDAO();

			usuarioEncontrado = usuarioDAO.obterParaLogin(loginDTO);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("ERRO na criptografia");

			Mensagem m = new Mensagem(Mensagem.ERRO, "Erro no processo de encriptar/desencriptar");
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			mensagens.add(m);
			throw new AuthException(mensagens);
		}

		if (usuarioEncontrado == null) {
			Mensagem m = new Mensagem(Mensagem.ERRO, "Usuario ou Senha invalido(s)");
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			mensagens.add(m);
			throw new AuthException(mensagens);
		}

		CtxSeguranca.getContext().setPrincipal(usuarioEncontrado);
		

		return new ResponseDTO();
	}

}
