package br.org.ufpr.tcc.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class ResultadoPaginadoDTO<T> {
    private List<T> entidades;

    private Pagina pagina;

    private List<Mensagem> mensagens;

    public ResultadoPaginadoDTO(List<T> entidades, Pagina pagina) {
        this(entidades, pagina, new ArrayList());
    }

    public ResultadoPaginadoDTO(List<T> entidades, Pagina pagina, List<Mensagem> mensagens) {
        super();
        this.entidades = entidades;
        this.pagina = pagina;
        this.mensagens = mensagens;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public List<T> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }

    public boolean isEmpty() {
        return entidades != null && entidades.isEmpty();
    }
}
