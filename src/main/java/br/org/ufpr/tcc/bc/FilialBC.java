package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.standard.Severity;

import br.org.ufpr.tcc.dao.FilialDAO;
import br.org.ufpr.tcc.dto.FilialFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.validator.FilialValidator;

public class FilialBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private FilialDAO dao = new FilialDAO();
    private FilialValidator validator = new FilialValidator();

    public Filial obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Filial> listar(FilialFiltroDTO filtros) {

        String logMsg = "Iniciando a listagens de Filial BC";
        log.info(logMsg);

        List<Filial> lista = dao.listar(filtros);
        ResultadoPaginadoDTO<Filial> resultadoPaginadoDTO = new ResultadoPaginadoDTO<Filial>(lista, filtros.getPagina());
        
        if(lista.isEmpty()){
        	resultadoPaginadoDTO.getMensagens().add(new Mensagem(Mensagem.AVISO, "Nenhum registro encontrado!"));
        }

		return resultadoPaginadoDTO;
    }

    public ResponseDTO persistir(Filial filial) {

    	validator.validateAndThrow(filial);
    	
        if (filial.getCodFilial() == null) {
            log.info("Inicia a persistência de um novo Filial.");
            dao.persistir(filial);
            log.info("Persistiu novo Filial na base de dados.");

        } else {
            log.info("Inicia a atualização do Filial [id=%d]" + filial.getCodFilial());

            try {
            	dao.persistir(filial);
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
			dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir Filial com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
