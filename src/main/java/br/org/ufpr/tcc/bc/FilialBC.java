package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.FilialDAO;
import br.org.ufpr.tcc.dto.FilialFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class FilialBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private FilialDAO dao = new FilialDAO();

    public Filial obter(Integer id) {
        return dao.obter(id);
    }

    public ResultadoPaginadoDTO<Filial> listar(FilialFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Filial BC";
        log.info(logMsg);

        List<Filial> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Filial>(lista, new Pagina());
    }

    public ResponseDTO persistir(Filial filial) {
        String descricaoOperacao = "";

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (filial.getCodFilial() == null) {
            log.info("Inicia a persistência de um novo Filial.");
            dao.inserir(filial);
            log.info("Persistiu novo Filial na base de dados.");

        } else {
            log.info("Inicia a atualização do Filial [id=%d]" + filial.getCodFilial());

            try {
                //TODO: PENDENTE
                dao.alterar(filial);
            } catch (Exception ex) {
                Logger.getLogger(FilialBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou Filial na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.excluir(id);
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir Filial com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
