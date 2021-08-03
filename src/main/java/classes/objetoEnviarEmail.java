package classes;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class objetoEnviarEmail {

	final String username = "counterstrikerevgamer@gmail.com";
	final String senha = "brenodev123";
	
	private String listaDestinarios = "";
	private String nomeRemetente = "";
	private String assunto = "";
	private String mensagem = "";
	
	
	
	public objetoEnviarEmail(String listaDestinarios, String nomeRemetente, String assunto, String mensagem) {
		this.listaDestinarios = listaDestinarios;
		this.nomeRemetente = nomeRemetente;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}



	public void enviarEmail(boolean envioHTML) {
		try {
			
			/* Olhe as configura��es do SMTP do email */
			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true"); /* Autoriza��o */

			properties.put("mail.smtp.starttls", "true"); /* Autentica��o */

			properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */

			properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */

			properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */

			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conex�o ao SMTP */

			properties.put("mail.smtp.ssl.trust", "*"); /* Cria uma Autentica��o SSL */

			/*
			 * Criando o autenticador com login e senha do email que vai enviar as mensagens
			 */
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, senha);
				}
			};

			/*
			 * Criando uma sess�o passando como par�metro as propriedades e o autenticador
			 */
			Session session = Session.getInstance(properties, authenticator);

			// Lista de emails destinatarios
			Address[] toUser = InternetAddress.parse(listaDestinarios);

			Message message = new MimeMessage(session);
			InternetAddress remetente = new InternetAddress(username, nomeRemetente); /* Quem est� enviando */
			message.setFrom(remetente); // De quem
			message.setRecipients(Message.RecipientType.TO, toUser); // Para quem
			message.setSubject(assunto); // Assunto do email
			
			// Se a condi��o for verdadeira, ele vai transformar a mensagem digitada em HTML e enviar
			if(envioHTML) {
				message.setContent(mensagem, "text/html; charset=utf-8"); 
			}
			// Caso n�o, a mensagem � enviada da forma que foi escrita.
			else {
			message.setText(mensagem); // Mensagem do email
			}
			// Enviar a mensagem
			Transport.send(message);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Email enviado com sucesso!");
	}

}
