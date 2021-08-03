package br.com.send;

import java.util.Scanner;

import classes.objetoEnviarEmail;

public class App {

	private static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);
		
		// HTML
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		stringBuilderTextoEmail.append("<div style=\"width: fit-content; height: fit-content; margin:auto\">"
				+ "<h2>Você está recebendo o acesso ao curso do RevDev </h2> <br>"
				+ "</div>");
		
		stringBuilderTextoEmail.append("<hr>"
				+ "<div style=\"width: fit-content; height: fit-content; margin:auto; color:grey;\">"
				+ "<a href=\"https://github.com/BrenoRev\"target=\"_blank\" "
				+ "style=\"color:red; padding:20px; text-align: center; text-decoration: none; display:inline-block; border-radius: 30px; "
				+ "font-size: 20px; font-family: courier; border: 3px solid green;\">"
				+ "Clique aqui para acessar</a>"
				+ "</div>"
				+ "<hr>");
		
		System.out.print("Email do destinatário: ");
		String dest = teclado.nextLine();
		System.out.print("Nome do remetente: ");
		String rem = teclado.nextLine();
		System.out.print("Assunto do email: ");
		String assunto = teclado.nextLine();

		/* Envio sem HTML */
		//System.out.println("Texto do email: ");
		//String texto = teclado.nextLine();
		
		String texto = stringBuilderTextoEmail.toString();
		objetoEnviarEmail sendEmail = new objetoEnviarEmail(dest, rem, assunto, texto);
		System.out.println("Aguarde enquanto enviamos o seu email...");
		
		// Enviar com HTML
		sendEmail.enviarEmail(true);
		
		// Enviar sem HTML
		sendEmail.enviarEmail(false);
		
	}

}
