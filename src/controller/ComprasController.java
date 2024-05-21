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

import br.edu.fateczl.filaobj.Fila;
import model.Produto;
import model.TipoProduto;
import model.pilha.object.fatec.zl.Pilha;

public class ComprasController implements ActionListener {

	private JTextField tfProdutoCarrinho;
	private JTextField tfValorCarrinho;
	private JTextArea taLista;

	boolean carrinhoAtivo = true;
	Fila fila = new Fila();
	Pilha pilha = new Pilha();

	public ComprasController(JTextField tfProdutoCarrinho, JTextField tfValorCarrinho, JTextArea taLista) {
		super();
		this.tfProdutoCarrinho = tfProdutoCarrinho;
		this.tfValorCarrinho = tfValorCarrinho;
		this.taLista = taLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			try {
				adicionar(); // adicionar produto na fila
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Excluir")) {
			// excluir(); // excluir produto na fila
		}
		if (cmd.equals("Visualizar Carrinho")) {
			try {
				visualizarCarrinho();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void visualizarCarrinho() throws Exception {
		int tamanho = pilha.size();

		for (int i = 0; i < tamanho; i++) {
			Produto produto = (Produto) pilha.pop();

			taLista.setText("Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome
					+ " - Valor: " + produto.valor + " - Descrição: " + produto.descricao + " - Qtd Estoque: "
					+ produto.qtdEstoque+"\n");
		}
	}

	private void adicionar() throws Exception {
		Produto produto = new Produto(); // instanciando um produto
		produto.tipoProduto = new TipoProduto();

		produto.nome = tfProdutoCarrinho.getText(); // pegando o nome do produto no tf

		ProdutosController p = new ProdutosController(tfProdutoCarrinho, tfProdutoCarrinho, tfProdutoCarrinho,
				tfValorCarrinho, tfProdutoCarrinho, taLista);

		produto = p.buscaProduto(produto); // buscando o produto

		if (produto != null) {
			fila.insert(produto); // inserir na fila
			taLista.setText("Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome
					+ " - Valor: " + produto.valor + " - Descrição: " + produto.descricao + " - Qtd Estoque: "
					+ produto.qtdEstoque);
			pilha.push(produto); // salvando o toString do produto na pilha
		} else {
			taLista.setText("Produto não disponivel");
		}

	}

//	private void listaProdutos(Fila fila) throws IOException {
//		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
//		File arq = new File(path, "produto.csv");
//
//		if (arq.exists() && arq.isFile()) {
//			FileInputStream fis = new FileInputStream(arq);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader buffer = new BufferedReader(isr);
//			String linha = buffer.readLine();
//			
//			while(linha != null) {
//				
//			}
//			buffer.close();
//			isr.close();
//			fis.close();
//		}
//		
//	}
}

// montar carrinhos em ordem cronologica (podendo remover elementos "Pilha")

// remover elementos

// exibir carrinhos (em ordem cronologica "Pilha")

// carrinho de compras só ficam ativos enquanto a compra estiver sendo realizada

//public void checkoutCompra() {
//	// mostrar cada elemento da compra em uma fila e valor da compra
//}
//
//public void verificarTiposEProdutosDisponiveis() {
//	// verificar lista de tipos e seus produtos disponiveis
//}
//
//public void verificarProdutosAtravesdeUmTipo() {
//
//}
//
//public void registrarCompra() { // criar arquivo csv
//	// cada compra finalizada deve ser registrada e exibida quando for consultada
//}
//
//public void consultarCompra() { // ler formato csv
//
//}

//String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
//File arq = new File(path, "produto.csv");
//busca(arq); // verificar se o produto existe

//private void busca(File arq) throws IOException { // le o arquivo produtos.csv e verifica se o produto esta existe
//	if (arq.exists() && arq.isFile()) {
//		FileInputStream fis = new FileInputStream(arq);
//		InputStreamReader isr = new InputStreamReader(fis);
//		BufferedReader buffer = new BufferedReader(isr);
//
//		String linha = buffer.readLine();
//		
//		while(linha !=null) {
//			String [] vetLinha = linha.split(";");
//			
//		}
//	}
//}
