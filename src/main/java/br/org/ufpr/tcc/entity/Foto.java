package br.org.ufpr.tcc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.ufpr.tcc.validator.validacoes.NaoNulo;
import br.org.ufpr.tcc.validator.validacoes.NaoVazio;

@Entity
@Table(name = "fotos", schema="public")
public class Foto {

	public static final String ID = "codFoto";
	public static final String PATH = "path";
	public static final String LEGENDA = "legenda";
	
	@Id
    @Column(name = "cod_foto")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FOTO")
    @SequenceGenerator(name = "SEQ_FOTO", schema="public", sequenceName = "fotos_cod_foto_seq", allocationSize = 1)
	private Integer codFoto;
	
	@NaoNulo(nomeCampo = "Path")
	@NaoVazio(nomeCampo = "Path")
	@Column(name = "path")
    private String path;
	
	@NaoNulo(nomeCampo = "Legenda")
	@NaoVazio(nomeCampo = "Legenda")
	@Column(name = "legenda")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFoto == null) ? 0 : codFoto.hashCode());
		result = prime * result + ((legenda == null) ? 0 : legenda.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		if (codFoto == null) {
			if (other.codFoto != null)
				return false;
		} else if (!codFoto.equals(other.codFoto))
			return false;
		if (legenda == null) {
			if (other.legenda != null)
				return false;
		} else if (!legenda.equals(other.legenda))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foto [codFoto=" + codFoto + ", path=" + path + ", legenda=" + legenda + "]";
	}
	
}
