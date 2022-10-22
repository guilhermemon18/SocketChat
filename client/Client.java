package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.Chat;

public class Client {
	

	//Componentes do cliente:
	private Socket socket;// aqui está a conexão que deve ser encerrada ao terminar o chat.
	private PrintStream saida;
	private Scanner entrada;
	private EmissorDeMensagem emissor;
	private Chat telaChat;//tela para visualizar as ações
	private ReceptorDeMensagem receptor;//receptor para receber as informações e setar na tela.
	private Thread pilha;//a thread para que flua.

	private JTextField txtIP;
	private JTextField txtPorta;
	private JTextField txtNome;

	//construtor do cliente.
	public Client(String nomeCliente) throws UnknownHostException, IOException {
		socket = new Socket("localhost", 10000);
		saida = new PrintStream(socket.getOutputStream());
		entrada = new Scanner(socket.getInputStream());
		emissor = new EmissorDeMensagem(saida);
		telaChat = new Chat(emissor,nomeCliente, socket);
		receptor = new ReceptorDeMensagem(entrada,
				telaChat);
		pilha = new Thread(receptor);
		pilha.start();
	}
	
	//construtor do cliente.
	public Client() throws UnknownHostException, IOException {
		JLabel lblMessage = new JLabel("Criando Cliente!");
	    txtIP = new JTextField();
		JLabel lblinserirnome = new JLabel("Insira o nome!");
	    txtNome = new JTextField();
	    
	    Object[] texts = {lblMessage, lblinserirnome, txtPorta, txtNome,new JLabel("Insira o IP"), txtIP };
	    JOptionPane.showMessageDialog(null, texts);
		socket = new Socket(txtIP.getText(), 10000);
		

		saida = new PrintStream(socket.getOutputStream());
		entrada = new Scanner(socket.getInputStream());
		emissor = new EmissorDeMensagem(saida);
		telaChat = new Chat(emissor,txtNome.getText(), socket);
		receptor = new ReceptorDeMensagem(entrada,
				telaChat);
		pilha = new Thread(receptor);
		pilha.start();
	}
	
	


	public static void main(String[] args)  {
		
		//instancia 2 clientes para conversarem
			try {
				new Client();
				System.out.println("Criando os cliente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		


	}
}
