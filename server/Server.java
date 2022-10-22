package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args) throws IOException {
		Distribuidor distribuidor = new Distribuidor();

		ServerSocket serverSocket = new ServerSocket(10000);

		//O registrador ele já tem o serversocket lá nele,então dá para acessar por lá.
		Registrador registrador = new Registrador(distribuidor, serverSocket);
		Thread pilha = new Thread(registrador);
		pilha.start();
		System.out.println("Criou o server!");
		System.out.println("Servidor ouvindo a porta 10000");
	}

}
