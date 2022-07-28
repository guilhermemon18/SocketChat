package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import view.Chat;

public class Client {
	
	private Socket socket;
	private PrintStream saida;
	private Scanner entrada;
	private EmissorDeMensagem emissor;
	private Chat telaChat;
	private ReceptorDeMensagem receptor;
	private Thread pilha;

	//construtor do cliente.
	public Client(String nomeCliente) throws UnknownHostException, IOException {
		socket = new Socket("localhost", 10000);
		saida = new PrintStream(socket.getOutputStream());
		entrada = new Scanner(socket.getInputStream());
		emissor = new EmissorDeMensagem(saida);
		telaChat = new Chat(emissor,nomeCliente);
		receptor = new ReceptorDeMensagem(entrada,
				telaChat);
		pilha = new Thread(receptor);
		pilha.start();
	}


	public static void main(String[] args)  {
		
		//instancia 2 clientes para conversarem
			try {
				Client c1 = new Client("Client1");
				Client c2 = new Client("Client2");
				System.out.println("Criando os clientes!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		


	}
}
