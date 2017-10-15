package br.org.ufpr.tcc.exception.handler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.org.ufpr.tcc.dto.ResponseDTO;

@Provider
public class NegocioExceptionHandler implements ExceptionMapper<NegocioException> {

	private static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;

	@Override
	public Response toResponse(NegocioException exception) {
		
		ResponseDTO responseDTO = new ResponseDTO(exception.getMensagens());
		
		return Response.status(UNPROCESSABLE_ENTITY_STATUS_CODE).entity(responseDTO)
				.type(MediaType.APPLICATION_JSON).build();
	}

}