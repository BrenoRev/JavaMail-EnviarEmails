package enviando.email.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.junit.Test;

public class AppTest {

	@Test
	public void testeEmail() {
		
		final String username = "counterstrikerevgamer@gmail.com";
		final String senha = "brenodev123";
		
		try {
		/* Olhe as configura��es do SMTP do email */
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true"); /* Autoriza��o */
		
		properties.put("mail.smtp.starttls", "true"); /* Autentica��o */
		
		properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */
		
		properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */
		
		properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */
		
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /* Classe socket de conex�o ao SMTP */
		
		/* Criando o autenticador com login e senha do email que vai enviar as mensagens */
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,senha);
			}
		};
		
		/* Criando uma sess�o passando como par�metro as propriedades e o autenticador */
		Session session = Session.getInstance(properties, authenticator );
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
