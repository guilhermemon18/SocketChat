package server;

import java.io.PrintStream;

/*
Para cada usu�rio cadastrado no chat da K19 deve ser criado um objeto da classe EMISSOR.
A tarefa de um objeto da classe EMISSOR � enviar as mensagens do chat para o usu�rio
correspondente.*/
public class Emissor {


	private PrintStream saida;

	public Emissor(PrintStream saida) {
		this.saida = saida;
	}

	public void envia(String mensagem) {
		if(mensagem.equalsIgnoreCase("Desconectado")) {
			System.out.println("Pediu para desconectar");
			//Tem que fechar a conex�o com o socket que foi criado para esse Scanner
			//a classe da tela Chat j� guarda o socket l�, d� para fechar por l�.
			this.saida.close();
			this.saida = null;
		}else {
			this.saida.println(mensagem);
			System.out.println("Imprimindo mensagem no emissor: " + mensagem);
			//String[] newMensagem = mensagem.split(";");
		}
	}

	@Override
	public String toString() {
		return "Emissor [saida=" + saida + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return saida.equals(obj);
	}
	
	public  PrintStream getSaida() {
		return saida;
	}
}
