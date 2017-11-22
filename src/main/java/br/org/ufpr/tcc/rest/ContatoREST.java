package br.org.ufpr.tcc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.org.ufpr.tcc.dto.DadosEmailDTO;
import br.org.ufpr.tcc.facade.ContatoFacade;

@Path("/contato")
public class ContatoREST {

	ContatoFacade contatoFacade = new ContatoFacade();
	
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response enviarEmail(DadosEmailDTO dadosDTO, @Context UriInfo uriInfo) {
        contatoFacade.enviarEmail(dadosDTO);        
        return Response.ok(dadosDTO).build();
    }

}