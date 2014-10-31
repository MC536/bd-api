package utils.mail;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by joaochencci on 03/09/14.
 */
public class MailJava {

	private static void sendMail(List<String> mailList, StringBuilder builder, String subject) {
		//SimpleEmail email = new SimpleEmail();
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSslSmtpPort("465");
		email.setStartTLSRequired(true);
		email.setSSLOnConnect(true);
		email.setAuthenticator(new DefaultAuthenticator("noreply@clubedaentrega.com.br", "thoushaltnotreplytothisemailever"));
		try {
			email.setFrom("noreply@clubedaentrega.com.br");

			email.setDebug(true);

			email.setSubject(subject);
			//email.setMsg( "Texto sem formatação" );

			email.setHtmlMsg(builder.toString());

			for (String emailTo : mailList) {
				email.addTo(emailTo);
			}

			email.send();

		}
		catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void sendRecoverPasswordMail(String emailTo, String secret) {
		StringBuilder builder = new StringBuilder();
		builder.append("<h1>Recuperar Senha</h1>");
		builder.append("<p>Utilize o link abaixo para recuperar sua senha</p>");
		builder.append(
				"<a href=\"http://dev.accounts.webserver.clubedaentrega.com.br:3333/signup?secret=" + secret + "\">Contas Corporativas - Clube da Entrega</a> <br> ");

		List<String> mailList = new ArrayList<String>();
		mailList.add(emailTo);

		sendMail(mailList, builder, "[CdE Contas Corporativas] Recuperação de Senha");
	}

	public static void sendCreateUserMail(String emailTo, String secret) {
		StringBuilder builder = new StringBuilder();
		builder.append("<h1>Bem vindo ao CdE Contas Corporativas!</h1>");
		builder.append("<p>Utilize o link abaixo para continuar seu cadastro</p>");
		builder.append(
				"<a href=\"http://dev.accounts.webserver.clubedaentrega.com.br:3333/signup?secret=" + secret + "\">Contas Corporativas - Clube da Entrega</a> <br> ");

		List<String> mailList = new ArrayList<String>();
		mailList.add(emailTo);

		sendMail(mailList, builder, "[CdE Contas Corporativas] Nova Conta");
	}
}
