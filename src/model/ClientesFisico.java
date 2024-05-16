package model;

public class ClientesFisico extends Endereço{
	
	public String cpf;
	public String nome;
	public Endereço endereço;
	public String celular;
	
	@Override
	public String toString() {
		return  cpf + ";" + nome + ";" + endereço + ";" + celular;
	}
}
