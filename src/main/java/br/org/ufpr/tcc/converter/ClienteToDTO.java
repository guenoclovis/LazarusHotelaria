package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.ClienteDTO;
import br.org.ufpr.tcc.entity.Cliente;

public class ClienteToDTO {

	public ClienteDTO convert(Cliente cliente){
		ClienteDTO dto = new ClienteDTO();

		dto.setCodCliente(cliente.getCodCliente());
		dto.setNome(cliente.getNome());
		//dto.setDtNasc(cliente.getDtNasc());
		dto.setSexo(cliente.getSexo());
		dto.setNacionalidade(cliente.getNacionalidade());
		dto.setTelefone1(cliente.getTelefone1());
		dto.setTelefone2(cliente.getTelefone2());
		dto.setEmail1(cliente.getEmail1());
		dto.setEmail2(cliente.getEmail2());
		dto.setCpf(cliente.getCpf());
		dto.setRg(cliente.getRg());
		dto.setPassaporte(cliente.getPassaporte());
		dto.setEndRua(cliente.getEndRua());
		dto.setEndNro(cliente.getEndNro());
		dto.setEndBairro(cliente.getEndBairro());
		dto.setEndCidade(cliente.getEndCidade());
		dto.setEndUf(cliente.getEndUf());
		dto.setEndCompl(cliente.getEndCompl());
		dto.setSenhaAcesso(cliente.getSenhaAcesso());
		dto.setStatus(cliente.getStatus());

		return dto;
	}

}
