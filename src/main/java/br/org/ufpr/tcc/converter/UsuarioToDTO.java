package br.org.ufpr.tcc.converter;

import br.org.ufpr.tcc.dto.UsuarioDTO;
import br.org.ufpr.tcc.entity.Usuario;

public class UsuarioToDTO {

	public UsuarioDTO convert(Usuario usuario){
		UsuarioDTO dto = new UsuarioDTO();

		dto.setCodUsuario(usuario.getCodUsuario());
		dto.setNome(usuario.getNome());
		dto.setAtivo(usuario.getAtivo());
		//dto.setDtNasc(usuario.getDtNasc());
		dto.setSexo(usuario.getSexo());
		dto.setNacionalidade(usuario.getNacionalidade());
		dto.setPerfil(usuario.getPerfil());
		dto.setTelefone(usuario.getTelefone());
		dto.setEmail(usuario.getEmail());
		dto.setCpf(usuario.getCpf());
		dto.setRg(usuario.getRg());
		dto.setPassaporte(usuario.getPassaporte());
		dto.setEndRua(usuario.getEndRua());
		dto.setEndNro(usuario.getEndNro());
		dto.setEndBairro(usuario.getEndBairro());
		dto.setEndCidade(usuario.getEndCidade());
		dto.setEndUf(usuario.getEndUf());
		dto.setEndCompl(usuario.getEndCompl());
		dto.setLogin(usuario.getLogin());
		dto.setSenha(usuario.getSenha());
		dto.setStatus(usuario.getStatus());

		return dto;
	}

}
