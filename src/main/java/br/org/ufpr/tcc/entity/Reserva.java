package br.org.ufpr.tcc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.org.ufpr.tcc.validator.validacoes.NaoNulo;

@Entity
@Table(name = "reserva", schema="public")

public class Reserva {

		public static final String ID = "codReserva";
		public static final String DATA_ENTRADA = "dtEntrada";
		public static final String DATA_SAIDA = "dtSaida";
		public static final String QUARTO = "quarto";
		
		@Id
	    @Column(name = "cod_reserva")
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_RESERVA")
	    @SequenceGenerator(name = "SEQ_RESERVA", schema="public", sequenceName = "reserva_cod_reserva_seq", allocationSize = 1)
		private Integer codReserva;
		
		@NaoNulo(nomeCampo = "Data de Entrada")	
		@Column(name = "dt_entrada")
	    private Date dtEntrada;
		
		@NaoNulo(nomeCampo = "Data de Saida")	
		@Column(name = "dt_saida")
	    private Date dtSaida;
		
		@NotNull	
		@Column(name = "dt_reserva")
	    private Date dtReserva;
		
		@Column(name = "preco")
	    private long preco;
		
		@Column(name = "status")
	    private Integer status;

		@Column(name = "cod_cliente")
	    private Integer codCliente;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "COD_QUARTO", nullable = false)
		private Quarto quarto;

		public Integer getCodReserva() {
			return codReserva;
		}

		public void setCodReserva(Integer codReserva) {
			this.codReserva = codReserva;
		}

		public Date getDtEntrada() {
			return dtEntrada;
		}

		public void setDtEntrada(Date dtEntrada) {
			this.dtEntrada = dtEntrada;
		}

		public Date getDtSaida() {
			return dtSaida;
		}

		public void setDtSaida(Date dtSaida) {
			this.dtSaida = dtSaida;
		}

		public Date getDtReserva() {
			return dtReserva;
		}

		public void setDtReserva(Date dtReserva) {
			this.dtReserva = dtReserva;
		}

		public long getPreco() {
			return preco;
		}

		public void setPreco(long preco) {
			this.preco = preco;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getCodCliente() {
			return codCliente;
		}

		public void setCodCliente(Integer codCliente) {
			this.codCliente = codCliente;
		}

		public Quarto getQuarto() {
			return quarto;
		}

		public void setQuarto(Quarto quarto) {
			this.quarto = quarto;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
			result = prime * result + ((codReserva == null) ? 0 : codReserva.hashCode());
			result = prime * result + ((dtEntrada == null) ? 0 : dtEntrada.hashCode());
			result = prime * result + ((dtReserva == null) ? 0 : dtReserva.hashCode());
			result = prime * result + ((dtSaida == null) ? 0 : dtSaida.hashCode());
			result = prime * result + (int) (preco ^ (preco >>> 32));
			result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
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
			Reserva other = (Reserva) obj;
			if (codCliente == null) {
				if (other.codCliente != null)
					return false;
			} else if (!codCliente.equals(other.codCliente))
				return false;
			if (codReserva == null) {
				if (other.codReserva != null)
					return false;
			} else if (!codReserva.equals(other.codReserva))
				return false;
			if (dtEntrada == null) {
				if (other.dtEntrada != null)
					return false;
			} else if (!dtEntrada.equals(other.dtEntrada))
				return false;
			if (dtReserva == null) {
				if (other.dtReserva != null)
					return false;
			} else if (!dtReserva.equals(other.dtReserva))
				return false;
			if (dtSaida == null) {
				if (other.dtSaida != null)
					return false;
			} else if (!dtSaida.equals(other.dtSaida))
				return false;
			if (preco != other.preco)
				return false;
			if (quarto == null) {
				if (other.quarto != null)
					return false;
			} else if (!quarto.equals(other.quarto))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Reserva [codReserva=" + codReserva + ", dtEntrada=" + dtEntrada + ", dtSaida=" + dtSaida
					+ ", dtReserva=" + dtReserva + ", preco=" + preco + ", status=" + status + ", codCliente="
					+ codCliente + ", quarto=" + quarto + "]";
		}


}
