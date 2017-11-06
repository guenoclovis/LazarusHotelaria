package br.org.ufpr.tcc.dto;

public class ReservaFiltroDTO extends PesquisaPaginaDTO {

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + "]";
	}
}
