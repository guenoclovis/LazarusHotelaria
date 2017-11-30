package br.org.ufpr.tcc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;

import br.org.ufpr.tcc.dto.QuartoFiltroDTO;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.Util;

public class QuartoDAO extends LazarusDAO<Quarto> {

	private static QuartoDAO dao = null;
	
	public static QuartoDAO getDAO(){
		if(dao == null){
			dao = new QuartoDAO();
		}
		return dao;
	}
	
	private QuartoDAO(){}
	
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	public List<Quarto> listar(QuartoFiltroDTO filtros) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Quarto> cq = cb.createQuery(Quarto.class);
		Root<Quarto> root = cq.from(Quarto.class);

		Predicate[] predicadosList = buildPredicatePesquisa(filtros, cb, root, cq);

		cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Quarto.ID)));

		TypedQuery<Quarto> listQuery = getEntityManager().createQuery(cq.distinct(true));

		Pagina pagina = filtros.getPagina();

		if (pagina != null) {
			CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
			Root<Quarto> rootCount = cqCount.from(Quarto.class);
			Path<Long> pathID = rootCount.get(Quarto.ID);
			Predicate[] predicadosCount = buildPredicatePesquisa(filtros, cb, rootCount, cqCount);

			cqCount.select(cb.countDistinct(pathID));

			if (predicadosCount.length > 0) {
				cqCount.where(predicadosCount);
			}

			pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

			listQuery.setFirstResult(pagina.getFirstResult());
			listQuery.setMaxResults(pagina.getPageSize());

		}

		return listQuery.getResultList();
	}

	public Session getHibernateSession() {
        EntityManager entityManager = getEntityManager();
        return entityManager.unwrap(Session.class);
    }

	
	public List<Quarto> listarSemReserva(QuartoFiltroDTO filtros) {

		StringBuffer SQL = new StringBuffer();

		SQL.append("SELECT distinct(q.*)  ");
		SQL.append("FROM quartos q LEFT JOIN reserva r on q.cod_quarto = r.cod_quarto  ");
		SQL.append("WHERE ");
		SQL.append(" q.cod_quarto NOT IN ( ");
		SQL.append("     SELECT distinct(q2.cod_quarto) FROM quartos q2 left join reserva r2 on q2.cod_quarto = r2.cod_quarto  ");
		SQL.append("     WHERE ( ");
		SQL.append("	(:dataEntrada >= r2.dt_entrada ) and (:dataEntrada < r2.dt_saida) ");
		SQL.append("	or ");
		SQL.append("	(:dataSaida > r2.dt_entrada ) and (:dataSaida <= r2.dt_saida)	 ");
		SQL.append("  ) ");
		SQL.append(") ");
		SQL.append(" AND q.cod_filial = :codFilial ");

		Query query = getEntityManager().createNativeQuery(SQL.toString(), Quarto.class);

		//filtros
		query.setParameter("dataEntrada", filtros.getDataEntrada());
		query.setParameter("dataSaida", filtros.getDataSaida());
		query.setParameter("codFilial", filtros.getCodFilial());

		//especificando tipo das colunas de retorno		
		//query.addScalar("cod_quarto", IntegerType.INSTANCE);

		List<Quarto> listaRetorno = (List<Quarto>) query.getResultList();
		
		return listaRetorno;
		
		/*
		Quarto q = null;
		for(Object objetoComTipoDesconhecido : listaObjTipoDesconhecido){
			Integer codQuarto  = (Integer) objetoComTipoDesconhecido;
			
			q = load(codQuarto);
			
			listaRetorno.add(q);
		}
		
		getHibernateSession().close();
		
		return listaRetorno;*/

	}
	
	
	public List<Quarto> listarComReserva(QuartoFiltroDTO filtros) {

		StringBuffer SQL = new StringBuffer();

		SQL.append("SELECT *");
		SQL.append("FROM reserva r2 ");
		SQL.append("WHERE ( ");
		SQL.append("  (:dataEntrada >= r2.dt_entrada ) and (:dataEntrada < r2.dt_saida) ");
		SQL.append("	OR ");
		SQL.append("  (:dataSaida > r2.dt_entrada ) and (:dataSaida <= r2.dt_saida) ");
		SQL.append(") ");

		SQLQuery query = getHibernateSession().createSQLQuery(SQL.toString());

		//filtros
		query.setDate("dataEntrada", filtros.getDataEntrada());
		query.setDate("dataSaida", filtros.getDataSaida());

		//especificando tipo das colunas de retorno		
		query.addScalar("cod_quarto", IntegerType.INSTANCE);

		List listaObjTipoDesconhecido = query.list();
		
		ArrayList<Quarto> listaRetorno = new ArrayList<Quarto>();
		
		Quarto q = null;
		for(Object objetoComTipoDesconhecido : listaObjTipoDesconhecido){
			Integer codQuarto  = (Integer) objetoComTipoDesconhecido;
			
			q = load(codQuarto);
			
			listaRetorno.add(q);
		}
		
		return listaRetorno;

	}

	private Predicate[] buildPredicatePesquisa(QuartoFiltroDTO filtros, CriteriaBuilder cb, Root<Quarto> root,
			CriteriaQuery<?> cq) {
		Predicate[] predicados = {};

		Path<Long> pathCodFilial = root.get("codFilial");
		Path<String> pathDescricao = root.get("descricao");

		if (filtros.getCodFilial() != null) {
			predicados = Util.add(predicados, cb.equal(pathCodFilial, filtros.getCodFilial()));
		}
		
		if (filtros.getDescricao() != null) {
			predicados = Util.add(predicados, cb.like(pathDescricao, Util.likeFormat(filtros.getDescricao())));
		}

		return predicados;
	}

	private Predicate[] buildPredicateSemReserva(QuartoFiltroDTO filtros, CriteriaBuilder cb, Root<Quarto> root,
			CriteriaQuery<?> cq) {
		Predicate[] predicados = {};

		Path<Long> pathCodFilial = root.get("codFilial");

		if (filtros.getCodFilial() != null) {
			predicados = Util.add(predicados, cb.equal(pathCodFilial, filtros.getCodFilial()));
		}

		if (filtros.getDataEntrada() != null || filtros.getDataSaida() != null) {

			Join<Quarto, Reserva> joinReserva = root.join("reservas", JoinType.INNER);

			Path<Date> pathDataEntrada = joinReserva.get("dtEntrada");
			Path<Date> pathDataSaida = joinReserva.get("dtSaida");

			if (filtros.getDataEntrada() != null) {
				predicados = Util.add(predicados,
						cb.or(cb.greaterThanOrEqualTo(pathDataEntrada, filtros.getDataEntrada()),
								cb.lessThan(pathDataSaida, filtros.getDataEntrada())));
			}

			if (filtros.getDataSaida() != null) {
				predicados = Util.add(predicados,
						cb.or(cb.greaterThanOrEqualTo(pathDataEntrada, filtros.getDataEntrada()),
								cb.lessThan(pathDataSaida, filtros.getDataEntrada())));
			}

		}

		return predicados;
	}
}
