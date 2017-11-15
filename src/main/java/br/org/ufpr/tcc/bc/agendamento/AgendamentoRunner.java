package br.org.ufpr.tcc.bc.agendamento;

import java.io.File;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.util.Constantes;


@Startup
@Singleton
public class AgendamentoRunner {
	
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	@PostConstruct
	public void criarRepositorioDeFotos() {
		
		File f = new File(Constantes.PATH_ARMAZENAMENTO_FOTOS);
				
		if(!f.exists()){
			
			log.info("Pasta para armazenamento de fotos ainda não existe. Tentando criar pasta ...");
			
			if(f.mkdirs()){
				
				log.info("Pasta "+Constantes.PATH_ARMAZENAMENTO_FOTOS+" criada com sucesso!");
				
				File fTmp = new File(Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator + Constantes.NOME_PASTA_TMP_FOTOS);
				
				if(!fTmp.exists()){
					
					log.info("Pasta para armazenamento temporario de fotos ainda não existe. Tentando criar pasta ...");
					if(!fTmp.mkdirs()){
						Mensagem m = new Mensagem(Mensagem.ERRO, "Não conseguiu criar pasta de fotos " + Constantes.NOME_PASTA_TMP_FOTOS);
						throw new NegocioException(m);
					}							
					
					log.info("Pasta criada com sucesso!");
				}
				
				File fDefinitivo = new File(Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator + Constantes.NOME_PASTA_DEF_FOTOS);
				
				if(!fDefinitivo.exists()){
					
					log.info("Pasta para armazenamento definitivo de fotos ainda não existe. Tentando criar pasta ...");
					
					if(!fDefinitivo.mkdirs()){
						Mensagem m = new Mensagem(Mensagem.ERRO, "Não conseguiu criar pasta de fotos " + Constantes.NOME_PASTA_DEF_FOTOS);
						throw new NegocioException(m);
					}
					
					log.info("Pasta criada com sucesso!");
				}
			} else {
				Mensagem m = new Mensagem(Mensagem.ERRO, "Não conseguiu criar repositorio de fotos");
				throw new NegocioException(m);
			}
			
		}
	}
	
	@Schedule( minute = "30", hour="*", dayOfWeek="*")
	public void esvaziarPastaFotosTemporarias(){
		log.info("Esvaziando pasta para armazenamento temporario de fotos ...");
		//TODO: PENDENTE
	}
}
