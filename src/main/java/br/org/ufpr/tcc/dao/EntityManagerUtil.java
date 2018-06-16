package br.org.ufpr.tcc.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	public static final String LAZARUS_PU = "lazarus-pu";
	
	
	private static HashMap<String, EntityManagerFactory>  emfactories = new HashMap<String, EntityManagerFactory>();

	public static EntityManager getEntityManager(String puName) {
		
		 EntityManagerFactory emf = emfactories.get(puName);
		
		 if (emf == null){
			emf = Persistence.createEntityManagerFactory(puName);
			emfactories.put(puName, emf);
		 }
		 
		 return emf.createEntityManager();
	}
}