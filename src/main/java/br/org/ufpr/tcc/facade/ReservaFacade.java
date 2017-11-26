package br.org.ufpr.tcc.facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.util.DateUtil;

import br.org.ufpr.tcc.bc.ClienteBC;
import br.org.ufpr.tcc.bc.ReservaBC;
import br.org.ufpr.tcc.converter.ReservaToDTO;
import br.org.ufpr.tcc.converter.DTOtoReserva;
import br.org.ufpr.tcc.converter.ReservaDTOtoCliente;
import br.org.ufpr.tcc.dto.ReservaDTO;
import br.org.ufpr.tcc.dto.ReservaFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Reserva;
import br.org.ufpr.tcc.enuns.StatusEnum;
import br.org.ufpr.tcc.enuns.StatusReservaEnum;
import br.org.ufpr.tcc.util.DataUtil;
import br.org.ufpr.tcc.entity.Cliente;
import br.org.ufpr.tcc.entity.Pagina;

public class ReservaFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private ReservaBC bc = new ReservaBC();    

    public ReservaDTO obter(Long id, String fields) {
        String logMsg = "Iniciando a busca do reserva id[%d]" + id;
        
        log.info(logMsg);

        Reserva c = bc.obter(id.intValue());

        logMsg = "Busca do reserva finalizada";
        log.info(logMsg);

        //CONVERTER
        ReservaToDTO converter = new ReservaToDTO();
        ReservaDTO reservaDTO = converter.convert(c);
    	
        return reservaDTO;
    }

    public ResultadoPaginadoDTO<ReservaDTO> listar(ReservaFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagens de Reserva Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Reserva> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
        for(Reserva c : listagem.getEntidades()){
        	ReservaToDTO converter = new ReservaToDTO();
        	ReservaDTO reservaDTO = converter.convert(c);
            
        	reservasDTO.add(reservaDTO);
        }
		
		ResultadoPaginadoDTO<ReservaDTO> responseDTO = new ResultadoPaginadoDTO<ReservaDTO>(reservasDTO, new Pagina());

        logMsg = "Finalizando listagem de Reserva";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(ReservaDTO dto) {
        String logMsg = "Iniciando a persistência de reserva";
        log.info(logMsg);

        //CONVERTER
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);

        return responseDTO;
    }
    
    public ResponseDTO remover(Long... ids) {
    	ResponseDTO retorno = new ResponseDTO();
    	
    	for(Long id : ids){
    		ResponseDTO aux = bc.remover(id.intValue());
    		retorno.getMensagens().addAll(aux.getMensagens());
    	}
    	
    	return retorno;
    	 
    }

	public ResponseDTO inserir(ReservaDTO dto) {
		String logMsg = "Iniciando a persistência de Reserva";
        log.info(logMsg);

        //CONVERTER
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);

        return responseDTO;
	}

	public ResponseDTO solicitarReserva(ReservaDTO dto) {
		String logMsg = "Solicitando Reserva ...";
        log.info(logMsg);

        //CONVERTER
        ReservaDTOtoCliente r2c = new ReservaDTOtoCliente();
        Cliente c = r2c.convert(dto);
        c.setStatus(StatusEnum.ATIVO.getCodigo());
        
        ClienteBC clienteBC = new ClienteBC();
//        Cliente cliente = clienteBC.obterPorCPF(dto.getCpf());
//        if(cliente == null){
        	ResponseDTO resultado = clienteBC.persistir(c);        	
        	logMsg = "Realizou Pré-Cadastro de Usuário";
//        }
        
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        reserva.setCodCliente(resultado.getId().intValue());
        reserva.setStatus(StatusReservaEnum.SOLICITADA.getCodigo());
        reserva.setDtReserva(DataUtil.toDate(LocalDate.now()));
        reserva.setPreco(200L);
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);
        
        EmailBC emailBC = new EmailBC();
        StringBuffer mensagem = new StringBuffer();
        mensagem.append("Caro "+dto.getNome()+",");
        mensagem.append("Obrigado por escolher a Lazarus Hotelaria para sua hospedagem!");
        mensagem.append("\nSua Solicitacao de Reserva foi realizada com sucesso conforme dados abaixo:\nNome: "+dto.getNome()+"\nTelefone: "+dto.getTelefone()+"\nE-mail: "+dto.getEmail()+"\nData Entrada: "+dto.getDataEntrada()+"\nData Saída: "+dto.getDtSaida()+"\nValor: "+reserva.getPreco()+"\n");
        mensagem.append("\n\nAguardamos depósito de sinal correspondente a 50% do valor em até 5 dias para que seja feita a confirmação de reserva.");
        mensagem.append("\nDADOS PARA DEPÓSITO DO SINAL");
        mensagem.append("\nBanco: 156");
        mensagem.append("\nAgência: 48989-3");
        mensagem.append("\nCC: 48989-X");
        mensagem.append("\nCNPJ: 82.438.591/0001-48");
        
        emailBC.enviarEmail(dto.getEmail(), "Solicitação de Reserva", mensagem.toString());

        return responseDTO;
	}

}
