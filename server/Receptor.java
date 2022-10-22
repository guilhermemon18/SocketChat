package server;

import java.util.Scanner;

/*Para cada usuário cadastrado no chat da K19 deve ser criado um objeto da classe RECEPTOR.
A tarefa de um objeto da classe RECEPTOR é aguardar as mensagens enviadas pelo
usuário correspondente.*/
public class Receptor implements Runnable {

	private Scanner entrada;
	private Distribuidor distribuidor;

	public Receptor(Scanner entrada, Distribuidor distribuidor) {
		this.entrada = entrada;
		this.distribuidor = distribuidor;
	}

	public void run() {
		while ( this.entrada != null &&this.entrada.hasNextLine()) {
			String mensagem = this.entrada.nextLine();
			
			String[] newMensagem = mensagem.split(";");
			System.out.println("Imprimindo msg no receptor: " + newMensagem[1]);
			if(newMensagem[1].equalsIgnoreCase("desconectado")) {
				System.out.println("Pediu para desconectar");
				//Tem que fechar a conexão com o socket que foi criado para esse Scanner
				//a classe da tela Chat já guarda o socket lá, dá para fechar por lá.
				this.entrada.close();
				this.entrada = null;
			}
			this.distribuidor.distribuiMensagem(mensagem);
		}
	}


}

