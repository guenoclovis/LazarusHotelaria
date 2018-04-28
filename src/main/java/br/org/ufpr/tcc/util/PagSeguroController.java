//package br.org.ufpr.tcc.util;
//
//import java.math.BigDecimal;
//import java.util.logging.Level;
//import javax.xml.ws.RequestWrapper;
//
//import org.jboss.resteasy.logging.Logger;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ibm.icu.impl.Utility;
//
//import br.com.uol.pagseguro.domain.AccountCredentials;
//import br.com.uol.pagseguro.domain.Credentials;
//import br.com.uol.pagseguro.domain.Item;
//import br.com.uol.pagseguro.domain.PaymentRequest;
//import br.com.uol.pagseguro.domain.Phone;
//import br.com.uol.pagseguro.domain.Sender;
//import br.com.uol.pagseguro.domain.Transaction;
//import br.com.uol.pagseguro.enums.Currency;
//import br.com.uol.pagseguro.exception.PagSeguroServiceException;
//import br.com.uol.pagseguro.service.NotificationService;
//
//@Controller
//@Scope(value = "request")
//public class PagSeguroController {
//
//	private final String EMAIL = "lazarushotelaria@gmail.com";
//	private final String TOKEN = "E156FE9A310341BF9E21B2D0D5B92EBE";
//
//	@RequestMapping(value = "/pagseguro-criarpagamento", produces = Utility.DEFAULT_PRODUCES_VALUE)
//	public @ResponseBody String criarPagamento() {
//		try {
//			PaymentRequest request = new PaymentRequest();
//			request.setReference("VND01");
//			request.setCurrency(Currency.BRL);
//			request.setSender(getSender());
//			request.addItem(getItem());
//			// Definir qual será a url que será recibida notifições no larazus
//			// (ADM)
//			request.setNotificationURL("http://licencascurae.doware.com.br/contatos/pagseguro-notificacao");
//			request.setRedirectURL("http://gueno.net");
//
//			return request.register(getCredentials());
//		} catch (PagSeguroServiceException ex) {
//			java.util.logging.Logger.getLogger(PagSeguroController.class.getName()).log(Level.SEVERE, null, ex);
//			return ex.getMessage();
//		}
//	}
//
//	private Credentials getCredentials() throws PagSeguroServiceException {
//
//		return new AccountCredentials(EMAIL, TOKEN);
//	}
//
//	// Dados do comprador
//	private Sender getSender() {
//		Sender sender = new Sender();
//		sender.setName("Clóvis");
//		sender.setEmail("lazarushotelaria@gmail.com");
//		sender.setPhone(new Phone("21", "9999999999999999"));
//
//		return sender;
//
//	}
//
//	private Item getItem() {
//		Item item = new Item();
//		item.setId("001");
//		item.setDescription("Quarto Simplesmente");
//		item.setQuantity(1);
//		item.setAmount(new BigDecimal("10.00"));
//
//		return item;
//	}
//
//	@RequestMapping(value = "/pagseguro-notificacao", method = requestMethod.POST)
//	public @ResponseBody
//	String registrarNotificacao(
//			@RequestParam(value = "notificationCode") String nCode,
//			@RequestParam(value = "notificationType") String nType)
//	{
//		try{
//			Transaction transaction = NotificationService.checkTransaction(getCredentials(), nCode);
//			
//			switch (transaction.getStatus()){
//				case PAID:
//					//TRANSAÇÃO PAGA
//					break;
//					
//				case CANCELLED:
//					//TRANSAÇÃO CANCELADA
//					break;
//				
//				case WAITING_PAYMENT:
//					//TRANSAÇÃO AGUARDANDO PAGAMENTO
//					break;
//				
//				case IN_ANALYSIS:
//				// TRANSAÇÃO 
//				break;
//			}
//		}
//		catch(PagSeguroServiceException ex){
//			java.util.logging.Logger.getLogger(PagSeguroController.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		
//		return "1";
//		
//	}
//
//}