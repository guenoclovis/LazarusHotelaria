package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.QuartoDAO;
import br.org.ufpr.tcc.dto.QuartoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Quarto;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.validator.QuartoValidator;

public class QuartoBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private QuartoDAO dao = QuartoDAO.getDAO();
    private QuartoValidator validator = new QuartoValidator();

    public Quarto obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Quarto> listar(QuartoFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Quartos";
        log.info(logMsg);

        List<Quarto> lista = dao.listar(filtros);
        ResultadoPaginadoDTO<Quarto> resultadoPaginadoDTO = new ResultadoPaginadoDTO<Quarto>(lista, filtros.getPagina());
        
        if(lista.isEmpty()){
        	resultadoPaginadoDTO.getMensagens().add(new Mensagem(Mensagem.AVISO, "Nenhum registro encontrado!"));
        }
        
		return resultadoPaginadoDTO;
    }

    
    public ResultadoPaginadoDTO<Quarto> listarSemReserva(QuartoFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Quartos sem reserva";
        log.info(logMsg);

        List<Quarto> lista = dao.listarSemReserva(filtros);
        ResultadoPaginadoDTO<Quarto> resultadoPaginadoDTO = new ResultadoPaginadoDTO<Quarto>(lista, filtros.getPagina());
        
        if(lista.isEmpty()){
        	resultadoPaginadoDTO.getMensagens().add(new Mensagem(Mensagem.AVISO, "Nenhum registro encontrado!"));
        }
        
		return resultadoPaginadoDTO;
    }

    
    public ResponseDTO persistir(Quarto quarto) {
    	validator.validateAndThrow(quarto);

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (quarto.getCodQuarto() == null) {
            log.info("Inicia a persistência de um novo quarto.");
            dao.persistir(quarto);
            log.info("Persistiu novo quarto na base de dados.");

        } else {
            log.info("Inicia a atualizacao do quarto [id=%d]" + quarto.getCodQuarto());

            try {
                //TODO: PENDENTE
                dao.persistir(quarto);
            } catch (Exception ex) {
                Logger.getLogger(QuartoBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou quarto na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
    		dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Nao foi possivel excluir quarto com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
