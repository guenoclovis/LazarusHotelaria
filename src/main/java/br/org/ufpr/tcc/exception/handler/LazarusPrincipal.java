package br.org.ufpr.tcc.exception.handler;

import java.util.Date;

import br.org.ufpr.tcc.entity.Usuario;

public class LazarusPrincipal {
	
	private String nomeUsuario;
	private String perfil;
	
	private Date dataHoraLogin;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataHoraLogin() {
		return dataHoraLogin;
	}

	public void setDataHoraLogin(Date dataHoraLogin) {
		this.dataHoraLogin = dataHoraLogin;
	}
	
}
