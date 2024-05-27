package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ClienteJuridico;
import model.Endereço;

public class ClientesJuridicosController implements ActionListener {

	private JTextField tfCnpj;
	private JTextField tfNomeJuri;
	private JTextField tfLogradouroJuri;
	private JTextField tfNumeroJuri;
	private JTextField tfComplementoJuri;
	private JTextField tfCepJuri;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextArea taClientesJuri;

	public ClientesJuridicosController(JTextField tfCnpj, JTextField tfNomeJuri, JTextField tfLogradouroJuri,
			JTextField tfNumeroJuri, JTextField tfComplementoJuri, JTextField tfCepJuri, JTextField tfTelefone,
			JTextField tfEmail, JTextArea taClienetesJuri) {
		super();
		this.tfCnpj = tfCnpj;
		this.tfNomeJuri = tfNomeJuri;
		this.tfLogradouroJuri = tfLogradouroJuri;
		this.tfNumeroJuri = tfNumeroJuri;
		this.tfComplementoJuri = tfComplementoJuri;
		this.tfCepJuri = tfCepJuri;
		this.tfTelefone = tfTelefone;
		this.tfEmail = tfEmail;
		this.taClientesJuri = taClienetesJuri;
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
		ClienteJuridico empresa = new ClienteJuridico();
		empresa.endereco = new Endereço();

		empresa.cnpj = tfCnpj.getText();
		empresa.nome = tfNomeJuri.getText();
		empresa.endereco.logradouro = tfLogradouroJuri.getText();
		empresa.endereco.numeroPorta = tfNumeroJuri.getText();
		empresa.endereco.complemento = tfComplementoJuri.getText();
		empresa.endereco.cep = tfCepJuri.getText();
		empresa.telefone = tfTelefone.getText();
		empresa.email = tfEmail.getText();

		cadastraCliente(empresa.toString());

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
		ClienteJuridico empresa = new ClienteJuridico();
		empresa.endereco = new Endereço();

		empresa.nome = tfNomeJuri.getText();

		empresa = buscaCliente(empresa);

		if (empresa.nome != null) {
			taClientesJuri.setText("CNPJ - " + empresa.cnpj + " / Nome " + empresa.nome + " / Endereco "
					+ empresa.endereco.logradouro + " / N° - " + empresa.endereco.numeroPorta + " / Complemento - " + empresa.endereco.complemento
					+ " / CEP - " + empresa.endereco.cep + " / Telefone - " + empresa.telefone + " / Email - " + empresa.email);
		}
		limpaLinhas();
	}

	private ClienteJuridico buscaCliente(ClienteJuridico empresa) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "clientes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {

				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(empresa.nome)) {
					empresa.cnpj = vetLinha[0];
					empresa.nome = vetLinha[1];
					empresa.endereco.logradouro = vetLinha[2];
					empresa.endereco.numeroPorta = vetLinha[3];
					empresa.endereco.complemento = vetLinha[4];
					empresa.endereco.cep = vetLinha[5];
					empresa.telefone = vetLinha[6];
					empresa.email = vetLinha[7];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return empresa;
	}

	public void limpaLinhas() {
		tfCnpj.setText("");
		tfNomeJuri.setText("");
		tfLogradouroJuri.setText("");
		tfNumeroJuri.setText("");
		tfComplementoJuri.setText("");
		tfCepJuri.setText("");
		tfTelefone.setText("");
		tfEmail.setText("");
	}
}
