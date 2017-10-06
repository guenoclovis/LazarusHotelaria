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

import br.org.ufpr.tcc.dto.UsuarioDTO;
import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
//import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.facade.UsuarioFacade;

@Path("/usuario")
public class UsuarioREST {

	UsuarioFacade facade = new UsuarioFacade();
	
	@GET
    @Path("{codUsuario}")
    @Produces("application/json") 
    public UsuarioDTO obter(@PathParam("codUsuario") Long id, @QueryParam("fields") String fields) {
		UsuarioDTO usuarioDTO = facade.obter(id, fields);
		return usuarioDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<UsuarioDTO> listar(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("nome") String cpf, @QueryParam("ativo") Boolean ativo,	        
	        @QueryParam("fields") String fields) {

		UsuarioFiltroDTO filtro = new UsuarioFiltroDTO();
        

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
    public Response inserir(UsuarioDTO usuarioDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(usuarioDTO);        
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
    @Path("{codUsuario}")
    @Produces("application/json")    
    public Response remover(@PathParam("codUsuario") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codUsuario}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codUsuario") Long id, UsuarioDTO usuarioDTO) {
    	usuarioDTO.setCodUsuario(id.intValue());
        ResponseDTO response = facade.persistir(usuarioDTO);
        return Response.ok(response).build();
    }
	
}