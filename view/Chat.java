package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import client.EmissorDeMensagem;

public class Chat {

	private String nome;
	private final JFrame frame;
	private final JPanel panel;
	private final JScrollPane scrollPane;
	private final JTextArea textArea1;
	private final JLabel label1;
	private final JTextField textField;
	private final JButton button;

	private final EmissorDeMensagem emissorDeMensagem;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnLimpar;

	public Chat(EmissorDeMensagem emissor, String nome) {
		this.nome = nome;
		this.emissorDeMensagem = emissor;

		this.frame = new JFrame( nome);
		this.panel = new JPanel();
		this.textArea1 = new JTextArea(10, 60);
		this.textArea1.setEditable(false);
		this.scrollPane = new JScrollPane(this.textArea1);


		this.frame.setContentPane(this.panel);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		this.textField = new JTextField(60);
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setForeground(Color.BLACK);
		this.label1 = new JLabel("Digite uma mensagem + Enter...");
		panel_1.add(label1, BorderLayout.WEST);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		this.button = new JButton("Enviar");
		panel_2.add(button);
		
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea1.setText("");
			}
		});
		panel_2.add(btnLimpar);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){  
					LocalTime agora = LocalTime.now();
					emissorDeMensagem.envia( agora + ";" + textField.getText() + ";" + nome);
					textField.setText("");
				}
			}
		});


		this.panel.add(this.scrollPane);

		class EnviaMensagemListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				LocalTime agora = LocalTime.now();
				emissorDeMensagem.envia( agora + ";" + textField.getText() + ";" + nome);
				textField.setText("");
			}
		}
		this.button.addActionListener(new EnviaMensagemListener());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(700, 300);
		this.frame.setVisible(true);

	}

	public void appendToPane(JTextPane tp, String txt, Color clr) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, clr);
		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Serif");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(txt);
	}

	public void adicionaMensagem(String mensagem) {
		String[] aux = mensagem.split(" ");
		if(aux[0].equalsIgnoreCase(nome)) {
			this.textArea1.append(mensagem.replace(aux[0], "Você") + "\n");
		}else {
			this.textArea1.append(mensagem + "\n");
		}


	}




}
