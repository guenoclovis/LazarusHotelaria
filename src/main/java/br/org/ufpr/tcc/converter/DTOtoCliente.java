package br.org.ufpr.tcc.converter;

import java.util.Date;

import br.org.ufpr.tcc.bc.ClienteBC;
import br.org.ufpr.tcc.dto.ClienteDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoCliente {
	
	public Cliente convert(ClienteDTO dto){
		Cliente cliente = new Cliente();

		if(dto.getCodCliente() != null){
			cliente = new ClienteBC().obter(dto.getCodCliente());
		}
		
		cliente.setCodCliente(dto.getCodCliente());
		cliente.setNome(dto.getNome());
		cliente.setDtNasc(DataUtil.toDate(dto.getDtNasc().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
		cliente.setSexo(dto.getSexo());
		cliente.setNacionalidade(dto.getNacionalidade());
		cliente.setTelefone1(dto.getTelefone1());
		cliente.setTelefone2(dto.getTelefone2());
		cliente.setEmail1(dto.getEmail1());
		cliente.setEmail2(dto.getEmail2());
		cliente.setCpf(dto.getCpf().replace(".", "").replace("-", ""));
		cliente.setRg(dto.getRg());
		cliente.setPassaporte(dto.getPassaporte());
		cliente.setEndRua(dto.getEndRua());
		cliente.setEndNro(dto.getEndNro());
		cliente.setEndBairro(dto.getEndBairro());
		cliente.setEndCidade(dto.getEndCidade());
		cliente.setEndUf(dto.getEndUf());
		cliente.setEndCompl(dto.getEndCompl());
		cliente.setSenhaAcesso(dto.getSenhaAcesso());
		cliente.setStatus(dto.getStatus());

		return cliente;
	}

}
