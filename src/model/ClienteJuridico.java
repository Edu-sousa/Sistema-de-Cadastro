package model;

public class ClienteJuridico extends Endere�o {

	public String cnpj;
	public String nome;
	public Endere�o endereco;
	public String telefone;
	public String email;

	@Override
	public String toString() {
		return cnpj + ";" + nome + ";" + endereco + ";" + telefone + ";" + email;
	}

}
