package br.org.ufpr.tcc.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.util.Util;


public class ReservaDAO extends LazarusDAO<Reserva> {

	private static ReservaDAO dao = null;
	
	public static ReservaDAO getDAO(){
		if(dao == null){
			dao = new ReservaDAO();
		}
		return dao;
	}
	
	private ReservaDAO(){}
	
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    public List<Reserva> listar(ReservaFiltroDTO filtros) {
    	CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reserva> cq = cb.createQuery(Reserva.class);
        Root<Reserva> root = cq.from(Reserva.class);

        Predicate[] predicadosList = buildPredicatePesquisa(filtros, cb, root, cq);

        cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Reserva.ID)));

        TypedQuery<Reserva> listQuery = getEntityManager().createQuery(cq.distinct(true));

        Pagina pagina = filtros.getPagina();

        if (pagina != null) {
            CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
            Root<Reserva> rootCount = cqCount.from(Reserva.class);
            Path<Long> pathID = rootCount.get(Reserva.ID);
            Predicate[] predicadosCount = buildPredicatePesquisa(filtros, cb, rootCount, cqCount);

            cqCount.select(cb.countDistinct(pathID));
            cqCount.where(predicadosCount);

            pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

            listQuery.setFirstResult(pagina.getFirstResult());
            listQuery.setMaxResults(pagina.getPageSize());

        }

        return listQuery.getResultList();        
    }

    
    private Predicate[] buildPredicatePesquisa(ReservaFiltroDTO filtros, CriteriaBuilder cb,
    		Root<Reserva> root, CriteriaQuery<?> cq) {
        Predicate[] predicados = { };
        
        
//        Join<Reserva, Quarto> joinQuarto = root.join(Reserva.QUARTO, JoinType.INNER);
//        Path<Integer> pathCodFilial = joinQuarto.get(Quarto.COD_FILIAL);
//        
//        Path<Date> pathDataEntrada = root.get(Reserva.DATA_ENTRADA);
//        Path<Date> pathDataSaida = root.get(Reserva.DATA_SAIDA);
//        
//        if(filtros.getCodFilial() != null){
//            predicados = Util.add(predicados, cb.equal(pathCodFilial,filtros.getCodFilial()));
//        }
//        
//        if(filtros.getDataEntrada() != null){
//            predicados = Util.add(predicados, cb.greaterThanOrEqualTo(pathDataEntrada, filtros.getDataEntrada()));
//        }
//        
//        if(filtros.getDataSaida() != null){
//        	predicados = Util.add(predicados, cb.lessThanOrEqualTo(pathDataSaida, filtros.getDataSaida()));
//        }
        
        return predicados;
    }
   
}
