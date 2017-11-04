package br.org.ufpr.tcc.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.TipoQuarto;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;

public class TipoQuartoValidator {


	public void validateAndThrow(TipoQuarto tipoQuarto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<TipoQuarto>> constraintViolations = validator.validate(tipoQuarto);
	    
	    if(constraintViolations.size() > 0){
	    	
	    	List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    	Mensagem mensagem = null;
	    	for(ConstraintViolation<TipoQuarto> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    	
	    	//OUTRAS VALIDACOES
	    	
	    	throw new NegocioException(mensagens);
	    }
	       
	}
	
}
