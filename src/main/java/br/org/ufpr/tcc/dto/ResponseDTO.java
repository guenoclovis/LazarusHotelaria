package br.org.ufpr.tcc.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.entity.Mensagem;

public class ResponseDTO {

    private Long id;
	private String checkoutCode;
    private List<Mensagem> mensagens;
    
    public ResponseDTO() {
        this(null, new ArrayList<Mensagem>());
    }

    public ResponseDTO(Long id){
    	this.id = id;
    }
    
    public ResponseDTO(Long id, List<Mensagem> mensagens) {
        this.id = id;
        this.mensagens = mensagens;
    }

    public ResponseDTO(List<Mensagem> mensagens) {
        this(null, mensagens);
    }

    public String getCheckoutCode() {
		return checkoutCode;
	}

	public void setCheckoutCode(String checkoutCode) {
		this.checkoutCode = checkoutCode;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

}
