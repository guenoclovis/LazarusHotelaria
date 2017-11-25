package br.org.ufpr.tcc.facade;

import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.DadosEmailDTO;
import br.org.ufpr.tcc.util.Constantes;

public class ContatoFacade {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	public void enviarEmail(DadosEmailDTO dadosEmail) {
		String logMsg = "Iniciando o envio de e-mail";

		log.info(logMsg);

		String mensagem = "Nome: " + dadosEmail.getNome() + "\nTelefone: " + dadosEmail.getTelefone() + "\nE-mail: "
				+ dadosEmail.getEmail() + "\n\n" + "Mensagem:\n" + dadosEmail.getMensagem();
		
		dadosEmail.setAssunto("E-mail enviado pelo formul&aacute;rio de Contato");

		EmailBC bc = new EmailBC();
		bc.enviarEmail(Constantes.EMAIL_GOOGLE_EMPRESA, dadosEmail.getAssunto(), mensagem);

		logMsg = "E-mail enviado";
		log.info(logMsg);
	}

}
