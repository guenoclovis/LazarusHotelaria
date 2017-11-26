package br.org.ufpr.tcc.exception.handler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.org.ufpr.tcc.dto.ResponseDTO;

@Provider
public class AuthExceptionHandler implements ExceptionMapper<AuthException> {

	private static final int UNAUTHORIZED_STATUS_CODE = 401;

	@Override
	public Response toResponse(AuthException exception) {
		
		ResponseDTO responseDTO = new ResponseDTO(exception.getMensagens());
		
		return Response.status(UNAUTHORIZED_STATUS_CODE).entity(responseDTO)
				.type(MediaType.APPLICATION_JSON).build();
	}

}