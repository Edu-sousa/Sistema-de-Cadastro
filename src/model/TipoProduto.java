package model;

public class TipoProduto {

	public int codIdentificador;
	public String nome;
	public String descricao;

	@Override
	public String toString() {
		return codIdentificador + ";" + nome + ";" + descricao;
	}
}
