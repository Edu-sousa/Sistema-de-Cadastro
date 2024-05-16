package model;

public class Endereço {

	public String logradouro;
	public String numeroPorta;
	public String complemento;
	public String cep;

	@Override
	public String toString() {
		return logradouro + ";" + numeroPorta + ";" + complemento + ";" + cep;
	}

}
