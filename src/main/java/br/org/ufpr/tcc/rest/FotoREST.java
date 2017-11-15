package br.org.ufpr.tcc.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.org.ufpr.tcc.bc.FotoBC;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.facade.FotoFacade;
import br.org.ufpr.tcc.util.Constantes;
import br.org.ufpr.tcc.util.ImageUtil;

@Path("/foto")
public class FotoREST {

	FotoFacade facade = new FotoFacade();
	
	@GET
    @Path("{codFoto}")
    @Produces("application/json") 
    public FotoDTO obter(@PathParam("codFoto") Long id, @QueryParam("fields") String fields) {
		FotoDTO atributoDTO = facade.obter(id, fields);
		return atributoDTO;
    }
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<FotoDTO> listar(@QueryParam("currentpage") int currentPage,			
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("nome") String cpf, @QueryParam("ativo") Boolean ativo,	        
	        @QueryParam("fields") String fields) {

        FotoFiltroDTO filtro = new FotoFiltroDTO();
        

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
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input, @QueryParam("nomeArquivo") String nomeArquivo,
			@QueryParam("legenda") String legenda) {

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		FotoDTO fotoSalvaDTO = facade.gravarFotoPastaTemporaria(inputParts, nomeArquivo, "");
		
		return Response.ok(fotoSalvaDTO).build();

	}

    @DELETE
    @Produces("application/json")    
    public Response remover(@QueryParam("ids") List<Long> ids) {
        ResponseDTO response = facade.remover(ids.toArray(new Long[ids.size()]));
        return Response.ok(response).build();
    }
    
    @DELETE
    @Path("{codFoto}")
    @Produces("application/json")    
    public Response remover(@PathParam("codFoto") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codFoto}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codFoto") Long id, FotoDTO atributoDTO) {
    	atributoDTO.setCodFoto(Integer.valueOf(id.intValue()));
        ResponseDTO response = facade.persistir(atributoDTO);
        facade.obter(response.getId(), null);
        return Response.ok(response).build();
    }
	
}