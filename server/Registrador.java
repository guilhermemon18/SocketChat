package server;

import java.io.IOException;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*Na aplicação servidora, um objeto registrador deve esperar novos usuários do chat 
e realizar todo processo de registro de novos usuários quando alguém chegar.*/
public class Registrador implements Runnable{

	private Distribuidor distribuidor;
	private ServerSocket serverSocket;
	//private List<Socket> clients;

	public Registrador(Distribuidor distribuidor, ServerSocket serverSocket) {
		this.distribuidor = distribuidor;
		this.serverSocket = serverSocket;

	}
	
	public void removeEmissor() {
		this.distribuidor.removeEmissor();
	}

	public void run() {
		while (true) {
			try {
				//aceita a conexão e retorna uma referencia para o socket do novo cliente que foi criado lá pela classe Client.
				Socket socket = this.serverSocket.accept();
				//provavelmente será aqui o controle dos clientes conectados, aqui ele pega uma nova conexão com o socket e cria emissores e receptores para ela.
				
				System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
				
				Scanner entrada = new Scanner(socket.getInputStream());
				PrintStream saida = new PrintStream(socket.getOutputStream());

				Receptor receptor = new Receptor(entrada, this.distribuidor);
				Thread pilha = new Thread(receptor);
				pilha.start();

				Emissor emissor = new Emissor(saida);

				this.distribuidor.adicionaEmissor(emissor);

			} catch (IOException e) {
				System.out.println("ERRO");
			}
		}

	}
}
