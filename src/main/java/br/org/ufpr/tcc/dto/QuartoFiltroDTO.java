package br.org.ufpr.tcc.dto;

public class QuartoFiltroDTO extends PesquisaPaginaDTO {

	private Long id;
	
	private Long idHotel;
	
	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "QuartoFiltroDTO [id=" + id + ", idHotel=" + idHotel + "]";
	}

}
