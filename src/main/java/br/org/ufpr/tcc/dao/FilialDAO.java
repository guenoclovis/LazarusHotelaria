package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.FilialFiltroDTO;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.util.Util;

public class FilialDAO extends LazarusDAO<Filial>{

	private static FilialDAO dao = null;
	
	public static FilialDAO getDAO(){
		if(dao == null){
			dao = new FilialDAO();
		}
		return dao;
	}
	
	private FilialDAO(){}
	
	public List<Filial> listar(FilialFiltroDTO filtros) {
		
		//SELECT a, b,c FROM filial WHERE nome like 'clov%' and idade > 30 and r3  ... ORDER BY b
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Filial> cq = cb.createQuery(Filial.class);
        Root<Filial> root = cq.from(Filial.class);

        Predicate[] predicadosList = buildPredicatePesquisa(filtros, cb, root, cq);

        cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Filial.NOME)));

        TypedQuery<Filial> listQuery = getEntityManager().createQuery(cq.distinct(true));

        Pagina pagina = filtros.getPagina();

        if (pagina != null) {
            CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
            Root<Filial> rootCount = cqCount.from(Filial.class);
            Path<Long> pathID = rootCount.get(Filial.ID);
            Predicate[] predicadosCount = buildPredicatePesquisa(filtros, cb, rootCount, cqCount);

            cqCount.select(cb.countDistinct(pathID));
            cqCount.where(predicadosCount);

            pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

            listQuery.setFirstResult(pagina.getFirstResult());
            listQuery.setMaxResults(pagina.getPageSize());

        }

        return listQuery.getResultList();        
    }

    private Predicate[] buildPredicatePesquisa(FilialFiltroDTO filtros, CriteriaBuilder cb,
        Root<Filial> root, CriteriaQuery<?> cq) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(Filial.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }
	
}
