package br.org.ufpr.tcc.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.util.DataUtil;

public class QuartoValidator {

	public void validateAndThrow(Quarto quarto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<Quarto>> constraintViolations = validator.validate(quarto);
	    
	    List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    Mensagem mensagem = null;

	    //Validações feitas via annotations, direto na entidade
	    if(constraintViolations.size() > 0){
	    	for(ConstraintViolation<Quarto> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    }
	    
		//OUTRAS VALIDACOES
    	
    	if(!mensagens.isEmpty()){
    		throw new NegocioException(mensagens);
    	}   
	}
}
