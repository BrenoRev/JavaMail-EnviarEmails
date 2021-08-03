package br.com.send;

import java.util.Scanner;

import classes.objetoEnviarEmail;

public class App {

	private static Scanner teclado;

	public static void main(String[] args) {
		teclado = new Scanner(System.in);
		
		System.out.print("Email do destinatário: ");
		String dest = teclado.nextLine();
		System.out.print("Nome do remetente: ");
		String rem = teclado.nextLine();
		System.out.print("Assunto do email: ");
		String assunto = teclado.nextLine();
		System.out.println("Texto do email: ");
		String texto = teclado.nextLine();
		teclado.nextLine();
		objetoEnviarEmail sendEmail = new objetoEnviarEmail(dest, rem, assunto, texto);
		System.out.println("Aguarde enquanto enviamos o seu email...");
		sendEmail.enviarEmail();
		
	}

}
