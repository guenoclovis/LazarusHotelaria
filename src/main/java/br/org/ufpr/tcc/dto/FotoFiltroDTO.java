package br.org.ufpr.tcc.dto;

public class FotoFiltroDTO extends PesquisaPaginaDTO {

	private Long codFoto;
	private String path;
	private String legenda;
	
	public Long getCodFoto() {
		return codFoto;
	}
	public void setCodFoto(Long codFoto) {
		this.codFoto = codFoto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLegenda() {
		return legenda;
	}
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	
}
