package server;

import java.io.PrintStream;

/*
Para cada usuário cadastrado no chat da K19 deve ser criado um objeto da classe EMISSOR.
A tarefa de um objeto da classe EMISSOR é enviar as mensagens do chat para o usuário
correspondente.*/
public class Emissor {


	private PrintStream saida;

	public Emissor(PrintStream saida) {
		this.saida = saida;
	}

	public void envia(String mensagem) {
		if(mensagem.equalsIgnoreCase("Desconectado")) {
			System.out.println("Pediu para desconectar");
			//Tem que fechar a conexão com o socket que foi criado para esse Scanner
			//a classe da tela Chat já guarda o socket lá, dá para fechar por lá.
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
