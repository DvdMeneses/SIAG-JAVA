package dominio;


public class Cliente {
	private String email;
	private String numero;
	private String cpf;
	

	public Cliente(){
		
	}

	public Cliente(String email, String numero, String cpf) {
		this.email = email;
		this.numero = numero;
		this.cpf  = cpf;
	}
	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf.replaceAll("[.]", "");
		
		
	}


}