package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.TipoQuarto;
import br.org.ufpr.tcc.util.Util;

public class TipoQuartoDAO extends LazarusDAO<TipoQuarto> {
    
	private static TipoQuartoDAO dao = null;
	
	public static TipoQuartoDAO getDAO(){
		if(dao == null){
			dao = new TipoQuartoDAO();
		}
		return dao;
	}
	
	private TipoQuartoDAO(){}
	
	public List<TipoQuarto> listar(TipoQuartoFiltroDTO filtros) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TipoQuarto> cq = cb.createQuery(TipoQuarto.class);
        Root<TipoQuarto> root = cq.from(TipoQuarto.class);
        Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

        cq.where(cb.and(predicados));

        Pagina pagina = filtros.getPagina();

        return findByCriteriaQuery(cq, pagina);
    }

    private Predicate[] buildPredicatePesquisa(TipoQuartoFiltroDTO filtros, CriteriaBuilder cb,
        Root<TipoQuarto> root) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(TipoQuarto.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }

}
