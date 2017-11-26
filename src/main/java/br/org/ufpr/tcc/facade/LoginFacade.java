package br.org.ufpr.tcc.facade;

import java.util.logging.Logger;

import br.org.ufpr.tcc.bc.LoginBC;
import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;



public class LoginFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private LoginBC bc = new LoginBC();

	public ResponseDTO login(LoginDTO loginDTO) {
		return bc.login(loginDTO);
	}
}
