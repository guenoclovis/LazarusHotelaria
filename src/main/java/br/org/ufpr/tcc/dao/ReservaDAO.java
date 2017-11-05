package br.org.ufpr.tcc.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.util.Util;


public class ReservaDAO extends LazarusDAO<Reserva> {
    
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
        
        Path<String> pathCampoTexto = root.get(Reserva.ID);
        
        /*
        if(StringUtils.isNotBlank(filtros.getIdReserva())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getIdReserva())));
        }
        */
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }
   
}
