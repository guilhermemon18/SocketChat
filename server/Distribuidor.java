package server;

import java.util.ArrayList;
import java.util.Collection;

public class Distribuidor {


	private Collection<Emissor> emissores = new ArrayList<Emissor>();

	public void adicionaEmissor(Emissor emissor) {
		this.emissores.add(emissor);
	}

	public void distribuiMensagem(String mensagem) {
		String hora = mensagem.substring(0,8);
		String[] newMensagem = mensagem.split(";");
		for (Emissor emissor : this.emissores) {
			emissor.envia(newMensagem[2] + " " + hora + ": " + newMensagem[1] );
		}
	}

}
