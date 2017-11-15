package br.org.ufpr.tcc.dao;

import javax.persistence.EntityTransaction;

public class LazarusDAO<E> extends GenericDAO<E> {
	
	/**
	 * Construtor
	 */
	public LazarusDAO() {
		super(EntityManagerUtil.LAZARUS_PU);
	}
	
	public E persistir(E entity) {
		
		EntityTransaction transaction = getEntityManager().getTransaction();
		transaction.begin();
		E entitySaved = null;
		
		try {
			entitySaved = merge(entity);
			flush();
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		return entitySaved;
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