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

import br.org.ufpr.tcc.dto.TipoQuartoDTO;
import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.facade.TipoQuartoFacade;



@Path("/tipoquarto")
public class TipoQuartoREST {

	TipoQuartoFacade facade = new TipoQuartoFacade();
	
	@GET
    @Path("{codTipoQuarto}")
    @Produces("application/json") 
    public TipoQuartoDTO obter(@PathParam("codTipoQuarto") Long id, @QueryParam("fields") String fields) {
		TipoQuartoDTO tipoquartoDTO = facade.obter(id, fields);
		return tipoquartoDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<TipoQuartoDTO> listar(@QueryParam("currentpage") int currentPage,			
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("nome") String nome) {

        TipoQuartoFiltroDTO filtro = new TipoQuartoFiltroDTO();
        filtro.setNome(nome);
        

        // Paginação
        if (pageSize != 0) {
            filtro.getPagina().setPageSize(pageSize);
        }
        if (currentPage != 0) {
            filtro.getPagina().setCurrentPage(currentPage);
        }

        return facade.listar(filtro, null);
    }
	
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response inserir(TipoQuartoDTO tipoquartoDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(tipoquartoDTO);        
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
    @Path("{codTipoQuarto}")
    @Produces("application/json")    
    public Response remover(@PathParam("codTipoQuarto") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codTipoQuarto}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codTipoQuarto") Long id, TipoQuartoDTO tipoquartoDTO) {
    	tipoquartoDTO.setCodTipoQuarto(Integer.valueOf(id.intValue()));
        ResponseDTO response = facade.persistir(tipoquartoDTO);
        return Response.ok(response).build();
    }
	
}