package br.org.ufpr.tcc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.exception.handler.CtxSeguranca;
import br.org.ufpr.tcc.exception.handler.LazarusPrincipal;
import br.org.ufpr.tcc.facade.LoginFacade;



@Path("/login")
public class LoginREST {

	LoginFacade facade = new LoginFacade();
		
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public LazarusPrincipal login(LoginDTO loginDTO, @Context UriInfo uriInfo) {
        facade.login(loginDTO);        
        return CtxSeguranca.getContext().getPrincipal();
    }
	
	@POST
	@Path("/logout")
    @Consumes("application/json")
    public void logout() {                
        CtxSeguranca.getContext().removePrincipal();
    }

}