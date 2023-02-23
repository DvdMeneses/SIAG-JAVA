package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
	private String usuario;
	private String senha;
	private String caminho;
	private Connection conexao;
	
	
	public Conexao(String c, String u, String s) {
		this.usuario = u;
		this.senha = s ;
		this.caminho = c;
		
	}
	
	public void Conectar() {
		try{
			Class.forName("org.postgresql.Driver");//caminho do driver \ cria a conexao com o postgre
			conexao = DriverManager.getConnection(caminho,usuario,senha);
			
		}catch(Exception e) {
			System.out.println("erro!");
		};
		
	}
	
	
	public void Desconectar() {
		try {
			conexao.close();
			
			
		}catch(Exception e) {System.out.println("ERRO DE DESCONEXÃO");}
	}

	
	public Connection getConexao() {
		return conexao;
	}
	
}


