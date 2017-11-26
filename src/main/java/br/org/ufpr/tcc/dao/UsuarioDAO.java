package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.LoginDTO;
import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.util.Util;

public class UsuarioDAO extends LazarusDAO<Usuario> {

	public Usuario obterParaLogin(LoginDTO dto) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);
		Predicate[] predicados = buildPredicatePesquisaPorLogin(dto, cb, root);

		cq.where(cb.and(predicados));

		List<Usuario> lista = findByCriteriaQuery(cq, null);

		if (lista == null) {
			return null;
		}

		if (lista != null && lista.isEmpty()) {
			return null;
		}

		return lista.get(0);
	}

	public List<Usuario> listar(UsuarioFiltroDTO filtros) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);
		Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

		cq.where(cb.and(predicados));

		Pagina pagina = filtros.getPagina();

		return findByCriteriaQuery(cq, pagina);
	}

	private Predicate[] buildPredicatePesquisa(UsuarioFiltroDTO filtros, CriteriaBuilder cb, Root<Usuario> root) {
		Predicate[] predicados = {};

		Path<String> pathCampoNome = root.get(Usuario.NOME);

		if (StringUtils.isNotBlank(filtros.getNome())) {
			predicados = Util.add(predicados, cb.like(pathCampoNome, Util.likeFormat(filtros.getNome())));
		}

		// ... outros predicados/filtros se houver

		return predicados;
	}

	private Predicate[] buildPredicatePesquisaPorLogin(LoginDTO dto, CriteriaBuilder cb, Root<Usuario> root) {
		Predicate[] predicados = {};

		Path<String> pathCampoUsuario = root.get(Usuario.LOGIN);
		Path<String> pathCampoSenha = root.get("senha");

		if (StringUtils.isNotBlank(dto.getUsuario())) {
			predicados = Util.add(predicados, cb.like(pathCampoUsuario, Util.likeFormat(dto.getUsuario())));
		}

		if (StringUtils.isNotBlank(dto.getSenha())) {
			predicados = Util.add(predicados, cb.like(pathCampoSenha, Util.likeFormat(dto.getSenha())));
		}

		return predicados;
	}

}
