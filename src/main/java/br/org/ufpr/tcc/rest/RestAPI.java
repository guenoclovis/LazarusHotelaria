package br.org.ufpr.tcc.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.org.ufpr.tcc.exception.handler.NegocioExceptionHandler;

@ApplicationPath("/rest-clovis")
public class RestAPI extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public RestAPI() {
		
		singletons.add(new LoginREST());
		
		singletons.add(new ClienteREST());
		singletons.add(new FilialREST());
		singletons.add(new AtributoREST());
		singletons.add(new FotoREST());
		singletons.add(new TipoQuartoREST());
		singletons.add(new ReservaREST());
		singletons.add(new QuartoREST());
		singletons.add(new ContatoREST());
		singletons.add(new UsuarioREST());
		
		singletons.add(new ShowcaseREST());
		
		
	}
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(NegocioExceptionHandler.class); 
        return classes;

    }

	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
