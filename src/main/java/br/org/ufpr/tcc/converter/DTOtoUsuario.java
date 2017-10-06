package br.org.ufpr.tcc.converter;

import java.util.Date;

import br.org.ufpr.tcc.bc.UsuarioBC;
import br.org.ufpr.tcc.dto.UsuarioDTO;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.util.DataUtil;

public class DTOtoUsuario {
	
	public Usuario convert(UsuarioDTO dto){
		Usuario usuario = new Usuario();

		if(dto.getCodUsuario() != null){
			usuario = new UsuarioBC().obter(dto.getCodUsuario());
		}
		
		usuario.setCodUsuario(dto.getCodUsuario());
		usuario.setNome(dto.getNome());
		usuario.setAtivo(dto.getAtivo());
		usuario.setDtNasc(DataUtil.toDate(dto.getDtNasc().replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));
		usuario.setSexo(dto.getSexo());
		usuario.setNacionalidade(dto.getNacionalidade());
		usuario.setPerfil(dto.getPerfil());
		usuario.setTelefone(dto.getTelefone());
		usuario.setEmail(dto.getEmail());
		usuario.setCpf(dto.getCpf().replace(".", "").replace("-", ""));
		usuario.setRg(dto.getRg());
		usuario.setPassaporte(dto.getPassaporte());
		usuario.setEndRua(dto.getEndRua());
		usuario.setEndNro(dto.getEndNro());
		usuario.setEndBairro(dto.getEndBairro());
		usuario.setEndCidade(dto.getEndCidade());
		usuario.setEndUf(dto.getEndUf());
		usuario.setEndCompl(dto.getEndCompl());
		usuario.setSenha(dto.getSenha());
		usuario.setStatus(dto.getStatus());

		return usuario;
	}

}
