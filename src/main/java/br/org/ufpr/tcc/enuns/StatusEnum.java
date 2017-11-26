package br.org.ufpr.tcc.enuns;

public enum StatusEnum {
	
	INATIVO(0,"Inativo"),
	ATIVO(1,"Ativo");
	
	private Integer codigo;
	private String descricao;
	
	private StatusEnum(Integer codigo, String descricao){
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
	
	public static StatusEnum fromCodigo(Integer codigo){
		for(StatusEnum se : values()){
			if(se.getCodigo().equals(codigo)){
				return se;
			}
		}
		return null;
	}
	
	public static StatusEnum fromDescricao(String descricao){
		for(StatusEnum se : values()){
			if(se.getDescricao().equals(descricao)){
				return se;
			}
		}
		return null;
	}

}
