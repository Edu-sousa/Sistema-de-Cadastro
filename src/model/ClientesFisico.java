package model;

public class ClientesFisico extends Endere�o{
	
	public String cpf;
	public String nome;
	public Endere�o endere�o;
	public String celular;
	
	@Override
	public String toString() {
		return  cpf + ";" + nome + ";" + endere�o + ";" + celular;
	}
}
