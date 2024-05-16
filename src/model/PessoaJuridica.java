package model;

public class PessoaJuridica extends Endereço {

	String cnpj;
	String nomeFantasia;
	Endereço endereco;
	String telefone;
	String email;
	
	
	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", nomeFantasia=" + nomeFantasia + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
	
	
	
}
