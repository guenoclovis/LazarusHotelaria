package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.ReservaDAO;
import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.validator.ReservaValidator;

public class ReservaBC {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private ReservaDAO dao = new ReservaDAO();
    private ReservaValidator validator = new ReservaValidator();

    public Reserva obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Reserva> listar(ReservaFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Reserva BC";
        log.info(logMsg);

        List<Reserva> lista = dao.listar(filtros);
        ResultadoPaginadoDTO<Reserva> resultadoPaginadoDTO = new ResultadoPaginadoDTO<Reserva>(lista, filtros.getPagina());
        
        if(lista.isEmpty()){
        	resultadoPaginadoDTO.getMensagens().add(new Mensagem(Mensagem.AVISO, "Nenhum registro encontrado!"));
        }
        
		return resultadoPaginadoDTO;
    }

    public ResponseDTO persistir(Reserva reserva) {
    	validator.validateAndThrow(reserva);

        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
        if (reserva.getCodReserva() == null) {
            log.info("Inicia a persistência de uma nova reserva.");
            dao.persistir(reserva);
            log.info("Persistiu novo reserva na base de dados.");

        } else {
            log.info("Inicia a atualização da reserva [id=%d]" + reserva.getCodReserva());

            try {
                //TODO: PENDENTE
                dao.persistir(reserva);
            } catch (Exception ex) {
                Logger.getLogger(ReservaBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou reserva na base de dados.");
        }

        return new ResponseDTO();
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
    		dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Nï¿½o foi possï¿½vel excluir reserva com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
