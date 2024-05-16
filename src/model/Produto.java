package model;

public class Produto {

	public TipoProduto tipoProduto;
	public String nome;
	public float valor;
	public String descricao;
	public int qtdEstoque;	
	
	@Override
	public String toString() {
		return  tipoProduto.codIdentificador+";"+nome + ";" + valor + ";" + descricao + ";" + qtdEstoque;
	}
}
