package model;

public class PessoaJuridica extends Endere�o {

	String cnpj;
	String nomeFantasia;
	Endere�o endereco;
	String telefone;
	String email;
	
	
	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", nomeFantasia=" + nomeFantasia + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
	
	
	
}
