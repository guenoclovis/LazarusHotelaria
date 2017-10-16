package br.org.ufpr.tcc.entity;

public class Mensagem {

	public static final String INFO = "INFO";  
	public static final String AVISO = "WARN";
	public static final String ERRO = "ERROR";
	
	private String severidade;
	
	private String texto;
	
	public Mensagem(String severidade, String texto) {
		this.severidade = severidade;
		this.texto = texto;
	}

	public String getSeveridade() {
		return severidade;
	}


	public void setSeveridade(String severidade) {
		this.severidade = severidade;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
