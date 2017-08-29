package br.org.ufpr.tcc.dto;

import java.util.List;

import br.org.ufpr.tcc.entity.Mensagem;

public class ResponseDTO {

    private Long id;

    private List<Mensagem> mensagens;

    public ResponseDTO() {
        this(null, null);
    }

    public ResponseDTO(Long id, List<Mensagem> mensagens) {
        this.id = id;
        this.mensagens = mensagens;
    }

    public ResponseDTO(List<Mensagem> mensagens) {
        this(null, mensagens);
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
