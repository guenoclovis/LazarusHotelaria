package br.org.ufpr.tcc.dto;

import java.util.List;

public class FilialDTO {

	private Integer codFilial;
    private String nome;
    private String email;
	private String descricao;
    private String exibirSite;
    private String status;
    
    private FotoDTO foto;
    
    private List<AtributoDTO> atributos;
    
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
	public FotoDTO getFoto() {
		return foto;
	}
	public void setFoto(FotoDTO foto) {
		this.foto = foto;
	}
	public List<AtributoDTO> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoDTO> atributos) {
		this.atributos = atributos;
	}
}
