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

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fateczl.listaSetGenerica.model.ListaSetGenerica;
import model.TipoProduto;

public class TiposProdutosController implements ActionListener {

	// instanciando os botões
	private JTextField tfCodigoTipo;
	private JTextField tfNomeTipo;
	private JTextField tfDescricaoTipo;
	private JTextArea taListaTipos;

	// metodo construtor
	public TiposProdutosController(JTextField tfCodigoTipo, JTextField tfNomeTipo, JTextField tfDescricaoTipo,
			JTextArea taListaTipos) {
		super();
		this.tfCodigoTipo = tfCodigoTipo;
		this.tfNomeTipo = tfNomeTipo;
		this.tfDescricaoTipo = tfDescricaoTipo;
		this.taListaTipos = taListaTipos;
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
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void cadastro() throws IOException {

		// verificando se os campos estão preenchidos
		if (tfCodigoTipo.getText().isEmpty() || tfNomeTipo.getText().isEmpty() || tfDescricaoTipo.getText().isEmpty()) {
			taListaTipos.setText("Preencha todos os campos para concluir o cadastro");
		} else {
			taListaTipos.setText("");
			TipoProduto tipo = new TipoProduto();
			// passando os texts filds para as variaveis do tipoProduto
			tipo.codIdentificador = Integer.parseInt(tfCodigoTipo.getText());
			tipo.nome = tfNomeTipo.getText();
			tipo.descricao = tfDescricaoTipo.getText();

			System.out.println(tipo);
			cadastraTipo(tipo.toString()); // chamando o metodo de cadastro com o toString como parametro

			limpaTexto(); // limpando os campos depois do cadastro
		}
	}

	private void cadastraTipo(String csvTipo) throws IOException {
		// pegando o caminho do diretorio Sistema Cadastro
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir(); // cria o diretorio se, não existir
		}
		File arq = new File(path, "tipos.csv"); // cria arquivo

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvTipo + "\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	private void busca() throws Exception {
		TipoProduto tipo = new TipoProduto();
		if (!tfCodigoTipo.getText().equals("")) {
			tipo.codIdentificador = Integer.parseInt(tfCodigoTipo.getText());
		}
		tipo.nome = tfNomeTipo.getText();
		tipo.descricao = tfDescricaoTipo.getText();

		ListaSetGenerica tipos = new ListaSetGenerica();

		if (tipo.codIdentificador != 0) { // verificar se o codigo existe
			tipo = buscaCodigo(tipo.codIdentificador); // chama o metodo busca tipo com o codigo como parametro
			if (tipo != null) {
				taListaTipos.setText("Codigo: " + tipo.codIdentificador + " - Nome: " + tipo.nome + " - Descrição: "
						+ tipo.descricao);
			}
		} else if (tipo.nome.equals("todos")) {
			tipos = listaTipos();
		} else if (!tipo.nome.equals("")) {
			tipo = buscaNome(tipo.nome);
			if (tipo != null) {
				taListaTipos.setText("Codigo: " + tipo.codIdentificador + " - Nome: " + tipo.nome + " - Descrição: "
						+ tipo.descricao);
			}
		} else {
			taListaTipos.setText("Insira um codigo valido para busca-lo");
		}
		int tamanhoLista = tipos.size();
		StringBuffer buffer = new StringBuffer();
		if (tamanhoLista > 0) {
			for (int i = 0; i < tamanhoLista; i++) {
				TipoProduto tp = (TipoProduto) tipos.get(i);
				buffer.append("Codigo: " + tp.codIdentificador + " - Nome : " + tp.nome + " - Descrição: "
						+ tp.descricao + "\r\n");
			}
			taListaTipos.setText(buffer.toString());
		}
		limpaTexto();
	}

	private TipoProduto buscaCodigo(int codIdentificador) throws IOException {

		TipoProduto tipo = new TipoProduto();
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "tipos.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {

				String[] vetLinha = linha.split(";");
				if (Integer.parseInt(vetLinha[0]) == codIdentificador) {
					tipo.codIdentificador = Integer.parseInt(vetLinha[0]);
					tipo.nome = vetLinha[1];
					tipo.descricao = vetLinha[2];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		if (tipo.codIdentificador == 0) {
			tipo = null;
		}
		return tipo;
	}

	private TipoProduto buscaNome(String nome) throws IOException {

		TipoProduto tipo = new TipoProduto();
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "tipos.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {

				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(nome)) {
					tipo.codIdentificador = Integer.parseInt(vetLinha[0]);
					tipo.nome = vetLinha[1];
					tipo.descricao = vetLinha[2];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		if (tipo.nome == null) {
			tipo = null;
		}
		return tipo;
	}

	private ListaSetGenerica listaTipos() throws IOException {

		ListaSetGenerica listaTipos = new ListaSetGenerica();

		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";
		File arq = new File(path, "tipos.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {

				String[] vetLinha = linha.split(";");
				TipoProduto tipo = new TipoProduto();
				tipo.codIdentificador = Integer.parseInt(vetLinha[0]);
				tipo.nome = vetLinha[1];
				tipo.descricao = vetLinha[2];

				listaTipos.addFirst(tipo);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaTipos;
	}

	// toda vez que um tipo é excluido o arquivo é reescrito sem ele
	private void excluir() throws Exception {

		// pegando o caminho do diretorio Sistema Cadastro
		String path = System.getProperty("user.home") + File.separator + "Sistema Cadastro";

		ListaSetGenerica lista = listaTipos(); // popula a lista
		StringBuffer buffer = new StringBuffer();

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir(); // cria o diretorio se, não existir
		}
		File arq = new File(path, "tipos.csv"); // cria arquivo

		if (tfCodigoTipo.getText().isEmpty()) {
			taListaTipos.setText("Insira um codigo valido para exclui-lo");
		} else {
			int codigo = Integer.parseInt(tfCodigoTipo.getText());

			int tamanho = lista.size();

			if (codigo > tamanho || codigo < 0) {
				taListaTipos.setText("Este codigo não existe");
			} else {
				for (int i = tamanho - 1; i >= 0; i--) { // (int i = 0; i < tamanho; i++)
					TipoProduto tipo = (TipoProduto) lista.get(i);
					if (tipo.codIdentificador == codigo) {

					} else {
						buffer.append(tipo.toString() + "\r\n");
					}
				}
				FileWriter fw = new FileWriter(arq);
				PrintWriter pw = new PrintWriter(fw);
				pw.write(buffer.toString());
				pw.flush();
				pw.close();
				fw.close();
				taListaTipos.setText("Codigo Excluido");
			}
			limpaTexto();
		}
	}

	public void limpaTexto() {
		tfCodigoTipo.setText("");
		tfNomeTipo.setText("");
		tfDescricaoTipo.setText("");
	}
}