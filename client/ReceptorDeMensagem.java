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
		while (this.entrada.hasNextLine()) {//enquanto a entrada tiver mensagem ele obtem a msg e coloca na tela do chat, funciona bem para o chat global.
			String mensagem = this.entrada.nextLine();
			this.telaChat.adicionaMensagem(mensagem);
		}
	}

}
