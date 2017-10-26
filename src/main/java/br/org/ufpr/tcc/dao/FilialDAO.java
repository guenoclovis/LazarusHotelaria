package br.org.ufpr.tcc.dao;

import java.util.List;

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

	public List<Filial> listar(FilialFiltroDTO filtros) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Filial> cq = cb.createQuery(Filial.class);
        Root<Filial> root = cq.from(Filial.class);
        Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

        cq.where(cb.and(predicados));

        Pagina pagina = filtros.getPagina();

        return findByCriteriaQuery(cq, pagina);
    }

    private Predicate[] buildPredicatePesquisa(FilialFiltroDTO filtros, CriteriaBuilder cb,
        Root<Filial> root) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(Filial.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }
	
}
