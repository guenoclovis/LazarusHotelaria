/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */
package br.org.ufpr.tcc.bc;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;
import br.org.ufpr.tcc.entity.Checkout;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.exception.handler.NegocioException;

/**
 * @author PagSeguro Internet Ltda.
 */
public class CheckoutRegisterBC {
	
	public String gerarCompraNoPagSeguro(Checkout checkout){
		try {

			String sellerEmail = "lazarushotelaria@gmail.com";
			String sellerToken = "E156FE9A310341BF9E21B2D0D5B92EBE";
			
			Credential credential = Credential.sellerCredential(sellerEmail, sellerToken);
			//Credential credential = Credential.applicationCredential("app8230426496", "96229D705454A21EE4037FA4A52D642C");
			
			final PagSeguro pagSeguro = PagSeguro.instance(
					new SimpleLoggerFactory(), 
					new JSEHttpClient(),
					credential, 
					PagSeguroEnv.SANDBOX);

			// Criando um checkout
			CheckoutRegistrationBuilder checkoutRegister = new CheckoutRegistrationBuilder() //
					.withCurrency(checkout.getCurrency()) //
					.withExtraAmount(checkout.getExtraAmount()) //
					//.withReference("XXXXXX")
					.withSender(new SenderBuilder()//
							.withEmail(checkout.getEmail())//
							.withName(checkout.getNome()).withCPF(checkout.getCpf())
							.withPhone(new PhoneBuilder()//
									.withAreaCode(checkout.getAreaCode()) //
									.withNumber(checkout.getNumber()))) //
					

					.addItem(new PaymentItemBuilder()//
							.withId(checkout.getIdReserva().toString())//
							.withDescription(checkout.getDescription()) //
							.withAmount(checkout.getAmount())//
							.withQuantity(checkout.getQuantity()).withWeight(1000))


					.withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
							.addInclude(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
							.addInclude(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BANK_SLIP)))

					.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
							.withPaymentMethod(
									new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
							.withConfig(new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT)
									.withValue(new BigDecimal(10.00))))
					.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
							.withPaymentMethod(
									new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BANK_SLIP))
							.withConfig(new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT)
									.withValue(new BigDecimal(0.01))))

					.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
							.withPaymentMethod(
									new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
							.withConfig(new ConfigBuilder().withKey(ConfigKey.MAX_INSTALLMENTS_LIMIT)
									.withValue(new BigDecimal(10))))
					.addPaymentMethodConfig(new PaymentMethodConfigBuilder()
							.withPaymentMethod(
									new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
							.withConfig(new ConfigBuilder().withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
									.withValue(new BigDecimal(5))));
			
			RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(checkoutRegister);
			
			return registeredCheckout.getCheckoutCode();

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(new Mensagem(Mensagem.ERRO, e.getMessage()));
		}
	}
	
}