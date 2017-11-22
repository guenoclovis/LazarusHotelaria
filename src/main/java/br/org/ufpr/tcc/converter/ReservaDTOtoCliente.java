package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.bc.ClienteBC;
import br.org.ufpr.tcc.dto.ClienteDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.util.DataUtil;

public class ReservaDTOtoCliente {
	
	public Cliente convert(ReservaDTO dto){
		Cliente cliente = new Cliente();

		if(dto.getCodCliente() != null){
			cliente = new ClienteBC().obter(dto.getCodCliente());
		}
		
		cliente.setCodCliente(dto.getCodCliente());
		cliente.setNome(dto.getNome());
		
		
		cliente.setTelefone1(dto.getTelefone());
		
		cliente.setEmail1(dto.getEmail());
		
		if(dto.getCpf() != null){
			cliente.setCpf(dto.getCpf().replace(".", "").replace("-", ""));
		}
		
		return cliente;
	}

}
