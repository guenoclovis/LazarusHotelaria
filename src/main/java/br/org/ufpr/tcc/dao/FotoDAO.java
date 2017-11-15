package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.util.Util;

public class FotoDAO extends LazarusDAO<Foto> {

	public List<Foto> listar(FotoFiltroDTO filtros) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Foto> cq = cb.createQuery(Foto.class);
        Root<Foto> root = cq.from(Foto.class);

        Predicate[] predicadosList = buildPredicatePesquisa(filtros, cb, root, cq);

        cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Foto.LEGENDA)));

        TypedQuery<Foto> listQuery = getEntityManager().createQuery(cq.distinct(true));

        Pagina pagina = filtros.getPagina();

        if (pagina != null) {
            CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
            Root<Foto> rootCount = cqCount.from(Foto.class);
            Path<Long> pathID = rootCount.get(Foto.ID);
            Predicate[] predicadosCount = buildPredicatePesquisa(filtros, cb, rootCount, cqCount);

            cqCount.select(cb.countDistinct(pathID));
            cqCount.where(predicadosCount);

            pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

            listQuery.setFirstResult(pagina.getFirstResult());
            listQuery.setMaxResults(pagina.getPageSize());

        }

        return listQuery.getResultList(); 
	}
    
	private Predicate[] buildPredicatePesquisa(FotoFiltroDTO filtros, CriteriaBuilder cb,
    		Root<Foto> root, CriteriaQuery<?> cq) {
        Predicate[] predicados = { };
        
        Path<String> pathLegenda = root.get(Foto.LEGENDA);
        Path<String> pathPath = root.get(Foto.NOME_FOTO_ORIGINAL);
        
        if(StringUtils.isNotBlank(filtros.getLegenda())){
            predicados = Util.add(predicados, cb.like(pathLegenda, Util.likeFormat(filtros.getLegenda())));
        }
        
        if(StringUtils.isNotBlank(filtros.getPath())){
            predicados = Util.add(predicados, cb.like(pathLegenda, Util.likeFormat(filtros.getPath())));
        }
        
        return predicados;
    }
	
}
