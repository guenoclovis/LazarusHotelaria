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

import br.org.ufpr.tcc.dto.ShowcaseDTO;
import br.org.ufpr.tcc.dto.ShowcaseFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.facade.ShowcaseFacade;



@Path("/showcase")
public class ShowcaseREST {

	ShowcaseFacade facade = new ShowcaseFacade();
	
	@GET
    @Path("{codShowcase}")
    @Produces("application/json") 
    public ShowcaseDTO obter(@PathParam("codShowcase") Long id, @QueryParam("fields") String fields) {
		ShowcaseDTO showcaseDTO = facade.obter(id, fields);
		return showcaseDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<ShowcaseDTO> listar(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("nome") String cpf, @QueryParam("ativo") Boolean ativo,	        
	        @QueryParam("fields") String fields) {

        ShowcaseFiltroDTO filtro = new ShowcaseFiltroDTO();
        

        // Paginação
        if (pageSize != 0) {
            filtro.getPagina().setPageSize(pageSize);
        }
        if (currentPage != 0) {
            filtro.getPagina().setCurrentPage(currentPage);
        }

        return facade.listar(filtro, fields);
    }
	
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response inserir(ShowcaseDTO showcaseDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(showcaseDTO);        
        URI location = uriInfo.getRequestUriBuilder().path(String.valueOf(response.getId())).build();
        return Response.created(location).entity(response).build();
    }

    @DELETE
    @Produces("application/json")    
    public Response remover(@QueryParam("ids") List<Long> ids) {
        ResponseDTO response = facade.remover(ids.toArray(new Long[ids.size()]));
        return Response.ok(response).build();
    }
    
    @DELETE
    @Path("{codShowcase}")
    @Produces("application/json")    
    public Response remover(@PathParam("codShowcase") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codShowcase}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codShowcase") Long id, ShowcaseDTO showcaseDTO) {
    	showcaseDTO.setCodShowcase(Integer.valueOf(id.intValue()));
        ResponseDTO response = facade.persistir(showcaseDTO);
        return Response.ok(response).build();
    }
	
}