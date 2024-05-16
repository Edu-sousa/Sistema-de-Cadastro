package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ClientesFisico;
import model.Endere�o;

public class ClientesFisicosController implements ActionListener {

	private JTextField tfCpf;
	private JTextField tfNomeCliente;
	private JTextField tfLogradouro;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JTextField tfCep;
	private JTextField tfCelular;
	private JTextArea taClientesFisicos;

	public ClientesFisicosController(JTextField tfCpf, JTextField tfNomeCliente, JTextField tfLogradouro,
			JTextField tfNumero, JTextField tfComplemento, JTextField tfCep, JTextField tfCelular,
			JTextArea taClientesFisicos) {
		super();
		this.tfCpf = tfCpf;
		this.tfNomeCliente = tfNomeCliente;
		this.tfLogradouro = tfLogradouro;
		this.tfNumero = tfNumero;
		this.tfComplemento = tfComplemento;
		this.tfCep = tfCep;
		this.tfCelular = tfCelular;
		this.taClientesFisicos = taClientesFisicos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); // Pega exatamente o nome escrito no bot�o
		if (cmd.equals("Cadastrar")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			try {
				busca();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void cadastro() throws IOException {

		ClientesFisico cliente = new ClientesFisico();
		cliente.endere�o = new Endere�o();

		cliente.cpf = tfCpf.getText();
		cliente.nome = tfNomeCliente.getText();
		cliente.endere�o.logradouro = tfLogradouro.getText();
		cliente.endere�o.numeroPorta = tfNumero.getText();
		cliente.endere�o.complemento = tfComplemento.getText();
		cliente.endere�o.cep = tfCep.getText();
		cliente.celular = tfCelular.getText();

		cadastraCliente(cliente.toString());

		limpaLinhas();
	}

	private void cadastraCliente(String csvCliente) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "cliente.csv");

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvCliente + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

		System.out.println("cadastrado com sucesso");
	}

	private void busca() throws IOException {
		ClientesFisico cliente = new ClientesFisico();
		cliente.endere�o = new Endere�o();
		
		cliente.nome = tfNomeCliente.getText();

		cliente = buscaCliente(cliente);

		if (cliente.nome != null) {
			taClientesFisicos.setText("CPF - " + cliente.cpf + " / Nome - " + cliente.nome + " / Endereco "
					+ cliente.endere�o.logradouro + " / N� - " + cliente.endere�o.numeroPorta + " / Complemento - "
					+ cliente.endere�o.complemento + " / CEP - " + cliente.endere�o.cep + " / Celular - "
					+ cliente.celular);
		}
		limpaLinhas();
	}

	private ClientesFisico buscaCliente(ClientesFisico cliente) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "cliente.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(cliente.nome)) {
					cliente.cpf = vetLinha[0];
					cliente.nome = vetLinha[1];
					cliente.endere�o.logradouro = vetLinha[2];
					cliente.endere�o.numeroPorta = vetLinha[3];
					cliente.endere�o.complemento = vetLinha[4];
					cliente.endere�o.cep = vetLinha[5];
					cliente.celular = vetLinha[6];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return cliente;
	}

	public void limpaLinhas() {
		tfCpf.setText("");
		tfNomeCliente.setText("");
		tfLogradouro.setText("");
		tfNumero.setText("");
		tfComplemento.setText("");
		tfCep.setText("");
		tfCelular.setText("");
	}
}