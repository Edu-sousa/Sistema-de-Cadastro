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
			if(cmd.equals("Excluir")) {
				excluir();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void cadastro() throws IOException {
		if (tfCnpj.getText().isEmpty() || tfNomeJuri.getText().isEmpty() || tfLogradouroJuri.getText().isEmpty()
				|| tfNumeroJuri.getText().isEmpty() || tfComplementoJuri.getText().isEmpty()
				|| tfCepJuri.getText().isEmpty() || tfTelefone.getText().isEmpty() || tfEmail.getText().isEmpty()) {
			taClientesJuri.setText("Preencha todos os campos para conluir o cadastro");
		} else {
			taClientesJuri.setText("");

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
			taClientesJuri.setText("Cliente Cadastrado");
		}
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

	}

//-------------------------------------------------------------------------------------	
	private void busca() throws IOException {
		ClienteJuridico empresa = new ClienteJuridico();
		empresa.endereco = new Endereço();

		empresa.nome = tfNomeJuri.getText();

		empresa = buscaCliente(empresa);

		if (empresa != null) {
			taClientesJuri.setText("CNPJ - " + empresa.cnpj + " / Nome " + empresa.nome + " / Endereco "
					+ empresa.endereco.logradouro + " / N° - " + empresa.endereco.numeroPorta + " / Complemento - "
					+ empresa.endereco.complemento + " / CEP - " + empresa.endereco.cep + " / Telefone - "
					+ empresa.telefone + " / Email - " + empresa.email);
		}else {
			taClientesJuri.setText("Cliente não encontrado");
		}
		limpaLinhas();
	}

	private ClienteJuridico buscaCliente(ClienteJuridico empresa) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "clientes.csv");

		boolean existe = false; //verifica se o cliente existe
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
					existe = true;
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		if(existe == false) {
			empresa = null;
		}
		return empresa;
	}
//-------------------------------------------------------------------------------------	
	private void excluir() throws IOException {
		// pegando o caminho do diretorio Sistema Cadastro
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";

		// buffer que tera todos os produtos, menos o excluido
		StringBuffer buffer2 = new StringBuffer();

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir(); // cria o diretorio se não existir
		}
		File arq = new File(path, "clientes.csv"); // cria arquivo

		if (tfNomeJuri.getText().isEmpty()) {
			taClientesJuri.setText("Digite o nome de um Cliente existente para exclui-lo");
		} else {
			// começa leitura do arquivo
			if (arq.exists() && arq.isFile()) {
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffer = new BufferedReader(isr);
				String linha = buffer.readLine();
				while (linha != null) {

					String[] vetLinha = linha.split(";");
					// verificando o nome do produto com o arquivo produto
					if (tfNomeJuri.getText().equals(vetLinha[1])) {

					} else {
						// adicionando no buffer se não for o produto excluido
						buffer2.append(linha + "\r\n");
					}
					linha = buffer.readLine();
				}
				buffer.close();
				isr.close();
				fis.close();
				// termina leitura do arquivo
			}
			FileWriter fw = new FileWriter(arq);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(buffer2.toString());
			pw.flush();
			pw.close();
			fw.close();
			taClientesJuri.setText("Cliente Excluido");
			// termina reescrita do arquivo
			limpaLinhas();
		}
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
