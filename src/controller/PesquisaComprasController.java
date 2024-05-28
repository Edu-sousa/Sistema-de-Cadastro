package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PesquisaComprasController implements ActionListener {

	private JTextField tfConsultaNome;
	private JTextArea taConsultaCompra;

	public PesquisaComprasController(JTextField tfConsultaNome, JTextArea taConsultaCompra) {
		super();
		this.tfConsultaNome = tfConsultaNome;
		this.taConsultaCompra = taConsultaCompra;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Pesquisar")) {
			try {
				pesquisaCliente();
				tfConsultaNome.setText("");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void pesquisaCliente() throws IOException {
		
		StringBuffer br = new StringBuffer();

		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "compras.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(tfConsultaNome.getText())) {
						br.append(linha+"\n");					
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		if(br.toString().equals("")) {
			taConsultaCompra.setText("Cliente não encontrado");
		}else
			taConsultaCompra.setText(br.toString());
	}
}
