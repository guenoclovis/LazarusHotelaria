package br.org.ufpr.tcc.dto;

import br.org.ufpr.tcc.entity.Pagina;

public class PesquisaPaginaDTO {

    private static final long serialVersionUID = 1L;

    private Pagina pagina;
    
    public PesquisaPaginaDTO() {
        this(0, 10);
    }

    public PesquisaPaginaDTO(int currentPage, int pageSize) {
        this.pagina = new Pagina();
        pagina.setCurrentPage(currentPage);
        pagina.setPageSize(pageSize);
    }

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }

}