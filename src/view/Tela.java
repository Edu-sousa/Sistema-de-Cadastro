package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClientesFisicosController;
import controller.ClientesJuridicosController;
import controller.ComprasController;
import controller.EstoqueController;
import controller.PesquisaComprasController;
import controller.ProdutosController;
import controller.TiposProdutosController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JComboBox;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNomeProduto;
	private JTextField tfValorProduto;
	private JTextField tfDescricaoProduto;
	private JTextField tfQtdProduto;
	private JTextField tfCodigo;
	private JTextField tfCodigoTipo;
	private JTextField tfNomeTipo;
	private JTextField tfDescricaoTipo;
	private JTextField tfCpf;
	private JTextField tfNomeCliente;
	private JTextField tfLogradouro;
	private JTextField tfCelular;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JTextField tfCep;
	private JTextField tfLogradouroJuri;
	private JTextField tfNumeroJuri;
	private JTextField tfComplementoJuri;
	private JTextField tfCepJuri;
	private JTextField tfCnpj;
	private JTextField tfNomeJuri;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextField tfProdutoCarrinho;
	private JTextField tfValorCarrinho;
	private JTextField tfClienteCompra;
	private JTextField tfQtd;
	private JTextField tfConsultaNome;
	private JTextField tfBuscaCodigo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 604, 419);
		contentPane.add(tabbedPane);
		
		JPanel Produtos = new JPanel();
		tabbedPane.addTab("Produtos", null, Produtos, "Pagina de Produtos");
		Produtos.setLayout(null);
		
		JLabel lblNomeProduto = new JLabel("Nome");
		lblNomeProduto.setBounds(59, 63, 46, 14);
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Produtos.add(lblNomeProduto);
		
		JLabel lblValorProduto = new JLabel("Valor");
		lblValorProduto.setBounds(59, 100, 46, 14);
		lblValorProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Produtos.add(lblValorProduto);
		
		JLabel lblDescricaoProduto = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoProduto.setBounds(59, 135, 74, 20);
		lblDescricaoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Produtos.add(lblDescricaoProduto);
		
		JLabel lblQtdEstoque = new JLabel("Quantidade em Estoque");
		lblQtdEstoque.setBounds(59, 174, 147, 19);
		lblQtdEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Produtos.add(lblQtdEstoque);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(220, 62, 86, 20);
		Produtos.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		tfValorProduto = new JTextField();
		tfValorProduto.setBounds(220, 99, 86, 20);
		Produtos.add(tfValorProduto);
		tfValorProduto.setColumns(10);
		
		tfDescricaoProduto = new JTextField();
		tfDescricaoProduto.setBounds(220, 137, 86, 20);
		Produtos.add(tfDescricaoProduto);
		tfDescricaoProduto.setColumns(10);
		
		tfQtdProduto = new JTextField();
		tfQtdProduto.setBounds(220, 173, 86, 20);
		Produtos.add(tfQtdProduto);
		tfQtdProduto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 220, 599, 171);
		Produtos.add(scrollPane);
		
		JTextArea taListaProduto = new JTextArea();
		taListaProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(taListaProduto);
		
		JButton btnCadastrarProdutos = new JButton("Cadastrar");
		btnCadastrarProdutos.setBounds(435, 45, 99, 23);
		Produtos.add(btnCadastrarProdutos);
		
		JButton btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setToolTipText("Insira o nome de um produto para busca-lo");
		btnBuscarProduto.setBounds(435, 146, 99, 23);
		Produtos.add(btnBuscarProduto);
		
		
		JLabel lblCodigo = new JLabel("Codigo ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(59, 28, 99, 20);
		Produtos.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(220, 31, 86, 20);
		Produtos.add(tfCodigo);
		
		JPanel TiposDeProdutos = new JPanel();
		tabbedPane.addTab("Tipos de Produto", null, TiposDeProdutos, null);
		TiposDeProdutos.setLayout(null);
		
		JLabel lblTipoNome = new JLabel("Nome");
		lblTipoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoNome.setBounds(57, 95, 46, 14);
		TiposDeProdutos.add(lblTipoNome);
		
		JLabel lblDescricaoTipo = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescricaoTipo.setBounds(57, 134, 96, 19);
		TiposDeProdutos.add(lblDescricaoTipo);
		
		JLabel lblCodigoTipo = new JLabel("Codigo");
		lblCodigoTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoTipo.setBounds(57, 58, 46, 17);
		TiposDeProdutos.add(lblCodigoTipo);
		
		tfCodigoTipo = new JTextField();
		tfCodigoTipo.setColumns(10);
		tfCodigoTipo.setBounds(181, 58, 86, 20);
		TiposDeProdutos.add(tfCodigoTipo);
		
		tfNomeTipo = new JTextField();
		tfNomeTipo.setColumns(10);
		tfNomeTipo.setBounds(181, 94, 86, 20);
		TiposDeProdutos.add(tfNomeTipo);
		
		tfDescricaoTipo = new JTextField();
		tfDescricaoTipo.setColumns(10);
		tfDescricaoTipo.setBounds(181, 133, 86, 20);
		TiposDeProdutos.add(tfDescricaoTipo);
		
		JButton btnCadastraTipo = new JButton("Cadastrar");
		btnCadastraTipo.setBounds(435, 45, 99, 23);
		TiposDeProdutos.add(btnCadastraTipo);
		
		JButton btnBuscaTipo = new JButton("Buscar");
		btnBuscaTipo.setToolTipText("Insira o nome de um tipo de produto para busca-lo");
		btnBuscaTipo.setBounds(435, 146, 99, 23);
		TiposDeProdutos.add(btnBuscaTipo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 220, 599, 171);
		TiposDeProdutos.add(scrollPane_1);
		
		JTextArea taListaTipos = new JTextArea();
		taListaTipos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setViewportView(taListaTipos);
		
		JPanel Pessoas = new JPanel();
		tabbedPane.addTab("Pessoas", null, Pessoas, null);
		Pessoas.setLayout(null);
		
		JLabel lblNomeCliente = new JLabel("Nome");
		lblNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeCliente.setBounds(62, 49, 36, 14);
		Pessoas.add(lblNomeCliente);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(62, 24, 46, 14);
		Pessoas.add(lblCpf);
		
		JLabel lblEnderecoCliente = new JLabel("Endere\u00E7o");
		lblEnderecoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnderecoCliente.setBounds(62, 74, 78, 14);
		Pessoas.add(lblEnderecoCliente);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(62, 190, 65, 14);
		Pessoas.add(lblCelular);
		
		tfCpf = new JTextField();
		tfCpf.setToolTipText("CPF");
		tfCpf.setColumns(10);
		tfCpf.setBounds(156, 23, 86, 20);
		Pessoas.add(tfCpf);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setToolTipText("Nome");
		tfNomeCliente.setColumns(10);
		tfNomeCliente.setBounds(156, 48, 86, 20);
		Pessoas.add(tfNomeCliente);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setToolTipText("Logradouro");
		tfLogradouro.setForeground(new Color(0, 0, 0));
		tfLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(156, 73, 86, 20);
		Pessoas.add(tfLogradouro);
		
		tfCelular = new JTextField();
		tfCelular.setToolTipText("Celular");
		tfCelular.setColumns(10);
		tfCelular.setBounds(156, 189, 86, 20);
		Pessoas.add(tfCelular);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrarCliente.setBounds(435, 45, 99, 23);
		Pessoas.add(btnCadastrarCliente);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setToolTipText("Insira o nome de um cliente para busca-lo");
		btnBuscarCliente.setBounds(435, 146, 99, 23);
		Pessoas.add(btnBuscarCliente);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 220, 599, 171);
		Pessoas.add(scrollPane_2);
		
		JTextArea taClientesFisicos = new JTextArea();
		taClientesFisicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_2.setViewportView(taClientesFisicos);
	
		
		tfNumero = new JTextField();
		tfNumero.setToolTipText("Numero");
		tfNumero.setHorizontalAlignment(SwingConstants.LEFT);
		tfNumero.setForeground(new Color(0, 0, 0));
		tfNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNumero.setColumns(10);
		tfNumero.setBounds(156, 99, 86, 20);
		Pessoas.add(tfNumero);
		
		tfComplemento = new JTextField();
		tfComplemento.setToolTipText("Complemento");
		tfComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		tfComplemento.setForeground(new Color(0, 0, 0));
		tfComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(156, 130, 86, 20);
		Pessoas.add(tfComplemento);
		
		tfCep = new JTextField();
		tfCep.setToolTipText("CEP");
		tfCep.setHorizontalAlignment(SwingConstants.LEFT);
		tfCep.setForeground(new Color(0, 0, 0));
		tfCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCep.setColumns(10);
		tfCep.setBounds(156, 158, 86, 20);
		Pessoas.add(tfCep);
		
		JPanel Empresas = new JPanel();
		tabbedPane.addTab("Empresas", null, Empresas, null);
		Empresas.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 220, 599, 171);
		Empresas.add(scrollPane_3);
		
		JTextArea taClientesJuri = new JTextArea();
		taClientesJuri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_3.setViewportView(taClientesJuri);

		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(62, 37, 46, 14);
		Empresas.add(lblCnpj);
		
		JLabel lblNomeJuri = new JLabel("Nome");
		lblNomeJuri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeJuri.setBounds(62, 65, 46, 14);
		Empresas.add(lblNomeJuri);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(264, 158, 55, 14);
		Empresas.add(lblTelefone);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(264, 191, 62, 14);
		Empresas.add(lblEmail);
		
		JLabel lblEnderecoJuri = new JLabel("Endere\u00E7o");
		lblEnderecoJuri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnderecoJuri.setBounds(62, 96, 78, 14);
		Empresas.add(lblEnderecoJuri);
		
		tfLogradouroJuri = new JTextField();
		tfLogradouroJuri.setToolTipText("Logradouro");
		tfLogradouroJuri.setHorizontalAlignment(SwingConstants.LEFT);
		tfLogradouroJuri.setForeground(new Color(0, 0, 0));
		tfLogradouroJuri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfLogradouroJuri.setColumns(10);
		tfLogradouroJuri.setBounds(156, 94, 86, 20);
		Empresas.add(tfLogradouroJuri);
		
		tfNumeroJuri = new JTextField();
		tfNumeroJuri.setToolTipText("Numero ");
		tfNumeroJuri.setHorizontalAlignment(SwingConstants.LEFT);
		tfNumeroJuri.setForeground(new Color(0, 0, 0));
		tfNumeroJuri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNumeroJuri.setColumns(10);
		tfNumeroJuri.setBounds(157, 156, 86, 20);
		Empresas.add(tfNumeroJuri);
		
		tfComplementoJuri = new JTextField();
		tfComplementoJuri.setToolTipText("Complemento");
		tfComplementoJuri.setHorizontalAlignment(SwingConstants.LEFT);
		tfComplementoJuri.setForeground(new Color(0, 0, 0));
		tfComplementoJuri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfComplementoJuri.setColumns(10);
		tfComplementoJuri.setBounds(156, 125, 86, 20);
		Empresas.add(tfComplementoJuri);
		
		tfCepJuri = new JTextField();
		tfCepJuri.setToolTipText("CEP");
		tfCepJuri.setHorizontalAlignment(SwingConstants.LEFT);
		tfCepJuri.setForeground(new Color(0, 0, 0));
		tfCepJuri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCepJuri.setColumns(10);
		tfCepJuri.setBounds(157, 187, 86, 20);
		Empresas.add(tfCepJuri);
		
		JButton btnCadastrarClienteJ = new JButton("Cadastrar");
		btnCadastrarClienteJ.setBounds(435, 45, 99, 23);
		Empresas.add(btnCadastrarClienteJ);
		
		JButton btnBuscarClienteJ = new JButton("Buscar");
		btnBuscarClienteJ.setToolTipText("Insira o nome de um cliente para busca-lo");
		btnBuscarClienteJ.setBounds(435, 146, 99, 23);
		Empresas.add(btnBuscarClienteJ);
		
		tfCnpj = new JTextField();
		tfCnpj.setToolTipText("CNPJ");
		tfCnpj.setHorizontalAlignment(SwingConstants.LEFT);
		tfCnpj.setForeground(new Color(0, 0, 0));
		tfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCnpj.setColumns(10);
		tfCnpj.setBounds(156, 31, 86, 20);
		Empresas.add(tfCnpj);
		
		tfNomeJuri = new JTextField();
		tfNomeJuri.setToolTipText("Nome");
		tfNomeJuri.setHorizontalAlignment(SwingConstants.LEFT);
		tfNomeJuri.setForeground(new Color(0, 0, 0));
		tfNomeJuri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNomeJuri.setColumns(10);
		tfNomeJuri.setBounds(156, 63, 86, 20);
		Empresas.add(tfNomeJuri);
		
		tfTelefone = new JTextField();
		tfTelefone.setToolTipText("Telefone");
		tfTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		tfTelefone.setForeground(new Color(0, 0, 0));
		tfTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(321, 156, 86, 20);
		Empresas.add(tfTelefone);
		
		tfEmail = new JTextField();
		tfEmail.setToolTipText("Email");
		tfEmail.setHorizontalAlignment(SwingConstants.LEFT);
		tfEmail.setForeground(new Color(0, 0, 0));
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfEmail.setColumns(10);
		tfEmail.setBounds(321, 189, 86, 20);
		Empresas.add(tfEmail);
		
		JPanel Carrinho = new JPanel();
		tabbedPane.addTab("Carrinho", null, Carrinho, null);
		tabbedPane.setBackgroundAt(4, new Color(128, 128, 128));
		Carrinho.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 222, 597, 169);
		Carrinho.add(scrollPane_4);
		
		JTextArea taLista = new JTextArea();
		taLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_4.setViewportView(taLista);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setToolTipText("Insira um produto e sua quantidade para adiciona-lo");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionar.setBounds(435, 60, 99, 23);
		Carrinho.add(btnAdicionar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(435, 146, 99, 23);
		Carrinho.add(btnExcluir);
		
		JButton btnVisualizarCarrinho = new JButton("Visualizar Carrinho");
		btnVisualizarCarrinho.setBounds(346, 188, 149, 23);
		Carrinho.add(btnVisualizarCarrinho);
		
		JLabel lblProdutoCarrinho = new JLabel("Produto");
		lblProdutoCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProdutoCarrinho.setBounds(49, 73, 68, 14);
		Carrinho.add(lblProdutoCarrinho);
		
		JLabel lblValorCarrinho = new JLabel("Valor Total");
		lblValorCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorCarrinho.setBounds(49, 151, 86, 14);
		Carrinho.add(lblValorCarrinho);
		
		tfProdutoCarrinho = new JTextField();
		tfProdutoCarrinho.setToolTipText("Insira o nome de um produto");
		tfProdutoCarrinho.setHorizontalAlignment(SwingConstants.LEFT);
		tfProdutoCarrinho.setForeground(Color.BLACK);
		tfProdutoCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfProdutoCarrinho.setColumns(10);
		tfProdutoCarrinho.setBounds(140, 72, 86, 20);
		Carrinho.add(tfProdutoCarrinho);
		
		tfValorCarrinho = new JTextField();
		tfValorCarrinho.setToolTipText("Valor Total da Compra");
		tfValorCarrinho.setHorizontalAlignment(SwingConstants.LEFT);
		tfValorCarrinho.setForeground(Color.BLACK);
		tfValorCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfValorCarrinho.setColumns(10);
		tfValorCarrinho.setBounds(140, 149, 86, 20);
		Carrinho.add(tfValorCarrinho);
		
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setBounds(105, 188, 149, 23);
		Carrinho.add(btnFinalizarCompra);	
		
		JPanel Estoque = new JPanel();
		tabbedPane.addTab("Estoque", null, Estoque, null);
		Estoque.setLayout(null);
		
		JButton btnListaTipos = new JButton("Lista de Tipos");
		btnListaTipos.setToolTipText("Listagem de tipos e todos seus produtos");
		btnListaTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListaTipos.setBounds(408, 77, 121, 23);
		Estoque.add(btnListaTipos);
		 
		 JButton btnPesquisarTipo = new JButton("Pesquisar");
		 btnPesquisarTipo.setToolTipText("Pesquisar todos os produtos de um tipo ");
		 btnPesquisarTipo.setBounds(179, 77, 99, 23);
		 Estoque.add(btnPesquisarTipo);
		 
		 tfBuscaCodigo = new JTextField();
		 tfBuscaCodigo.setToolTipText("Insira o codigo do tipo, para retornar seus produtos");
		 tfBuscaCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		 tfBuscaCodigo.setForeground(Color.BLACK);
		 tfBuscaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 tfBuscaCodigo.setColumns(10);
		 tfBuscaCodigo.setBounds(69, 80, 86, 20);
		 Estoque.add(tfBuscaCodigo);
		 
		 JLabel lblCodigo_1 = new JLabel("Codigo");
		 lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblCodigo_1.setBounds(87, 53, 51, 18);
		 Estoque.add(lblCodigo_1);
		 
		 JScrollPane scrollPane_5 = new JScrollPane();
		 scrollPane_5.setBounds(0, 126, 599, 265);
		 Estoque.add(scrollPane_5);
		 
		 JTextArea taEstoque = new JTextArea();
		 taEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 scrollPane_5.setViewportView(taEstoque);

		
		JLabel lblClienteCompra = new JLabel("Cliente");
		lblClienteCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClienteCompra.setBounds(49, 32, 68, 14);
		Carrinho.add(lblClienteCompra);
		
		tfClienteCompra = new JTextField();
		tfClienteCompra.setToolTipText("Insira o nome de um Cliente");
		tfClienteCompra.setHorizontalAlignment(SwingConstants.LEFT);
		tfClienteCompra.setForeground(Color.BLACK);
		tfClienteCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfClienteCompra.setColumns(10);
		tfClienteCompra.setBounds(140, 31, 86, 20);
		Carrinho.add(tfClienteCompra);
		
		JPanel ConsultarCompra = new JPanel();
		ConsultarCompra.setToolTipText("");
		tabbedPane.addTab("Consultar", null, ConsultarCompra, null);
		ConsultarCompra.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 224, 595, 167);
		ConsultarCompra.add(scrollPane_6);
		
		JTextArea taConsultaCompra = new JTextArea();
		scrollPane_6.setViewportView(taConsultaCompra);
		taConsultaCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		tfConsultaNome = new JTextField();
		tfConsultaNome.setToolTipText("DIgite o nome de um cliente para consultar o historico de compras");
		tfConsultaNome.setHorizontalAlignment(SwingConstants.LEFT);
		tfConsultaNome.setForeground(Color.BLACK);
		tfConsultaNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfConsultaNome.setColumns(10);
		tfConsultaNome.setBounds(183, 80, 149, 20);
		ConsultarCompra.add(tfConsultaNome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(120, 82, 68, 14);
		ConsultarCompra.add(lblNome);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(362, 80, 99, 23);
		ConsultarCompra.add(btnPesquisar);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(49, 112, 86, 14);
		Carrinho.add(lblQuantidade);
		
		tfQtd = new JTextField();
		tfQtd.setToolTipText("Insira a quantidade");
		tfQtd.setHorizontalAlignment(SwingConstants.LEFT);
		tfQtd.setForeground(Color.BLACK);
		tfQtd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfQtd.setColumns(10);
		tfQtd.setBounds(140, 107, 86, 20);
		Carrinho.add(tfQtd);
		
	
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(62, 103, 78, 14);
		Pessoas.add(lblNumero);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComplemento.setBounds(62, 134, 92, 16);
		Pessoas.add(lblComplemento);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep.setBounds(62, 162, 78, 14);
		Pessoas.add(lblCep);

		
		JLabel lblNumero_1 = new JLabel("Numero");
		lblNumero_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero_1.setBounds(62, 156, 50, 14);
		Empresas.add(lblNumero_1);
		
		JLabel lblComplemento_1 = new JLabel("Complemento");
		lblComplemento_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComplemento_1.setBounds(62, 126, 92, 16);
		Empresas.add(lblComplemento_1);
		
		JLabel lblCep_1 = new JLabel("CEP");
		lblCep_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep_1.setBounds(62, 191, 35, 14);
		Empresas.add(lblCep_1);
		
		JButton btnExcluirTipo = new JButton("Excluir");
		btnExcluirTipo.setToolTipText("Insira um codigo para excluir o tipo");
		btnExcluirTipo.setBounds(435, 96, 99, 23);
		TiposDeProdutos.add(btnExcluirTipo);
		
		JLabel lblConsultarCompra = new JLabel("Consultar Compras");
		lblConsultarCompra.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblConsultarCompra.setBounds(212, 26, 173, 31);
		ConsultarCompra.add(lblConsultarCompra);
		
		JButton btnExcluirPessoa = new JButton("Excluir");
		btnExcluirPessoa.setToolTipText("Insira o nome de um cliente para exclui-lo");
		btnExcluirPessoa.setBounds(435, 96, 99, 23);
		Pessoas.add(btnExcluirPessoa);
		
		JButton btnExcluirEmpresa = new JButton("Excluir");
		btnExcluirEmpresa.setToolTipText("Insira o nome de um cliente para exclui-lo");
		btnExcluirEmpresa.setBounds(435, 96, 99, 23);
		Empresas.add(btnExcluirEmpresa);
		
		JButton btnExcluirProduto = new JButton("Excluir");
		btnExcluirProduto.setToolTipText("Insira um nome de um produto para exclui-lo");
		btnExcluirProduto.setBounds(435, 96, 99, 23);
		Produtos.add(btnExcluirProduto);
		
		ClientesJuridicosController cjCont = new ClientesJuridicosController(tfCnpj, tfNomeJuri, tfLogradouroJuri, tfNumeroJuri, tfComplementoJuri, tfCepJuri, tfTelefone, tfEmail, taClientesJuri);
		ClientesFisicosController cfCont = new ClientesFisicosController(tfCpf, tfNomeCliente, tfLogradouro, tfNumero, tfComplemento, tfCep, tfCelular, taClientesFisicos);
		ProdutosController pCont = new ProdutosController(tfCodigo,tfNomeProduto, tfValorProduto, tfDescricaoProduto, tfQtdProduto, taListaProduto);
		PesquisaComprasController psCont = new PesquisaComprasController(tfConsultaNome, taConsultaCompra);
		TiposProdutosController tpCont = new TiposProdutosController(tfCodigoTipo, tfNomeTipo, tfDescricaoTipo, taListaTipos);
		ComprasController compCont = new ComprasController(tfProdutoCarrinho, tfQtd, tfValorCarrinho, tfClienteCompra,taLista);
		EstoqueController eCont = new EstoqueController(tfBuscaCodigo, taEstoque);
		
		btnCadastrarProdutos.addActionListener(pCont);
		btnBuscarProduto.addActionListener(pCont);
		btnExcluirProduto.addActionListener(pCont);
		
		btnCadastraTipo.addActionListener(tpCont);
		btnBuscaTipo.addActionListener(tpCont);
		btnExcluirTipo.addActionListener(tpCont);
		
		btnCadastrarCliente.addActionListener(cfCont);
		btnBuscarCliente.addActionListener(cfCont);
		btnExcluirPessoa.addActionListener(cfCont);
		
		btnCadastrarClienteJ.addActionListener(cjCont);
		btnBuscarClienteJ.addActionListener(cjCont);
		btnExcluirEmpresa.addActionListener(cjCont);
		
		btnAdicionar.addActionListener(compCont);
		btnExcluir.addActionListener(compCont);
		btnVisualizarCarrinho.addActionListener(compCont);
		btnFinalizarCompra.addActionListener(compCont);
		
		btnPesquisar.addActionListener(psCont);
		
		btnPesquisarTipo.addActionListener(eCont);
		btnListaTipos.addActionListener(eCont);
			
	}
}
