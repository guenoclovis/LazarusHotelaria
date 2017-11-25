package br.org.ufpr.tcc.exception.handler;

import java.util.Date;

import br.org.ufpr.tcc.entity.Usuario;

public class LazarusPrincipal {
	
	private Usuario usuario;
	
	private Date dataHoraLogin;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataHoraLogin() {
		return dataHoraLogin;
	}

	public void setDataHoraLogin(Date dataHoraLogin) {
		this.dataHoraLogin = dataHoraLogin;
	}
	
}
