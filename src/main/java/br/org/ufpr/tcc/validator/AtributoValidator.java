package br.org.ufpr.tcc.validator;

import java.util.ArrayList;


/*

 mvn install:install-file -Dfile=pagseguro-api-2.5.6.jar -DgroupId=br.com.uol.pagseguro -DartifactId=pagseguro -Dversion=2.5.2 -Dpackaging=jar
 
 */


import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;

public class AtributoValidator {
	
	public void validateAndThrow(Atributo atributo) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<Atributo>> constraintViolations = validator.validate(atributo);
	    
	    if(constraintViolations.size() > 0){
	    	
	    	List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    	Mensagem mensagem = null;
	    	for(ConstraintViolation<Atributo> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    	
	    	
	    	//OUTRAS VALIDACOES
	    	
	    	throw new NegocioException(mensagens);
	    }
	    
	    
	}

}
