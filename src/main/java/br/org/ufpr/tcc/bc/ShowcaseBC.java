package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.ShowcaseDAO;
import br.org.ufpr.tcc.dto.ShowcaseFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Showcase;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class ShowcaseBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private ShowcaseDAO dao = new ShowcaseDAO();

    public Showcase obter(Integer id) {
        return dao.obter(id);
    }

    public ResultadoPaginadoDTO<Showcase> listar(ShowcaseFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Showcase BC";
        log.info(logMsg);

        List<Showcase> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Showcase>(lista, new Pagina());
    }

    public ResponseDTO persistir(Showcase showcase) {
        String descricaoOperacao = "";

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (showcase.getCodShowcase() == null) {
            log.info("Inicia a persistência de um novo Showcase.");
            dao.inserir(showcase);
            log.info("Persistiu novo Showcase na base de dados.");

        } else {
            log.info("Inicia a atualização do Showcase [id=%d]" + showcase.getCodShowcase());

            try {
                //TODO: PENDENTE
                dao.alterar(showcase);
            } catch (Exception ex) {
                Logger.getLogger(ShowcaseBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou Showcase na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.excluir(id);
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir Showcase com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
