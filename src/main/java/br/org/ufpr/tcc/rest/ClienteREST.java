package br.org.ufpr.tcc.rest;

import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.org.ufpr.tcc.dto.ClienteDTO;
import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.exception.handler.NegocioException;
//import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.facade.ClienteFacade;
import br.org.ufpr.tcc.util.Constantes;
import br.org.ufpr.tcc.util.reports.FileDTO;
import br.org.ufpr.tcc.util.reports.JasperTest;
import br.org.ufpr.tcc.util.reports.Relatorio;
import br.org.ufpr.tcc.util.reports.RelatoriosExportados;
import br.org.ufpr.tcc.util.reports.ReportUtils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Path("/cliente")
public class ClienteREST {

	ClienteFacade facade = new ClienteFacade();
	
	@GET
    @Path("{codCliente}")
    @Produces("application/json") 
    public ClienteDTO obter(@PathParam("codCliente") Long id, @QueryParam("fields") String fields) {
        ClienteDTO clienteDTO = facade.obter(id, fields);
		return clienteDTO;
    }
	
	@GET
	@Path("/pdf")
	@Produces("application/pdf")
    public Response gerarRelatorioPDF() {
		
		try {
			//listar clientes para o relatorio
			ClienteFiltroDTO filtro = filtrarResultadosRelatorio();
			List<ClienteDTO> listaClientes = facade.listar(filtro, null).getEntidades();
			
			//setar informacoes de cabecalho, rodape e filtros do relatorio 
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("tituloRelatorio", Relatorio.CLIENTES.getReportTitle());
			parametros.put("nomeSistema", "LAZARUS HOTELARIA");
			parametros.put("nome", "-");			
			parametros.put("dados", listaClientes);
			
			//gerar o PDF No servidor
			RelatoriosExportados relatorio = RelatoriosExportados.CLIENTES_PDF;
			String nomeArquivoJrxml = Relatorio.CLIENTES.getJasperReportName();
			JasperReport jasper = compileReport(
					JasperTest.class.getResourceAsStream(JasperTest.formatReportFileName(nomeArquivoJrxml)));
			
			List<ClienteDTO> listaFillReport = new ArrayList<ClienteDTO>();
			ClienteDTO clinteListaFillReport = new ClienteDTO();
			listaFillReport.add(clinteListaFillReport);
			
			
			JasperPrint print = fillReport(jasper, parametros, new JRBeanCollectionDataSource(listaFillReport));
			String pathCompletoPDFGerado = Constantes.PATH_ARMAZENAMENTO_RELATORIOS + File.separator + "Clientes.pdf";
			
			JasperExportManager.exportReportToPdfFile(print, pathCompletoPDFGerado);
			
			//ler o pdf e devolver para o cliente			
			FileInputStream fileInputStream = new FileInputStream(new File(pathCompletoPDFGerado));
			javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response
					.ok((Object) fileInputStream);
			responseBuilder.type("application/pdf");
			responseBuilder.header("Content-Disposition", "filename=test.pdf");
			
			return responseBuilder.build();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(new Mensagem(Mensagem.ERRO, "Problema ao gerar relatorio. Tente novamente mais tarde!"));
		}
		
    }

	private ClienteFiltroDTO filtrarResultadosRelatorio() {
		ClienteFiltroDTO filtro = new ClienteFiltroDTO();
		Pagina pagina = new Pagina();
		pagina.setPageSize(Integer.MAX_VALUE);
		filtro.setPagina(pagina);
		return filtro;
	}
	
	
	
	
	
	@GET
    @Produces("application/json")
	public ResultadoPaginadoDTO<ClienteDTO> listar(@QueryParam("currentpage") int currentPage,
	        @QueryParam("pagesize") int pageSize,
	        @QueryParam("nome") String cpf, @QueryParam("ativo") Boolean ativo,	        
	        @QueryParam("fields") String fields) {

        ClienteFiltroDTO filtro = new ClienteFiltroDTO();
        

        // Paginacao
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
    public Response inserir(ClienteDTO clienteDTO, @Context UriInfo uriInfo) {
        ResponseDTO response = facade.persistir(clienteDTO);        
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
    @Path("{codCliente}")
    @Produces("application/json")    
    public Response remover(@PathParam("codCliente") Long id) {
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(id);
        
        return remover(ids);
    }
    
    @PUT
    @Path("{codCliente}")
    @Consumes("application/json")
    @Produces("application/json")    
    public Response alterar(@PathParam("codCliente") Long id, ClienteDTO clienteDTO) {
    	clienteDTO.setCodCliente(id.intValue());
        ResponseDTO response = facade.persistir(clienteDTO);
        return Response.ok(response).build();
    }
	
}