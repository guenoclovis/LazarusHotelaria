package br.org.ufpr.tcc.dto;

import java.util.Date;

public class ShowcaseDTO {

	private Integer codShowcase;
    private String nome;
    private String email;
	private String descricao;
    private String exibirSite;
    private char status;
    
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
	public Integer getCodShowcase() {
		return codShowcase;
	}
	public void setCodShowcase(Integer codShowcase) {
		this.codShowcase = codShowcase;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
    
	    
	
}
