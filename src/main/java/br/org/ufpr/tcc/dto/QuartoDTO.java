package br.org.ufpr.tcc.dto;

import java.util.List;

public class QuartoDTO {

	private Integer codQuarto;
	private Integer codFilial;
	private Integer idQuarto;
	private Integer codTipoQuarto;
	private String descricaoTipoQuarto;
	private Integer nrCamas;
    private String descricao;
    private Integer status;
    private FotoDTO foto;
    private String preco;
    
    private List<AtributoDTO> atributos;
    
    private List<ReservaDTO> reservas;
    
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<AtributoDTO> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoDTO> atributos) {
		this.atributos = atributos;
	}

	public List<ReservaDTO> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}

	public String getDescricaoTipoQuarto() {
		return descricaoTipoQuarto;
	}

	public void setDescricaoTipoQuarto(String descricaoTipoQuarto) {
		this.descricaoTipoQuarto = descricaoTipoQuarto;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}
	
}
