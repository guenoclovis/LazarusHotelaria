package br.org.ufpr.tcc.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Usuario;

public class CtxSeguranca {

	private static CtxSeguranca ctx = null;
	
	private static LazarusPrincipal principal = null;
	
	
	public static CtxSeguranca getContext(){
		if(ctx == null){
			ctx = new CtxSeguranca();
		}
		return ctx;
	}
	
	public LazarusPrincipal getPrincipal(){
		if(principal == null){
			Mensagem m = new Mensagem(Mensagem.ERRO, "Faça login");
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			throw new AuthException(mensagens);
		} else {
			return principal;
		}
	}
	
	public void setPrincipal(Usuario usuario){
		LazarusPrincipal lp = new LazarusPrincipal();
		lp.setNomeUsuario(usuario.getNome());
		lp.setPerfil(usuario.getPerfil());
		lp.setDataHoraLogin(new Date());
		
		principal = lp;
	}
	
	public void removePrincipal(){
		principal = null;
	}
	
}
