package client;

import java.util.Scanner;

import view.Chat;

public class ReceptorDeMensagem implements Runnable {

	private Scanner entrada;

	private Chat telaChat;

	public ReceptorDeMensagem(Scanner entrada, Chat telaChat) {
		this.entrada = entrada;
		this.telaChat = telaChat;
	}

	public void run() {
		while (this.entrada.hasNextLine()) {
			String mensagem = this.entrada.nextLine();
			this.telaChat.adicionaMensagem(mensagem);
		}
	}

}
