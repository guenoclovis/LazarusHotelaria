package br.org.ufpr.tcc.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.org.ufpr.tcc.dto.AtributoDTO;
import br.org.ufpr.tcc.dto.AtributoFiltroDTO;
import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.facade.AtributoFacade;
import br.org.ufpr.tcc.facade.LoginFacade;



@Path("/login")
public class LoginREST {

	LoginFacade facade = new LoginFacade();
		
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response login(LoginDTO loginDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.login(loginDTO);        
        URI location = uriInfo.getRequestUriBuilder().path(String.valueOf(response.getId())).build();
        return Response.created(location).entity(response).build();
    }

}