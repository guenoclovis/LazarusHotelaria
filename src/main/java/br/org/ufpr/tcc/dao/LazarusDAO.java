package br.org.ufpr.tcc.dao;

import javax.persistence.EntityTransaction;

public class LazarusDAO<E> extends GenericDAO<E> {
	
	/**
	 * Construtor
	 */
	public LazarusDAO() {
		super(EntityManagerUtil.LAZARUS_PU);
	}
	
	public void persistir(E entity) {
		
		EntityTransaction transaction = getEntityManager().getTransaction();
		transaction.begin();
		
		try {
			merge(entity);
			flush();
			
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
		}
	}
	
	
	public void remover(E entity) {
		
		EntityTransaction transaction = getEntityManager().getTransaction();
		transaction.begin();
		
		try {
			remove(entity);
			flush();
			
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
		}
	}
}