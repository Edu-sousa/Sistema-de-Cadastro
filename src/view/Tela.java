package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClientesFisicosController;
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
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Produtos", null, panel, "Pagina de Produtos");
		panel.setLayout(null);
		
		JLabel lblNomeProduto = new JLabel("Nome");
		lblNomeProduto.setBounds(59, 63, 46, 14);
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNomeProduto);
		
		JLabel lblValorProduto = new JLabel("Valor");
		lblValorProduto.setBounds(59, 100, 46, 14);
		lblValorProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblValorProduto);
		
		JLabel lblDescricaoProduto = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoProduto.setBounds(59, 135, 74, 20);
		lblDescricaoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblDescricaoProduto);
		
		JLabel lblQtdEstoque = new JLabel("Quantidade em Estoque");
		lblQtdEstoque.setBounds(59, 174, 147, 14);
		lblQtdEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblQtdEstoque);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(220, 62, 86, 20);
		panel.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		tfValorProduto = new JTextField();
		tfValorProduto.setBounds(220, 99, 86, 20);
		panel.add(tfValorProduto);
		tfValorProduto.setColumns(10);
		
		tfDescricaoProduto = new JTextField();
		tfDescricaoProduto.setBounds(220, 137, 86, 20);
		panel.add(tfDescricaoProduto);
		tfDescricaoProduto.setColumns(10);
		
		tfQtdProduto = new JTextField();
		tfQtdProduto.setBounds(220, 173, 86, 20);
		panel.add(tfQtdProduto);
		tfQtdProduto.setColumns(10);
		
		JTextArea taListaProduto = new JTextArea();
		taListaProduto.setBounds(0, 220, 599, 171);
		panel.add(taListaProduto);
		
		JButton btnCadastrarProdutos = new JButton("Cadastrar");
		btnCadastrarProdutos.setBounds(419, 77, 99, 23);
		panel.add(btnCadastrarProdutos);
		
		JButton btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setBounds(419, 148, 99, 23);
		panel.add(btnBuscarProduto);
		
		
		JLabel lblCodigo = new JLabel("Codigo ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(59, 28, 99, 20);
		panel.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(220, 31, 86, 20);
		panel.add(tfCodigo);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tipos de Produto", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblTipoNome = new JLabel("Nome");
		lblTipoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoNome.setBounds(57, 95, 46, 14);
		panel_2.add(lblTipoNome);
		
		JLabel lblDescricaoTipo = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescricaoTipo.setBounds(57, 134, 96, 19);
		panel_2.add(lblDescricaoTipo);
		
		JLabel lblCodigoTipo = new JLabel("Codigo");
		lblCodigoTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoTipo.setBounds(57, 58, 46, 17);
		panel_2.add(lblCodigoTipo);
		
		tfCodigoTipo = new JTextField();
		tfCodigoTipo.setColumns(10);
		tfCodigoTipo.setBounds(181, 58, 86, 20);
		panel_2.add(tfCodigoTipo);
		
		tfNomeTipo = new JTextField();
		tfNomeTipo.setColumns(10);
		tfNomeTipo.setBounds(181, 94, 86, 20);
		panel_2.add(tfNomeTipo);
		
		tfDescricaoTipo = new JTextField();
		tfDescricaoTipo.setColumns(10);
		tfDescricaoTipo.setBounds(181, 133, 86, 20);
		panel_2.add(tfDescricaoTipo);
		
		JButton btnCadastraTipo = new JButton("Cadastrar");
		btnCadastraTipo.setBounds(386, 69, 99, 23);
		panel_2.add(btnCadastraTipo);
		
		JButton btnBuscaTipo = new JButton("Buscar");
		btnBuscaTipo.setBounds(386, 134, 99, 23);
		panel_2.add(btnBuscaTipo);
		
		JTextArea taListaTipos = new JTextArea();
		taListaTipos.setBounds(0, 220, 599, 171);
		panel_2.add(taListaTipos);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Clientes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNomeCliente = new JLabel("Nome");
		lblNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeCliente.setBounds(62, 49, 36, 14);
		panel_1.add(lblNomeCliente);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(62, 24, 46, 14);
		panel_1.add(lblCpf);
		
		JLabel lblEnderecoCliente = new JLabel("Endere\u00E7o");
		lblEnderecoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnderecoCliente.setBounds(62, 74, 78, 14);
		panel_1.add(lblEnderecoCliente);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(62, 190, 65, 14);
		panel_1.add(lblCelular);
		
		tfCpf = new JTextField();
		tfCpf.setColumns(10);
		tfCpf.setBounds(156, 23, 86, 20);
		panel_1.add(tfCpf);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setColumns(10);
		tfNomeCliente.setBounds(156, 48, 86, 20);
		panel_1.add(tfNomeCliente);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setForeground(new Color(192, 192, 192));
		tfLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		tfLogradouro.setText("Logradouro");
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(156, 73, 86, 20);
		panel_1.add(tfLogradouro);
		
		tfCelular = new JTextField();
		tfCelular.setColumns(10);
		tfCelular.setBounds(156, 189, 86, 20);
		panel_1.add(tfCelular);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrarCliente.setBounds(435, 60, 99, 23);
		panel_1.add(btnCadastrarCliente);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setBounds(435, 146, 99, 23);
		panel_1.add(btnBuscarCliente);
		
		JTextArea taClientesFisicos = new JTextArea();
		taClientesFisicos.setBounds(0, 220, 599, 171);
		panel_1.add(taClientesFisicos);
		
		tfNumero = new JTextField();
		tfNumero.setText("Numero ");
		tfNumero.setHorizontalAlignment(SwingConstants.LEFT);
		tfNumero.setForeground(Color.LIGHT_GRAY);
		tfNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNumero.setColumns(10);
		tfNumero.setBounds(156, 99, 86, 20);
		panel_1.add(tfNumero);
		
		tfComplemento = new JTextField();
		tfComplemento.setText("Complemento");
		tfComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		tfComplemento.setForeground(Color.LIGHT_GRAY);
		tfComplemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(156, 130, 86, 20);
		panel_1.add(tfComplemento);
		
		tfCep = new JTextField();
		tfCep.setText("CEP");
		tfCep.setHorizontalAlignment(SwingConstants.LEFT);
		tfCep.setForeground(Color.LIGHT_GRAY);
		tfCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCep.setColumns(10);
		tfCep.setBounds(156, 158, 86, 20);
		panel_1.add(tfCep);
		
		
		ProdutosController pCont = new ProdutosController(tfCodigo,tfNomeProduto, tfValorProduto, tfDescricaoProduto, tfQtdProduto, taListaProduto);
		TiposProdutosController tpCont = new TiposProdutosController(tfCodigoTipo, tfNomeTipo, tfDescricaoTipo, taListaTipos);
		ClientesFisicosController cfCont = new ClientesFisicosController(tfCpf, tfNomeCliente, tfLogradouro, tfNumero, tfComplemento, tfCep, tfCelular, taClientesFisicos);
		
		btnCadastrarProdutos.addActionListener(pCont);
		btnBuscarProduto.addActionListener(pCont);
		
		btnCadastraTipo.addActionListener(tpCont);
		btnBuscaTipo.addActionListener(tpCont);
		
		btnCadastrarCliente.addActionListener(cfCont);
		btnBuscarCliente.addActionListener(cfCont);
		

		
	}
}
