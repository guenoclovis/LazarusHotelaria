package br.org.ufpr.tcc.facade;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.org.ufpr.tcc.util.Constantes;

public class EmailBC {
	
	private static final String SENHA_EMAIL_GOOGLE_EMPRESA = "SENHAAAA";
	
	public static void main(String[] args) {
		EmailBC s = new EmailBC();
		s.enviarEmail("clovis.gueno@gmail.com, arildo.gueno@gmail.com", "Testando envio de e-mail via java - teste2", "Chegou?");
	}

	public void enviarEmail(String destinatarios, String assunto, String mensagem) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constantes.EMAIL_GOOGLE_EMPRESA,SENHA_EMAIL_GOOGLE_EMPRESA);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constantes.EMAIL_GOOGLE_EMPRESA));
			
			InternetAddress[] iAddresses = InternetAddress.parse(destinatarios);
			
			message.setRecipients(Message.RecipientType.TO, iAddresses);
			message.setSubject(assunto);
			message.setText(mensagem);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}