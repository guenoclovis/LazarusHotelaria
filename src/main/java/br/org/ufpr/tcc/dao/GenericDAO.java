package br.org.ufpr.tcc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.org.ufpr.tcc.entity.Pagina;



public abstract class GenericDAO<E> {

	private final EntityManager gerenciadorDeEntidades;

	private Class<E> entidadeASerManipulada;

	/**
	 * Construtor
	 */
	public GenericDAO(String puName) {
		this.gerenciadorDeEntidades = EntityManagerUtil.getEntityManager(puName);
		this.entidadeASerManipulada = entidadeASerManipulada();
	}

	public EntityManager getEntityManager() {
		return gerenciadorDeEntidades;
	}

	// =========================================================
	// METODOS GENERICOS PARA PERSISTENCIA DE ENTIDADES
	// =========================================================

	public void insert(E entity) {
		assertNotNull(entity, "Entidade nula nao pode ser inserida.");
		EntityManager entityManager = getEntityManager();
		entityManager.persist(entity);
	}

	public void remove(E entity) {
		assertNotNull(entity, "Entidade nula nao pode ser removida.");

		EntityManager entityManager = getEntityManager();

		E managedEntity = entity;
		if (!entityManager.contains(entity)) {
			Serializable id = getIdValue(entity);
			E actualEntity = entityManager.find(entidadeASerManipulada, id);
			if (actualEntity != null) {
				managedEntity = actualEntity;
			} else {
				throw new PersistenceException(
						"Falha ao remover entidade nao gerenciada e nao encontrada no banco de dados.");
			}
		}

		entityManager.remove(managedEntity);
	}

	public E merge(E entity) {
		assertNotNull(entity, "Não é possível efetuar merge de entidade nula.");

		EntityManager entityManager = getEntityManager();

		E merged = entityManager.merge(entity);

		return merged;

	}

	public void refresh(E entity) {
		assertNotNull(entity, "Entidade nula nao pode ser recarregada.");
		getEntityManager().refresh(entity);
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}

	public E load(Serializable id) {
		return getEntityManager().find(entidadeASerManipulada, id);
	}

	public List<E> findAll() {
		return findAll(null, null);
	}

	public List<E> findAll(Pagina page) {
		return findAll(page, null);
	}

	public List<E> findAll(String order) {
		return findAll(null, order);
	}

	public List<E> findAll(Pagina page, String order) {
		Entity entityAnnotation = getEntityClass().getAnnotation(Entity.class);
		String entityName = null;
		if (entityAnnotation != null && entityAnnotation.name() != null && !entityAnnotation.name().trim().equals("")) {
			entityName = entityAnnotation.name();
		} else {
			entityName = getEntityClass().getSimpleName();
		}
		String query = "select this from " + entityName + " this";
		if (order != null) {
			query += " order by " + order;
		}
		return findByJPQL(query, page);
	}

	public List<E> findByJPQL(String jpql) {
		return findByJPQL(jpql, null);
	}

	public List<E> findByJPQL(String jpql, Pagina page) {
		TypedQuery<E> listQuery = getEntityManager().createQuery(jpql, getEntityClass());

		if (page != null) {
			String countQuery = createCountQuery(jpql);
			Query query = getEntityManager().createQuery(countQuery);
			Number cResults = (Number) query.getSingleResult();
			page.setTotalResults(cResults.intValue());
			listQuery.setFirstResult(page.getFirstResult());
			listQuery.setMaxResults(page.getPageSize());
		}

		return listQuery.getResultList();
	}

	public List<E> findByCriteriaQuery(CriteriaQuery<E> criteriaQuery) {
		return findByCriteriaQuery(criteriaQuery, null);
	}

	public List<E> findByCriteriaQuery(CriteriaQuery<E> criteriaQuery, Pagina page) {
		TypedQuery<E> listQuery = getEntityManager().createQuery(criteriaQuery);

		if (page != null) {
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			countQuery.select(builder.count(countQuery.from(getEntityClass())));
			if (criteriaQuery.getRestriction() != null) {
				countQuery.where(criteriaQuery.getRestriction());
			}
			getEntityManager().createQuery(countQuery);
			page.setTotalResults((int) (getEntityManager().createQuery(countQuery).getSingleResult() + 0));
			listQuery.setFirstResult(page.getFirstResult());
			listQuery.setMaxResults(page.getPageSize());
		}

		return listQuery.getResultList();
	}

	/**
	 * Obtém a classe da entidade de trabalho do DAO.
	 * 
	 * @return Classe da entidade
	 */
	public Class<E> getEntityClass() {
		return entidadeASerManipulada;
	}

	// =========================================================
	// METODOS PRIVADOS AUXILIARES
	// =========================================================

	/**
	 * Retorna o valor da chave primária de uma entidade.
	 * 
	 * @param entity
	 *            Entidade
	 * @return Valor da chave primária
	 */
	private Serializable getIdValue(E entity) {
		EntityManagerFactory entityManagerFactory = getEntityManager().getEntityManagerFactory();
		Object identifier = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity);
		if (identifier == null) {
			throw new IllegalStateException("Valor da chave da entidade inexistente!");
		}
		return (Serializable) identifier;
	}

	/**
	 * Converte uma query em uma count query.
	 * 
	 * @param query
	 *            a ser convertida.
	 * @return count query.
	 */
	private String createCountQuery(String query) {
		String result = query;
		Matcher matcher = Pattern.compile("[Ss][Ee][Ll][Ee][Cc][Tt](.+)[Ff][Rr][Oo][Mm]").matcher(result);

		if (matcher.find()) {
			String group = matcher.group(1).trim();
			result = result.replaceFirst(group, "COUNT(" + group + ")");
			matcher = Pattern.compile("[Oo][Rr][Dd][Ee][Rr](.+)").matcher(result);

			if (matcher.find()) {
				group = matcher.group(0);
				result = result.replaceFirst(group, "");
			}

		} else {
			throw new PersistenceException("malformed-jpql");
		}

		return result;
	}

	/**
	 * Dispara exceção se a entidade estiver nula
	 */
	private void assertNotNull(E entity, String message) {
		if (entity == null) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * Devolve qual é a entidade que esta DAO generica vai manipular.
	 * 
	 * @return Entidade
	 */
	private Class<E> entidadeASerManipulada() {
		return getGenericTypeArgument(this.getClass(), 0);
	}


	/**
	 * Pega o n-ésimo tipo generico da classe.
	 * 
	 * @param clazzWithGenericTypesArguments
	 *            classe que contém tipo genérico. Ex: ClasseTeste<T,D,S>, onde
	 *            T,D e S são os tipos genericos.
	 * 
	 * @param genericTypeArgumenPosition
	 *            qual o tipo generico será obtido.
	 *            
	 * @return tipo generico obtido
	 */
	@SuppressWarnings("unchecked")
	private static <T> Class<T> getGenericTypeArgument(final Class<?> clazzWithGenericTypesArguments,
			final int genericTypeArgumenPosition) {
		
		final Type type = clazzWithGenericTypesArguments.getGenericSuperclass();

		ParameterizedType paramType;
		try {
			paramType = (ParameterizedType) type;
		} catch (ClassCastException cause) {
			return getGenericTypeArgument((Class<T>) type, genericTypeArgumenPosition);
		}

		return (Class<T>) paramType.getActualTypeArguments()[genericTypeArgumenPosition];
	}

}