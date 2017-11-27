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
import org.apache.james.mime4j.message.Message;

import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.util.Util;

public class ClienteDAO extends LazarusDAO<Cliente> {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	public List<Cliente> listar(ClienteFiltroDTO filtros) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> root = cq.from(Cliente.class);

		Predicate[] predicadosList = buildPredicatePesquisa(filtros, cb, root, cq);

		cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Cliente.NOME)));

		TypedQuery<Cliente> listQuery = getEntityManager().createQuery(cq.distinct(true));

		Pagina pagina = filtros.getPagina();

		if (pagina != null) {
			CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
			Root<Cliente> rootCount = cqCount.from(Cliente.class);
			Path<Long> pathID = rootCount.get(Cliente.ID);
			Predicate[] predicadosCount = buildPredicatePesquisa(filtros, cb, rootCount, cqCount);

			cqCount.select(cb.countDistinct(pathID));
			cqCount.where(predicadosCount);

			pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

			listQuery.setFirstResult(pagina.getFirstResult());
			listQuery.setMaxResults(pagina.getPageSize());

		}

		return listQuery.getResultList();
	}

	private Predicate[] buildPredicatePesquisa(ClienteFiltroDTO filtros, CriteriaBuilder cb, Root<Cliente> root,
			CriteriaQuery<?> cq) {
		Predicate[] predicados = {};

		Path<String> pathCampoTexto = root.get(Cliente.NOME);

		if (StringUtils.isNotBlank(filtros.getNome())) {
			predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
		}

		// ... outros predicados/filtros se houver

		return predicados;
	}

	public Cliente obterClienteParaReserva(ReservaDTO dto) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> root = cq.from(Cliente.class);

		Predicate[] predicadosList = buildPredicateObterParaReserva(dto, cb, root, cq);

		cq.where(cb.and(predicadosList)).orderBy(cb.asc(root.get(Cliente.NOME)));

		TypedQuery<Cliente> listQuery = getEntityManager().createQuery(cq.distinct(true));

		Pagina pagina = null;

		if (pagina != null) {
			CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
			Root<Cliente> rootCount = cqCount.from(Cliente.class);
			Path<Long> pathID = rootCount.get(Cliente.ID);
			Predicate[] predicadosCount = buildPredicateObterParaReserva(dto, cb, root, cq);
			;

			cqCount.select(cb.countDistinct(pathID));
			cqCount.where(predicadosCount);

			pagina.setTotalResults((int) (getEntityManager().createQuery(cqCount).getSingleResult() + 0));

			listQuery.setFirstResult(pagina.getFirstResult());
			listQuery.setMaxResults(pagina.getPageSize());

		}

		List<Cliente> resultList = listQuery.getResultList();

		if (resultList == null) {
			return null;
		}

		if (resultList.size() == 0) {
			return null;
		}

		if (resultList.size() > 1) {
			throw new NegocioException(new Mensagem(Mensagem.ERRO,
					"Cliente possui mais de uma cadastro no Sistema. Entre em contato com o Hotel!"));
		}
		
		return resultList.get(0);

	}

	private Predicate[] buildPredicateObterParaReserva(ReservaDTO dto, CriteriaBuilder cb, Root<Cliente> root,
			CriteriaQuery<Cliente> cq) {
		
		Predicate[] predicados = {};
		
		Path<String> pathCampoTexto = root.get(Cliente.NOME);
		Path<String> pathCampoTelefone1 = root.get("telefone1");
		Path<String> pathCampoEmail1 = root.get("email1");
		Path<String> pathCampoCPF = root.get("cpf");
		
		predicados = Util.add(predicados, cb.equal(pathCampoTexto, dto.getNome()));
		
		predicados = Util.add(predicados, cb.equal(pathCampoEmail1, dto.getEmail()));
		
		predicados = Util.add(predicados, cb.equal(pathCampoTelefone1, dto.getTelefone()));
		
		predicados = Util.add(predicados, cb.equal(pathCampoCPF, dto.getCpf()));
		
		return predicados;
		
	}
}
