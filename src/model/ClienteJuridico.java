package model;

public class ClienteJuridico extends Endereço {

	public String cnpj;
	public String nome;
	public Endereço endereco;
	public String telefone;
	public String email;

	@Override
	public String toString() {
		return cnpj + ";" + nome + ";" + endereco + ";" + telefone + ";" + email;
	}

}
