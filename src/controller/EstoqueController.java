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

import fateczl.listaSetGenerica.model.ListaSetGenerica;
import model.Produto;
import model.TipoProduto;

public class EstoqueController implements ActionListener {

	private JTextField tfBuscaCodigo;
	private JTextArea taEstoque;

	// tabela de espalhamento
	ListaSetGenerica[] tabela; // instanciando vetor de listas

	public EstoqueController(JTextField tfBuscaCodigo, JTextArea taEstoque) {
		super();
		this.tfBuscaCodigo = tfBuscaCodigo;
		this.taEstoque = taEstoque;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Pesquisar")) { // Devolve a lista de produtos do tipo selecionado
			try {
				instanciarProcessos();
				tfBuscaCodigo.setText("");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Lista de Tipos")) { // Lista todos os tipos e seus produtos
			try {
				listarTiposEProdutos();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void instanciarProcessos() throws Exception {
		// ler o arquivo tipos, devolver o numero que sera a quantidade de posiçõe do
		// vetor da tabela de espalhamento
		int posicoes = numerosDePosicoes();

		this.tabela = new ListaSetGenerica[posicoes + 1]; // definindo o limite de posições da tabela

		iniciarEPopularTabela();
		if (tfBuscaCodigo.getText().isEmpty()) {
			taEstoque.setText("Campo Vazio");
		} else {
			int codigo = Integer.parseInt(tfBuscaCodigo.getText());
			if (codigo >= posicoes + 1 || codigo < 0) {// verificando se codigo é valido
				taEstoque.setText("Codigo inexistente");
			} else {
				pesquisa(codigo);
			}
		}
	}

	private void pesquisa(int codigo) throws Exception {
		int tamanho = tabela[codigo].size();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tamanho; i++) {
			buffer.append(tabela[codigo].get(i) + " \n");
		}
		taEstoque.setText(buffer.toString());

	}

	private void iniciarEPopularTabela() throws IOException {
		int tamanho = tabela.length;
		for (int i = 0; i < tamanho; i++) {
			tabela[i] = new ListaSetGenerica();
			// instanciar as listas
		}
		// ler o arquivo produtos e popular com base no id
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "produto.csv");

		Produto produto = new Produto();
		produto.tipoProduto = new TipoProduto();
		int posicao = 0;

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");

				int idTipo = Integer.parseInt(vetLinha[0]);
				// adicionando o produto na tabela com base na posição do tipo do produto
				tabela[idTipo].addFirst(linha);

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}
	private int numerosDePosicoes() throws IOException {
		// maiorCodigo sera o numero de posições de vetores da tabela
		int maiorCodigo = 0;

		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "tipos.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (Integer.parseInt(vetLinha[0]) > maiorCodigo) {
					maiorCodigo = Integer.parseInt(vetLinha[0]);
				} 
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return maiorCodigo;
	}

//--------------------------------------------------------------------------------------------------------
	private void listarTiposEProdutos() throws Exception {

		int posicoes = numerosDePosicoes();
		this.tabela = new ListaSetGenerica[posicoes + 1]; // definindo o limite de posições da tabela
		iniciarEPopularTabela();

		StringBuffer bufferzao = new StringBuffer();
		int j = 1;

		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "tipos.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {

				bufferzao.append(linha + "\n"); // pega cada tipo de produto

				int tamanho = tabela[j].size();
				for (int i = 0; i < tamanho; i++) { // pega cada produto da lista encadeada
					bufferzao.append("    " + tabela[j].get(i) + "\n"); // adiciona no buffer
				}
				bufferzao.append("\n");
				j++;
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		taEstoque.setText(bufferzao.toString());
	}
}
