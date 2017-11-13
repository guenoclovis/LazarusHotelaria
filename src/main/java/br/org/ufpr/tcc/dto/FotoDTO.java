package br.org.ufpr.tcc.dto;

public class FotoDTO {

	private Integer codFoto;
    private String path;	
    private String legenda;
    
	public Integer getCodFoto() {
		return codFoto;
	}
	public void setCodFoto(Integer codFoto) {
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
