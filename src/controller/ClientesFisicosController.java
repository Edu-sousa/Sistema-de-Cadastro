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
			if (cmd.equals("Excluir")) {
				excluir();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void cadastro() throws IOException {

		if (tfCpf.getText().isEmpty() || tfNomeCliente.getText().isEmpty() || tfLogradouro.getText().isEmpty()
				|| tfNumero.getText().isEmpty() || tfComplemento.getText().isEmpty() || tfCep.getText().isEmpty()
				|| tfCelular.getText().isEmpty()) {
			taClientesFisicos.setText("Preencha todos os campos para concluir o cadastro");
		} else {
			taClientesFisicos.setText("");
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
			taClientesFisicos.setText("Cliente Cadastrado");
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
		ClientesFisico cliente = new ClientesFisico();
		cliente.endereco = new Endereço();

		cliente.nome = tfNomeCliente.getText();

		cliente = buscaCliente(cliente);

		if (cliente != null) {
			taClientesFisicos.setText("CPF - " + cliente.cpf + " / Nome - " + cliente.nome + " / Endereco "
					+ cliente.endereco.logradouro + " / N° - " + cliente.endereco.numeroPorta + " / Complemento - "
					+ cliente.endereco.complemento + " / CEP - " + cliente.endereco.cep + " / Celular - "
					+ cliente.celular);
		}else {
			taClientesFisicos.setText("Cliente não encontrado");
		}
		limpaLinhas();
	}

	private ClientesFisico buscaCliente(ClientesFisico cliente) throws IOException {
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
				if (vetLinha[1].equals(cliente.nome)) {
					cliente.cpf = vetLinha[0];
					cliente.nome = vetLinha[1];
					cliente.endereco.logradouro = vetLinha[2];
					cliente.endereco.numeroPorta = vetLinha[3];
					cliente.endereco.complemento = vetLinha[4];
					cliente.endereco.cep = vetLinha[5];
					cliente.celular = vetLinha[6];
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
			cliente = null;
		}
		return cliente;
	}

// -------------------------------------------------------------------------------------
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

		if (tfNomeCliente.getText().isEmpty()) {
			taClientesFisicos.setText("Digite o nome de um Cliente existente para exclui-lo");
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
					if (tfNomeCliente.getText().equals(vetLinha[1])) {

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
			taClientesFisicos.setText("Cliente Excluido");
			// termina reescrita do arquivo
			limpaLinhas();
		}
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
