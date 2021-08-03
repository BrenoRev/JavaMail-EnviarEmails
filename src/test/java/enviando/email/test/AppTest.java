package enviando.email.test;

import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {

	@Test
	public void testeEmail() {
		
		 final String username = "counterstrikerevgamer@gmail.com";
		 final String senha = "brenodev123";
		 
		try {
		/* Olhe as configurações do SMTP do email */
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true"); /* Autorização */
		
		properties.put("mail.smtp.starttls", "true"); /* Autenticação */
		
		properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */
		
		properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */
		
		properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */
		
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */
		
		properties.put("mail.smtp.ssl.trust", "*"); /* Cria uma Autenticação SSL */
		
		/* Criando o autenticador com login e senha do email que vai enviar as mensagens */
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, senha);
			}
		};
		
		/* Criando uma sessão passando como parâmetro as propriedades e o autenticador */
		Session session = Session.getInstance(properties, authenticator );
		
		// Lista de emails destinatarios
		Address[] toUser = InternetAddress.parse("silvabreno462@gmail.com, elianemaria8858@gmail.com, counterstrikerevgamer@gmail.com");
		
		Message message = new MimeMessage(session);
		InternetAddress remetente = new InternetAddress(username, "Breno Silva"); /* Quem está enviando */
		message.setFrom(remetente); // De quem
		message.setRecipients(Message.RecipientType.TO, toUser); // Para quem
		message.setSubject("Chegou o e-mail enviado com java"); // Assunto do email
		message.setText("Mensagem enviada agora em: " + LocalDate.now()); // Mensagem do email
		// Enviar a mensagem
		Transport.send(message);
		
		// TEMPO DE ESPERA PARA NÃO SOBRECARREGAR
		Thread.sleep(5000);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
