package model;

public class ClienteFisico extends Endere�o {

	public String cpf;
	public String nome;
	public Endere�o endereco;
	public String celular;

	@Override
	public String toString() {
		return cpf + ";" + nome + ";" + endereco + ";" + celular;
	}
}
