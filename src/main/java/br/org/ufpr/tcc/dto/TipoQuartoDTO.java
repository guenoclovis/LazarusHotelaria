package br.org.ufpr.tcc.dto;

// import java.util.Date;

public class TipoQuartoDTO {

	private Integer codTipoQuarto;
    private String tipo;	
    private String nome;
    private String descricao;
    private String status;
    
	public Integer getCodTipoQuarto() {
		return codTipoQuarto;
	}
	public void setCodTipoQuarto(Integer codTipoQuarto) {
		this.codTipoQuarto = codTipoQuarto;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
