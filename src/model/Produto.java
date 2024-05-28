package model;

import java.util.Objects;

public class Produto {

	public TipoProduto tipoProduto;
	public String nome;
	public float valor;
	public String descricao;
	public int qtdEstoque;

	@Override
	public int hashCode() {
		return tipoProduto.codIdentificador;
//		return Objects.hash(descricao, nome, qtdEstoque, tipoProduto, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(nome, other.nome)
				&& qtdEstoque == other.qtdEstoque && Objects.equals(tipoProduto, other.tipoProduto)
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor);
	}

	@Override
	public String toString() {
		return tipoProduto.codIdentificador + ";" + nome + ";" + valor + ";" + descricao + ";" + qtdEstoque;
	}
}
