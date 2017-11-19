package br.org.ufpr.tcc.dto;

import java.util.List;

public class QuartoDTO {

	private Integer codQuarto;
	private Integer codFilial;
	private Integer idQuarto;
	private Integer codTipoQuarto;
	private Integer nrCamas;
    private String descricao;
    private char status;
    private FotoDTO foto;
    
    private List<AtributoDTO> atributos;
    
	public Integer getCodQuarto() {
		return codQuarto;
	}
	
	public Integer getCodFilial() {
		return codFilial;
	}
	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}
	public FotoDTO getFoto() {
		return foto;
	}
	public void setFoto(FotoDTO foto) {
		this.foto = foto;
	}
	
	public void setCodQuarto(Integer codQuarto) {
		this.codQuarto = codQuarto;
	}
	public Integer getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(Integer idQuarto) {
		this.idQuarto = idQuarto;
	}
	public Integer getCodTipoQuarto() {
		return codTipoQuarto;
	}
	public void setCodTipoQuarto(Integer codTipoQuarto) {
		this.codTipoQuarto = codTipoQuarto;
	}
	public Integer getNrCamas() {
		return nrCamas;
	}
	public void setNrCamas(Integer nrCamas) {
		this.nrCamas = nrCamas;
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
	public List<AtributoDTO> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoDTO> atributos) {
		this.atributos = atributos;
	}
	
}
