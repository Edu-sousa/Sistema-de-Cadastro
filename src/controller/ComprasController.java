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
import java.util.Stack;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.filaobj.Fila;
import fateczl.listaSetGenerica.model.ListaSetGenerica;
import model.Produto;
import model.TipoProduto;

public class ComprasController implements ActionListener {

	private JTextField tfProdutoCarrinho;
	private JTextField tfQtd;
	private JTextField tfValorCarrinho;
	private JTextField tfClienteCompra;
	private JTextArea taLista;

	boolean carrinhoAtivo = true;
	float valorTotal = 0;
	Stack<Object> pilha = new Stack<>();
	ListaSetGenerica lista = new ListaSetGenerica();

	public ComprasController(JTextField tfProdutoCarrinho, JTextField tfQtd, JTextField tfValorCarrinho,
			JTextField tfClienteCompra, JTextArea taLista) {
		super();
		this.tfProdutoCarrinho = tfProdutoCarrinho;
		this.tfQtd = tfQtd;
		this.tfValorCarrinho = tfValorCarrinho;
		this.tfClienteCompra = tfClienteCompra;
		this.taLista = taLista;
	}

	ProdutosController p = new ProdutosController(tfProdutoCarrinho, tfProdutoCarrinho, tfProdutoCarrinho,
			tfValorCarrinho, tfProdutoCarrinho, taLista);

	@Override
	public void actionPerformed(ActionEvent e) {
		try { // depois testar cmd com switch case
			String cmd = e.getActionCommand();
			if (cmd.equals("Adicionar")) {
				adicionar(); // adicionar produto na fila
			}
			if (cmd.equals("Excluir")) {
				excluir(); // excluir produto da fila
			}
			if (cmd.equals("Visualizar Carrinho")) {
				visualizarCarrinho();
			}
			if (cmd.equals("Finalizar Compra")) {
				checkout();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void visualizarCarrinho() throws Exception {

		int tamanhoLista = lista.size();
		for (int i = 0; i < tamanhoLista; i++) { // for para popular a pilha a partir da lista
			Produto prod = (Produto) lista.get(i);
			pilha.push(prod);
		}

		int tamanho = pilha.size(); // define tamanho da lista
		StringBuffer buffer = new StringBuffer(); // instancia o buffer

		for (int i = tamanho - 1; i >= 0; i--) {
			Produto produto = (Produto) pilha.get(i); // instancia cada elemento da pilha em um produto
//			buffer.append(produto.toString() + "\r\n"); // adiciona cada produto no buffer
			buffer.append("Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome
					+ " - Quantidade: " + produto.qtdEstoque + " - Valor: " + produto.valor + "\r\n");
			pilha.pop();
		}
		taLista.setText(buffer.toString()); // seta o valor do text area com o buffer
	}

	private void adicionar() throws Exception {
		Produto produto = new Produto(); // instanciando um produto
		produto.tipoProduto = new TipoProduto();
		produto.nome = tfProdutoCarrinho.getText(); // pegando o nome do produto

		produto = p.buscaProduto(produto); // buscando o produto

		if (produto != null && !tfQtd.getText().isEmpty()) {

			produto.valor = produto.valor * Float.parseFloat(tfQtd.getText()); // definindo o valor com base na
																				// quantidade do produto
			produto.qtdEstoque = Integer.parseInt(tfQtd.getText()); // definindo a qtdEstoque como quantidade dos
																	// produtos

			taLista.setText(textoDosProdutos(produto));

			if (lista.isEmpty()) { // adicionar na primeira posição ou na ultima
				lista.addFirst(produto);
			} else {
				lista.addLast(produto);
			}

		} else {
			taLista.setText("Produto não disponivel");
		}
	}

	public void excluir() throws Exception {
		Produto produto = new Produto();
		produto.tipoProduto = new TipoProduto();
		produto.nome = tfProdutoCarrinho.getText();

		for (int i = 0; i < lista.size(); i++) {
			Produto prod = (Produto) lista.get(i);
			if (produto.nome.equals(prod.nome)) { // comparando o nome dos produtos
				lista.remove(i);
				visualizarCarrinho();
				break;
			}
		}
	}

	private void checkout() throws Exception {

		if (tfClienteCompra.getText().equals("")) { // se o compra cliente estiver vazio, a compra não é finalizada
			taLista.setText("Preencha o campo Cliente com um nome valido ");
		} else {
			StringBuffer buffer = new StringBuffer();
			String compra = tfClienteCompra.getText(); // String que ira armazenar todas as informações da compra

			for (int i = 0; i < lista.size(); i++) {
				Produto prod = (Produto) lista.get(i);
				tfValorCarrinho.setText(Float.toString(valorTotal += prod.valor)); // Calcula valor final da compra
				buffer.append(prod.toString() + "\r\n");
			}
			compra += ";" + "de sousa silva";
			cadastraCompra(compra); // String com as informações = nome do cliente + nome dos produto + quantidade +
									// valor total
			taLista.setText(buffer.toString());
		}
	}

	public void cadastraCompra(String csvCompra) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro"; // pega o diretorio do
		// usuario
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir(); // cria diretorio
		}
		File arq = new File(path, "compras.csv"); // cria arquivo

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvCompra + "\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	public String textoDosProdutos(Produto produto) {
		return "Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome + " - Quantidade: "
				+ tfQtd.getText() + " - Valor: " + produto.valor;
	}

	public void limpaTexto() {
		tfProdutoCarrinho.setText("");
	}
}

//int posicao = p.devolvePosicao(produto); // devolve posição do produto
//if(posicao >= 0 ) {
//	lista.remove(posicao); // remove elemento com base na posição
//}else {
//	taLista.setText("Produto não encontrado");
//}

//int tamanho = lista.size();
//for (int i = 0; i < tamanho; i++) {
//	System.out.println("Produto " + i + " " + lista.get(i) + "\n");
//}
