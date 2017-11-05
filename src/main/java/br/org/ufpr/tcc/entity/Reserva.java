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
		public static final String NOME = "nome";
		
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
	
}
