package br.org.ufpr.tcc.dto;

public class FotoDTO {

	private Integer codFoto;
    private String nomeFotoOriginal;	
    private String nomeFotoMiniatura;
    private String legenda;
    
    private byte[] imagemOriginal;
    private byte[] imagemMiniatura;
    
	public byte[] getImagemOriginal() {
		return imagemOriginal;
	}
	public void setImagemOriginal(byte[] imagemOriginal) {
		this.imagemOriginal = imagemOriginal;
	}
	public byte[] getImagemMiniatura() {
		return imagemMiniatura;
	}
	public void setImagemMiniatura(byte[] imagemMiniatura) {
		this.imagemMiniatura = imagemMiniatura;
	}
	public Integer getCodFoto() {
		return codFoto;
	}
	public void setCodFoto(Integer codFoto) {
		this.codFoto = codFoto;
	}
	public String getNomeFotoOriginal() {
		return nomeFotoOriginal;
	}
	public void setNomeFotoOriginal(String nomeFotoOriginal) {
		this.nomeFotoOriginal = nomeFotoOriginal;
	}
	public String getNomeFotoMiniatura() {
		return nomeFotoMiniatura;
	}
	public void setNomeFotoMiniatura(String nomeFotoMiniatura) {
		this.nomeFotoMiniatura = nomeFotoMiniatura;
	}
	public String getLegenda() {
		return legenda;
	}
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
}
