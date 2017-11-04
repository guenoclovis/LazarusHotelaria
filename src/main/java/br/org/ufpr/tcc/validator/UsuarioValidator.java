package br.org.ufpr.tcc.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;

public class UsuarioValidator {

	public void validateAndThrow(Usuario usuario) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
	    
	    if(constraintViolations.size() > 0){
	    	
	    	List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    	Mensagem mensagem = null;
	    	for(ConstraintViolation<Usuario> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    	
	    	
	    	//OUTRAS VALIDACOES
	    	
	    	throw new NegocioException(mensagens);
	    }
	    	    
	}

}
