package enviando.email.test;

import java.util.Properties;

import org.junit.Test;

public class AppTest {

	@Test
	public void testeEmail() {
		
		/* Olhe as configurações do SMTP do email */
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true"); /* Autorização */
		
		properties.put("mail.smtp.starttls", "true"); /* Autenticação */
		
		properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */
		
		properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */
		
		properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */
		
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */
		
	}
}
