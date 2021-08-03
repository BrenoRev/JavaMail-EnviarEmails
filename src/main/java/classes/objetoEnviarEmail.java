package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

			/* Olhe as configurações do SMTP do email */
			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true"); /* Autorização */

			properties.put("mail.smtp.starttls", "true"); /* Autenticação */

			properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */

			properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */

			properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */

			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */

			properties.put("mail.smtp.ssl.trust", "*"); /* Cria uma Autenticação SSL */

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
			 * Criando uma sessão passando como parâmetro as propriedades e o autenticador
			 */
			Session session = Session.getInstance(properties, authenticator);

			// Lista de emails destinatarios
			Address[] toUser = InternetAddress.parse(listaDestinarios);

			Message message = new MimeMessage(session);
			InternetAddress remetente = new InternetAddress(username, nomeRemetente); /* Quem está enviando */
			message.setFrom(remetente); // De quem
			message.setRecipients(Message.RecipientType.TO, toUser); // Para quem
			message.setSubject(assunto); // Assunto do email

			// Se a condição for verdadeira, ele vai transformar a mensagem digitada em HTML
			// e enviar
			if (envioHTML) {
				message.setContent(mensagem, "text/html; charset=utf-8");
			}
			// Caso não, a mensagem é enviada da forma que foi escrita.
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

	/*
	 * Este método simula o PDF ou qualquer arquivo que vai ser enviado no email O
	 * arquivo pode ser pego no banco de dados
	 * Retorna um PDF com o texto do paragrafo.
	 */
	private FileInputStream simulardorPDF() throws Exception {
		// Cria um novo documento da biblioteca do itext
		Document document = new Document();
		// Cria um novo arquivo chamado "anexo.pdf"
		File file = new File("Anexo.pdf");
		file.createNewFile();
		// Cria um escritor de pdf recebendo como parametro o documento e o pdf 
		PdfWriter.getInstance(document, new FileOutputStream(file));
		
		// Modifica o PDF
		document.open();
		document.add(new Paragraph("Conteúdo do PDF anexo com Java Mail"));
		document.addAuthor("Breno Dev");
		document.close();
		
		// Retorna um novo PDF
		return new FileInputStream(file);
	}
	
	public void enviarAnexo() {
		try {

			/* Olhe as configurações do SMTP do email */
			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true"); /* Autorização */

			properties.put("mail.smtp.starttls", "true"); /* Autenticação */

			properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor do Gmail */

			properties.put("mail.smtp.port", "465"); /* Porta do Servidor do Google */

			properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */

			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */

			properties.put("mail.smtp.ssl.trust", "*"); /* Cria uma Autenticação SSL */

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
			 * Criando uma sessão passando como parâmetro as propriedades e o autenticador
			 */
			Session session = Session.getInstance(properties, authenticator);

			// Lista de emails destinatarios
			Address[] toUser = InternetAddress.parse(listaDestinarios);

			Message message = new MimeMessage(session);
			InternetAddress remetente = new InternetAddress(username, nomeRemetente); /* Quem está enviando */
			message.setFrom(remetente); // De quem
			message.setRecipients(Message.RecipientType.TO, toUser); // Para quem
			message.setSubject(assunto); // Assunto do email

			/* Parte 1 do e-mail, texto e descrição do e-mail */
			MimeBodyPart corpoEmail = new MimeBodyPart();
					
				corpoEmail.setContent(mensagem, "text/html; charset=utf-8");
			
			/* Parte 2 do e-mail , Anexos em PDF ou qualquer outra coisa */
			MimeBodyPart anexoEmail = new MimeBodyPart();
			// Passa o método que cria o simulador como parametro e um nome pra ele
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simulardorPDF(), "application/pdf")));
			// da um nome ao pdf
			anexoEmail.setFileName("Anexo.pdf");
			
			/* Junta as 2 partes em uma só */
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			message.setContent(multipart);
			
			// Enviar a mensagem
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Email enviado com sucesso!");
	}
}
