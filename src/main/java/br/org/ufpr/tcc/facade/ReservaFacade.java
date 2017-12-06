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
import br.org.ufpr.tcc.util.Constantes;
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
        
        Integer codCliente = obterIDCliente(dto, c);
        
        DTOtoReserva converter = new DTOtoReserva();
        Reserva reserva = converter.convert(dto);
        reserva.setCodCliente(codCliente);
        reserva.setStatus(StatusReservaEnum.SOLICITADA.getCodigo());
        reserva.setDtReserva(DataUtil.toDate(LocalDate.now()));        
        
		ResponseDTO responseDTO = bc.persistir(reserva);

        logMsg = "Registro de Reserva persistido";
        log.info(logMsg);
        
        enviarEmail(dto, reserva);

        return responseDTO;
	}

	private Integer obterIDCliente(ReservaDTO dto, Cliente c) {
		String logMsg;
		
		ClienteBC clienteBC = new ClienteBC();
        Cliente cliente = clienteBC.obterClienteParaReserva(dto);
        
        Integer codCliente = null;
        
        if(cliente == null){
        	ResponseDTO resultado = clienteBC.persistir(c);        	
        	logMsg = "Realizou Pré-Cadastro de Usuário";
        	codCliente = resultado.getId().intValue();
        } else {
        	codCliente = cliente.getCodCliente().intValue();
        }
        
		return codCliente;
	}

	private void enviarEmail(ReservaDTO dto, Reserva reserva) {
		
		EmailBC emailBC = new EmailBC();
		
        StringBuffer mensagem = new StringBuffer();
        
        mensagem.append("Caro "+dto.getNome()+":");
        mensagem.append("\n");
        mensagem.append("\nObrigado por escolher a Lazarus Hotelaria para sua hospedagem!");
        mensagem.append("\nSua Solicitac�o de Reserva foi realizada com sucesso conforme dados abaixo:");
        mensagem.append("\nNome: "+dto.getNome());
        mensagem.append("\nTelefone: "+dto.getTelefone());
        mensagem.append("\nE-mail: "+dto.getEmail());
        mensagem.append("\nData Entrada: "+dto.getDataEntrada());
        mensagem.append("\nData Sa�da: "+dto.getDtSaida());
        mensagem.append("\nValor(R$): "+reserva.getPreco());
        mensagem.append("\n");
        mensagem.append("\nAguardamos dep�sito de sinal correspondente a 50% do valor em at� 5 dias para que seja feita a confirma��o da reserva.");
        mensagem.append("\nDADOS PARA DEP�SITO DO SINAL");
        mensagem.append("\nBanco: " + Constantes.BANCO_EMPRESA);
        mensagem.append("\nAg�ncia: " + Constantes.AGENCIA_EMPRESA);
        mensagem.append("\nCC: " + Constantes.CONTA_CORRENTE_EMPRESA);
        mensagem.append("\nCNPJ: " + Constantes.CNPJ_EMPRESA);
        
        emailBC.enviarEmail(dto.getEmail(), "Solicita��o de Reserva", mensagem.toString());
	}

}
