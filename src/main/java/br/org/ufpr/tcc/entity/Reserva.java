package br.org.ufpr.tcc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "reserva", schema="public")

public class Reserva {

		public static final String ID = "codReserva";
		
		@Id
	    @Column(name = "cod_reserva")
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_RESERVA")
	    @SequenceGenerator(name = "SEQ_RESERVA", schema="public", sequenceName = "reserva_cod_reserva_seq", allocationSize = 1)
		private Integer codReserva;
		
		@NotNull	
		@Column(name = "dt_entrada")
	    private Date dtEntrada;
		
		@NotNull	
		@Column(name = "dt_saida")
	    private Date dtSaida;
		
		@NotNull	
		@Column(name = "dt_reserva")
	    private Date dtReserva;
		
		@Column(name = "preco")
	    private long preco;
		
		@Column(name = "status")
	    private char status;

		@Column(name = "cod_cliente")
	    private Integer codCliente;
		
		@Column(name = "cod_quarto")
	    private Integer codQuarto;

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

		public char getStatus() {
			return status;
		}

		public void setStatus(char status) {
			this.status = status;
		}

		public Integer getCodCliente() {
			return codCliente;
		}

		public void setCodCliente(Integer codCliente) {
			this.codCliente = codCliente;
		}

		public Integer getCodQuarto() {
			return codQuarto;
		}

		public void setCodQuarto(Integer codQuarto) {
			this.codQuarto = codQuarto;
		}
		
		
	
}
