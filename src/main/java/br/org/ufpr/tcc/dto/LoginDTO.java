package br.org.ufpr.tcc.dto;

// import java.util.Date;

public class LoginDTO {

	private String usuario;
	private String senha;
	
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public String toString() {
		return "LoginDTO [usuario=" + usuario + ", senha=" + senha + "]";
	}
	
}
