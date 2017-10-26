package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.AtributoDAO;
import br.org.ufpr.tcc.dao.LazarusDAO;
import br.org.ufpr.tcc.dto.AtributoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Atributo;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.validator.AtributoValidator;

public class AtributoBC extends LazarusDAO<Atributo> {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private AtributoDAO dao = new AtributoDAO();
    private AtributoValidator validator = new AtributoValidator();

    public Atributo obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Atributo> listar(AtributoFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Atributo BC";
        log.info(logMsg);

        List<Atributo> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Atributo>(lista, new Pagina());
    }

    public void persistir(Atributo atributo) {

    	validator.validateAndThrow(atributo);
        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (atributo.getCodAtributo() == null) {
            log.info("Inicia a persistencia de um novo Atributo.");
            dao.persistir(atributo);
            log.info("Persistiu novo Atributo na base de dados.");

        } else {
            log.info("Inicia a atualizacao do Atributo [id=%d]" + atributo.getCodAtributo());

            try {
                //TODO: PENDENTE
                dao.persistir(atributo);
            } catch (Exception ex) {
                Logger.getLogger(AtributoBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou Atributo na base de dados.");
        }
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir Atributo com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
