package dominio;

public class Funcionario {
	private float salario;
	private String email;
	private String carteiraTrab;
	private int numero;
	
	

	
	
	public Funcionario() {

	}

	public Funcionario(String carteiraTrab,float salario, String email, int numero){
		this.salario = salario;
		this.email = email;
		this.carteiraTrab = carteiraTrab;
		this.numero = numero;
		
	

	} 

	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;

	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCarteiraTrab() {
		return carteiraTrab;
	}
	public void setCarteiraTrab(String carteiraTrab) {
		this.carteiraTrab = carteiraTrab;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	

}
