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

import fateczl.listaSetGenerica.model.ListaSetGenerica;
import model.Produto;
import model.TipoProduto;

public class ProdutosController implements ActionListener {

	private JTextField tfCodigo;
	private JTextField tfNomeProduto;
	private JTextField tfValorProduto;
	private JTextField tfDescricaoProduto;
	private JTextField tfQtdProduto;
	private JTextArea taListaProduto;

	public ProdutosController(JTextField tfCodigo, JTextField tfNomeProduto, JTextField tfValorProduto,
			JTextField tfDescricaoProduto, JTextField tfQtdProduto, JTextArea taListaProduto) {
		super();
		this.tfCodigo = tfCodigo;
		this.tfNomeProduto = tfNomeProduto;
		this.tfValorProduto = tfValorProduto;
		this.tfDescricaoProduto = tfDescricaoProduto;
		this.tfQtdProduto = tfQtdProduto;
		this.taListaProduto = taListaProduto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); // Pega exatamente o nome escrito no botão
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
		// verificando se os campos estão preenchidos
		if (tfCodigo.getText().isEmpty() || tfNomeProduto.getText().isEmpty() || tfValorProduto.getText().isEmpty()
				|| tfDescricaoProduto.getText().isEmpty() || tfQtdProduto.getText().isEmpty()) {
			taListaProduto.setText("Preencha todos os campos para concluir o cadastro");
		} else {
			taListaProduto.setText("");
			Produto produto = new Produto();
			produto.tipoProduto = new TipoProduto();

			produto.tipoProduto.codIdentificador = Integer.parseInt(tfCodigo.getText());
			produto.nome = tfNomeProduto.getText(); // pega o conteudo
			produto.valor = Integer.parseInt(tfValorProduto.getText());
			produto.descricao = tfDescricaoProduto.getText();
			produto.qtdEstoque = Integer.parseInt(tfQtdProduto.getText());

			cadastraProduto(produto.toString());
			limpaTexto();
			taListaProduto.setText("Produto Cadastrado");
		}
	}

	private void cadastraProduto(String csvProduto) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro"; // pega o diretorio do
																								// usuario
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir(); // cria diretorio
		}
		File arq = new File(path, "produto.csv"); // cria arquivo

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvProduto + "\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

//-------------------------------------------------------------------------------------	
	private void busca() throws IOException {
		Produto produto = new Produto();
		produto.nome = tfNomeProduto.getText();
		produto.tipoProduto = new TipoProduto();

		produto = buscaProduto(produto);

		if (produto != null) {
			taListaProduto.setText("Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome
					+ " - Valor: " + produto.valor + " - Descrição: " + produto.descricao + " - Qtd Estoque: "
					+ produto.qtdEstoque);
		} else {
			taListaProduto.setText("Produto não encontrado");
		}
		limpaTexto();
	}

	public Produto buscaProduto(Produto produto) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "produto.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(produto.nome)
						|| Integer.parseInt(vetLinha[0]) == produto.tipoProduto.codIdentificador) {
					produto.tipoProduto.codIdentificador = Integer.parseInt(vetLinha[0]);
					produto.nome = vetLinha[1];
					produto.valor = Float.parseFloat(vetLinha[2]);
					produto.descricao = vetLinha[3];
					produto.qtdEstoque = Integer.parseInt(vetLinha[4]);
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		if (produto.qtdEstoque <= 0) {
			produto = null;
		}
		return produto;
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
		File arq = new File(path, "produto.csv"); // cria arquivo

		if (tfNomeProduto.getText().isEmpty()) {
			taListaProduto.setText("Digite o nome de um produto existente para exclui-lo");
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
					if (tfNomeProduto.getText().equals(vetLinha[1])) {

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
			taListaProduto.setText("Produto Excluido");
			// termina reescrita do arquivo
			limpaTexto();
		}
	}

	public void limpaTexto() {
		tfCodigo.setText("");
		tfNomeProduto.setText("");
		tfValorProduto.setText("");
		tfDescricaoProduto.setText("");
		tfQtdProduto.setText("");
	}
}
