package br.org.ufpr.tcc.dto;

// import java.util.Date;

public class AtributoDTO {

	private Integer codAtributo;
    private String tipo;	
    private String nome;
    private String descricao;
    private char status;
    
	public Integer getCodAtributo() {
		return codAtributo;
	}
	public void setCodAtributo(Integer codAtributo) {
		this.codAtributo = codAtributo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
        
}
