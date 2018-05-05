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

import br.org.ufpr.tcc.dto.QuartoDTO;
import br.org.ufpr.tcc.dto.QuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.facade.FilialFacade;
import br.org.ufpr.tcc.facade.FotoFacade;
//import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.facade.QuartoFacade;
import br.org.ufpr.tcc.util.DataUtil;

@Path("/quarto")
public class QuartoREST {

	QuartoFacade facade = new QuartoFacade();
	FotoFacade fotoFacade = new FotoFacade();
	
	@GET
    @Path("{codQuarto}")
    @Produces("application/json") 
    public QuartoDTO obter(@PathParam("codQuarto") Long id, @QueryParam("fields") String fields) {
		QuartoDTO quartoDTO = facade.obter(id, fields);
		return quartoDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<QuartoDTO> listar(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("codFilial") Long codFilial, @QueryParam("dataEntrada") String dataEntrada,	        
	        @QueryParam("dataSaida") String dataSaida) {

		QuartoFiltroDTO filtro = new QuartoFiltroDTO();
		filtro.setCodFilial(codFilial);
		if(dataEntrada != null){
			filtro.setDataEntrada(DataUtil.toDate(dataEntrada.replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));			
		}
		if(dataSaida != null){
			filtro.setDataSaida(DataUtil.toDate(dataSaida.replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));			
		}

        // Paginação
        if (pageSize != 0) {
            filtro.getPagina().setPageSize(pageSize);
        }
        if (currentPage != 0) {
            filtro.getPagina().setCurrentPage(currentPage);
        }

        return facade.listar(filtro, null);
    }
	
	@GET
	@Path("/semreserva")
    @Produces("application/json")
	public ResultadoPaginadoDTO<QuartoDTO> listarSemReserva(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("codFilial") Long codFilial, @QueryParam("dataEntrada") String dataEntrada,	        
	        @QueryParam("dataSaida") String dataSaida) {

		QuartoFiltroDTO filtro = new QuartoFiltroDTO();
		filtro.setCodFilial(codFilial);
		if(dataEntrada != null){
			filtro.setDataEntrada(DataUtil.toDate(dataEntrada.replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));			
		}
		if(dataSaida != null){
			filtro.setDataSaida(DataUtil.toDate(dataSaida.replace("T", " ").replace("Z", ""), "yyyy-MM-dd HH:mm:ss.SSS"));			
		}

        // Paginação
        if (pageSize != 0) {
            filtro.getPagina().setPageSize(pageSize);
        }
        if (currentPage != 0) {
            filtro.getPagina().setCurrentPage(currentPage);
        }
        
        return facade.listarSemReserva(filtro, null);
    }
	
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response inserir(QuartoDTO quartoDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(quartoDTO);        
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
    @Path("{codQuarto}")
    @Produces("application/json")    
    public Response remover(@PathParam("codQuarto") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codQuarto}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codQuarto") Long id, QuartoDTO quartoDTO) {
    	quartoDTO.setCodQuarto(id.intValue());
        ResponseDTO response = facade.persistir(quartoDTO);
        return Response.ok(response).build();
    }
	
}