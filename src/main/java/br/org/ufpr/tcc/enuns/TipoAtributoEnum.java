package br.org.ufpr.tcc.enuns;

public enum TipoAtributoEnum {
	
	HOTEL(1,"Hotel"),
	QUARTO(2,"Quarto");
	
	private Integer codigo;
	private String descricao;
	
	private TipoAtributoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
