package model;

public class ClienteFisico extends Endereço {

	public String cpf;
	public String nome;
	public Endereço endereco;
	public String celular;

	@Override
	public String toString() {
		return cpf + ";" + nome + ";" + endereco + ";" + celular;
	}
}
