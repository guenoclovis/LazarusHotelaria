package br.org.ufpr.tcc.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.util.DataUtil;

public class ClienteValidator {

	public void validateAndThrow(Cliente cliente) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    javax.validation.Validator validator = factory.getValidator();
	    
	    Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
	    
	    List<Mensagem> mensagens = new ArrayList<Mensagem>();
	    Mensagem mensagem = null;

	    //Validações feitas via annotations, direto na entidade
	    if(constraintViolations.size() > 0){
	    	for(ConstraintViolation<Cliente> violacao : constraintViolations){
	    		mensagem = new Mensagem(Mensagem.ERRO, violacao.getMessage());
	    		
	    		mensagens.add(mensagem);
	    	}
	    }
	    
		//OUTRAS VALIDACOES
    	
    	//data nascimento <= data_atual
    	if(cliente.getDtNasc() != null && DataUtil.isDataFuturo(cliente.getDtNasc())){
    		mensagem = new Mensagem(Mensagem.ERRO, "Data de nascimento deve ser menor ou igual a data atual!");
    		
    		mensagens.add(mensagem);
    	}
	    
    	if(!mensagens.isEmpty()){
    		throw new NegocioException(mensagens);
    	}
	    
	}

}
