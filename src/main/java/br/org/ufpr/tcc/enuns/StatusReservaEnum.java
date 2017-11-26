package br.org.ufpr.tcc.enuns;

public enum StatusReservaEnum {
	
	SOLICITADA(1,"Solicitada"),
	CONFIRMADA(2,"Confirmada"),
	EFETIVADA(3,"Efetivada");
	
	private Integer codigo;
	private String descricao;
	
	private StatusReservaEnum(Integer codigo, String descricao){
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
	
	public static StatusReservaEnum fromCodigo(Integer codigo){
		for(StatusReservaEnum se : values()){
			if(se.getCodigo().equals(codigo)){
				return se;
			}
		}
		return null;
	}
	
	public static StatusReservaEnum fromDescricao(String descricao){
		for(StatusReservaEnum se : values()){
			if(se.getDescricao().equals(descricao)){
				return se;
			}
		}
		return null;
	}

}
