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

import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
//import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.facade.ReservaFacade;
import br.org.ufpr.tcc.util.DataUtil;

@Path("/reserva")
public class ReservaREST {

	ReservaFacade facade = new ReservaFacade();
	
	@GET
    @Path("{codReserva}")
    @Produces("application/json") 
    public ReservaDTO obter(@PathParam("codReserva") Long id, @QueryParam("fields") String fields) {
		ReservaDTO reservaDTO = facade.obter(id, fields);
		return reservaDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<ReservaDTO> listar(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("codFilial") Integer codFilial, @QueryParam("dataEntrada") String dtEntrada,	        
	        @QueryParam("dataSaida") String dtSaida,
	        @QueryParam("fields") String fields) {

		ReservaFiltroDTO filtro = new ReservaFiltroDTO();
		filtro.setCodFilial(codFilial);
		filtro.setDataEntrada(DataUtil.converterData(dtEntrada));
		filtro.setDataSaida(DataUtil.converterData(dtSaida));

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
    public Response inserir(ReservaDTO reservaDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(reservaDTO);        
        URI location = uriInfo.getRequestUriBuilder().path(String.valueOf(response.getId())).build();
        return Response.created(location).entity(response).build();
    }

	@POST
	@Path("/solicitar")
    @Produces("application/json")
    @Consumes("application/json")
    public Response solicitarReserva(ReservaDTO reservaDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.solicitarReserva(reservaDTO);        
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
    @Path("{codReserva}")
    @Produces("application/json")    
    public Response remover(@PathParam("codReserva") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codReserva}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codReserva") Long id, ReservaDTO reservaDTO) {
    	reservaDTO.setCodReserva(id.intValue());
        ResponseDTO response = facade.persistir(reservaDTO);
        return Response.ok(response).build();
    }
	
}