package br.org.ufpr.tcc.converter;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Checkout;



public class DTOtoCheckout {
	
	public Checkout convert(ReservaDTO dto, Long idReserva){
		Checkout checkout = new Checkout();

		checkout.setCurrency(Currency.BRL);
		checkout.setExtraAmount(BigDecimal.ONE);
		checkout.setEmail(dto.getEmail());
		checkout.setNome(dto.getNome());
		checkout.setCpf(dto.getCpf().replace(".", "").replace("-", ""));
		checkout.setAreaCode(dto.getTelefone().replace("(", "").replace(")", "").replace("-", "").substring(0,2));
		checkout.setNumber(dto.getTelefone().replace("(", "").replace(")", "").replace("-", "").substring(2));
		checkout.setIdReserva(idReserva);
		checkout.setDescription(dto.getStatusDescricao());
		
		BigDecimal d = new BigDecimal(dto.getPreco());
		checkout.setAmount(d);
		checkout.setQuantity(1);
		
		return checkout;
	}
	
}
