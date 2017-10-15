package br.org.ufpr.tcc.exception.handler;

import java.util.List;

import br.org.ufpr.tcc.entity.Mensagem;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<Mensagem> mensagens;

    public NegocioException(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	
}
