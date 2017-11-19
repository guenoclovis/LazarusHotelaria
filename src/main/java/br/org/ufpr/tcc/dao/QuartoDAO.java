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

import br.org.ufpr.tcc.dto.QuartoFiltroDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.util.Util;


public class QuartoDAO extends LazarusDAO<Quarto> {
    
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
            
            if(predicadosCount.length > 0){
            	cqCount.where(predicadosCount);            	
            }

            pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

            listQuery.setFirstResult(pagina.getFirstResult());
            listQuery.setMaxResults(pagina.getPageSize());

        }

        return listQuery.getResultList();        
    }

    private Predicate[] buildPredicatePesquisa(QuartoFiltroDTO filtros, CriteriaBuilder cb,
    		Root<Quarto> root, CriteriaQuery<?> cq) {
        Predicate[] predicados = { };
        
        Path<String> pathNumeroQuarto = root.get(Quarto.NUMERO_QUARTO);
        /*
         * TODO: PENDENTE
         
        if(filtros.getIdHotel() != null){
            predicados = Util.add(predicados, cb.equal(pathNumeroQuarto, filtros.getId));
        }
        */
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }
}
