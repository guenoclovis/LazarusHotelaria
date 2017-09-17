package br.org.ufpr.tcc.dto;

import br.org.ufpr.tcc.entity.Atributo;

public class AtributoFiltroDTO extends PesquisaPaginaDTO {

	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Atributo [id=" + id + ", nome=" + nome + "]";
	}
	
}
