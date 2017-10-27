package br.org.ufpr.tcc.entity;

public class Mensagem {

	public static final String INFO = "INFO";  
	public static final String AVISO = "WARN";
	public static final String ERRO = "ERROR";
	
	private String severity;
	
	private String message;
	
	public Mensagem(String severidade, String texto) {
		this.severity = severidade;
		this.message = texto;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
