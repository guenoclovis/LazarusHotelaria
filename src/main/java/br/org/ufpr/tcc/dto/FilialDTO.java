package br.org.ufpr.tcc.dto;

import java.util.List;

public class FilialDTO {

	private Integer codFilial;
    private String nome;
    private String email;
	private String descricao;
    private Integer exibirSite;
    private Integer status;
    
    private FotoDTO foto;
    
    private List<AtributoDTO> atributos;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getExibirSite() {
		return exibirSite;
	}

	public void setExibirSite(Integer exibirSite) {
		this.exibirSite = exibirSite;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
