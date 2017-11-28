package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.TipoQuartoDAO;
import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.TipoQuarto;
import br.org.ufpr.tcc.validator.FilialValidator;
import br.org.ufpr.tcc.validator.TipoQuartoValidator;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class TipoQuartoBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private TipoQuartoDAO dao = TipoQuartoDAO.getDAO();
    private TipoQuartoValidator validator = new TipoQuartoValidator();
    
    public TipoQuarto obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<TipoQuarto> listar(TipoQuartoFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de TipoQuarto BC";
        log.info(logMsg);

        List<TipoQuarto> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<TipoQuarto>(lista, new Pagina());
    }

    public ResponseDTO persistir(TipoQuarto tipoquarto) {
        
    	validator.validateAndThrow(tipoquarto);

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (tipoquarto.getCodTipoQuarto() == null) {
            log.info("Inicia a persistencia de um novo Tipo de Quarto.");
            dao.persistir(tipoquarto);
            log.info("Persistiu novo Tipo de Quarto na base de dados.");

        } else {
            log.info("Inicia a atualizacao do Tipo de Quarto [id=%d]" + tipoquarto.getCodTipoQuarto());

            try {
                //TODO: PENDENTE
                dao.persistir(tipoquarto);
            } catch (Exception ex) {
                Logger.getLogger(TipoQuartoBC.class.getName()).log(Level.SEVERE, "Erro ao alterar Tipo de Quarto.", ex);
            }
            log.info("Alterou Tipo de Quarto na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Nao foi possivel excluir Tipo de Quarto com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
