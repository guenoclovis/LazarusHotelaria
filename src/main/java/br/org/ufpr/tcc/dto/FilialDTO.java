package br.org.ufpr.tcc.dto;

public class FilialDTO {

	private Integer codFilial;
    private String nome;
    private String email;
	private String descricao;
    private String exibirSite;
    private String status;
    
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
	public Integer getCodFilial() {
		return codFilial;
	}
	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getExibirSite() {
		return exibirSite;
	}
	public void setExibirSite(String exibirSite) {
		this.exibirSite = exibirSite;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
