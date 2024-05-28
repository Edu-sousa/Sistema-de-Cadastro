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
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.filaobj.Fila;
import fateczl.listaSetGenerica.model.ListaSetGenerica;
import model.Produto;
import model.TipoProduto;

public class ComprasController<E> implements ActionListener {

	private JTextField tfProdutoCarrinho;
	private JTextField tfQtd;
	private JTextField tfValorCarrinho;
	private JTextField tfClienteCompra;
	private JTextArea taLista;

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
			buffer.append("Codigo: " + produto.tipoProduto.codIdentificador + " - Nome: " + produto.nome
					+ " - Quantidade: " + produto.qtdEstoque + " - Valor: " + produto.valor + "\r\n"); // adiciona cada
																										// produto no
																										// buffer
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
		limpaTexto();
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
		boolean existe = pesquisarCliente();

		if (tfClienteCompra.getText().equals("")) { // se o compra cliente estiver vazio, a compra não é finalizada
			taLista.setText("Preencha o campo Cliente com um nome valido ");
		} else if (existe == false) {
			taLista.setText("Cliente não cadastrado no banco de dados");
		} else if (lista.isEmpty()) {
			taLista.setText("Carrinho de compras esta vazio");
		} else {
			StringBuffer buffer = new StringBuffer(); // buffer para aparecer os produtos comprados no taLista
			String compra = tfClienteCompra.getText(); // String que ira armazenar todas as informações da compra

			for (int i = 0; i < lista.size(); i++) {
				Produto prod = (Produto) lista.get(i);
				tfValorCarrinho.setText(Float.toString(valorTotal += prod.valor)); // Calcula valor final da compra

				compra += ";" + prod.nome + ";" + prod.qtdEstoque + ";" + prod.valor; // pegando nome, quantidade e
																						// valor
				buffer.append(prod.toString() + "\r\n");
			}
			compra += ";" + tfValorCarrinho.getText();// acrescentando o valor final na String compra
			cadastraCompra(compra); // String com as informações = nome do cliente + nome dos produto + quantidade
									// valor total
			taLista.setText(buffer.toString());
			zerandoCarrinho(lista);// metodo para zerar o carrinho e os texts filds
		}
	}

	private void zerandoCarrinho(ListaSetGenerica lista) throws Exception {
		tfClienteCompra.setText("");
		limpaTexto();
		int tamanho = lista.size();
		for (int i = 0; i < tamanho; i++) {
			lista.removeFirst();
		}

		Timer timer = new Timer();
		TimerTask zerarLista = new TimerTask() {
			@Override
			public void run() {
				taLista.setText("");
				tfValorCarrinho.setText("");
			}
		};
		timer.schedule(zerarLista, 5000);
	}

	private boolean pesquisarCliente() throws InterruptedException, IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "clientes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(tfClienteCompra.getText())) {// comparando o nome passado com nomes no arquivo
																	// clientes
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return false;
	}

	public void cadastraCompra(String csvCompra) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "compras.csv");

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
		tfQtd.setText("");
	}
}