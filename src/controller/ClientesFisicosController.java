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
import model.Endereço;

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
		String cmd = e.getActionCommand(); 
			
		try {
			if (cmd.equals("Cadastrar")) {
				cadastro();
			}
			if (cmd.equals("Buscar")) {
				busca();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	private void cadastro() throws IOException {

		ClientesFisico cliente = new ClientesFisico();
		cliente.endereco = new Endereço();

		cliente.cpf = tfCpf.getText();
		cliente.nome = tfNomeCliente.getText();
		cliente.endereco.logradouro = tfLogradouro.getText();
		cliente.endereco.numeroPorta = tfNumero.getText();
		cliente.endereco.complemento = tfComplemento.getText();
		cliente.endereco.cep = tfCep.getText();
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
		File arq = new File(path, "clientes.csv");

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
		cliente.endereco = new Endereço();

		cliente.nome = tfNomeCliente.getText();

		cliente = buscaCliente(cliente);

		if (cliente.nome != null) {
			taClientesFisicos.setText("CPF - " + cliente.cpf + " / Nome - " + cliente.nome + " / Endereco "
					+ cliente.endereco.logradouro + " / N° - " + cliente.endereco.numeroPorta + " / Complemento - " + cliente.endereco.complemento
					+ " / CEP - " + cliente.endereco.cep + " / Celular - " + cliente.celular);
		}
		limpaLinhas();
	}

	private ClientesFisico buscaCliente(ClientesFisico cliente) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "clientes.csv");

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
					cliente.endereco.logradouro = vetLinha[2];
					cliente.endereco.numeroPorta = vetLinha[3];
					cliente.endereco.complemento = vetLinha[4];
					cliente.endereco.cep = vetLinha[5];
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
