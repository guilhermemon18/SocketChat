package server;

import java.util.ArrayList;
import java.util.Collection;

/*Na aplicação servidora, deve existir um objeto da classe DISTRIBUIDOR que tem como
tarefa receber as mensagens dos receptores e repassá-las para os emissores.*/
public class Distribuidor {


	private Collection<Emissor> emissores = new ArrayList<Emissor>();

	public void adicionaEmissor(Emissor emissor) {
		this.emissores.add(emissor);
	}
	
	//método novo para remover um emissor que vier a se desconectar do chat.
	public void removeEmissor() {
		for (Emissor emissor : emissores) {
			if(emissor.getSaida() == null) {
				emissores.remove(emissor);
			}
		}
	}

	public void distribuiMensagem(String mensagem) {
		String hora = mensagem.substring(0,8);
		String[] newMensagem = mensagem.split(";");
		
		for (Emissor emissor : this.emissores) {
			System.out.println(emissor);
			System.out.println(mensagem);
			emissor.envia(newMensagem[2] + " " + hora + ": " + newMensagem[1] );
		}
		if(newMensagem[1].equalsIgnoreCase("Desconectado")) {
			removeEmissor();
		}
	}

}
