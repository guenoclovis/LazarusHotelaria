package br.org.ufpr.tcc.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;

public class FilialValidator {
	
	public void validateAndThrow(Filial filial) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<Filial>> constraintViolations = validator.validate(filial);
	    
	    if(constraintViolations.size() > 0){
	    	
	    	List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    	Mensagem mensagem = null;
	    	for(ConstraintViolation<Filial> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    	
	    	throw new NegocioException(mensagens);
	    }
	    
	    
	}

}
