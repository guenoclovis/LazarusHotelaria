package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.AtributoFiltroDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.util.Util;

public class AtributoDAO extends LazarusDAO<Atributo> {
	
	private static AtributoDAO dao = null;
	
	public static AtributoDAO getDAO(){
		if(dao == null){
			dao = new AtributoDAO();
		}
		return dao;
	}
	
	
	private AtributoDAO(){
		
	}
    
	public List<Atributo> listar(AtributoFiltroDTO filtros) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Atributo> cq = cb.createQuery(Atributo.class);
        Root<Atributo> root = cq.from(Atributo.class);
        Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

        cq.where(cb.and(predicados));

        Pagina pagina = filtros.getPagina();

        return findByCriteriaQuery(cq, pagina);
    }

    private Predicate[] buildPredicatePesquisa(AtributoFiltroDTO filtros, CriteriaBuilder cb,
        Root<Atributo> root) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(Atributo.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }
}
