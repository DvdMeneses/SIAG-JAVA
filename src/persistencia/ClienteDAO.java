package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Conexao;
import dominio.Cliente;
public class ClienteDAO {
	private Conexao c;
	private final String INCLUIR = "insert into \"cliente\" " + "(\"email\", \"numero\" ,\"cpf\") values (?,?,?)";	
	private final String EXCLUIR = "delete from \"cliente\" where \"cpf\"=?";
	private final String BUSCAR = "select * from \"cliente\" where \"cpf\"=?";
	private final String ALTERAR = "update \"cliente\" set \"cpf\"=?, \"numero\"=?, \"email\"=? where \"cpf\"=?";
	private final String GERAL = "select * from \"cliente\"";
	
	public ClienteDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","david");
	}
	
	public void inserirCliente(Cliente cli) {
		try {
			System.out.println("t0 no try DE CLIENTE ");
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, cli.getEmail());
			instrucao.setString(2, cli.getNumero());
			instrucao.setString(3, cli.getCpf());
			
			
			instrucao.execute();
			
			c.Desconectar();
		}catch(SQLException e) {
			System.out.println("Erro na inclusao" + e);
		}
	}
	public void excluirCliente(String cpf) {
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(EXCLUIR);
			instrucao.setString(1, cpf);
			instrucao.execute();
			c.Desconectar();
			
		}catch(SQLException e){
			System.out.println("Erro na exclusao"+e);
		}
		
	}
	
	public Cliente buscar(String cpf) {
        Cliente cli = null;
        try {
            c.Conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, cpf);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()) {
                cli = new Cliente (rs.getString("email"), rs.getString("numero"), rs.getString("cpf"));
            }
            c.Desconectar();
        }catch(SQLException e) {
            System.out.println("Erro na busca"+e);
        }
        return cli;

    }
	
	public void alterarCliente(Cliente cli, String cpfAntigo) {
		
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1, cli.getCpf());
			instrucao.setString(2, cli.getNumero());
			instrucao.setString(3, cli.getEmail());
			instrucao.setString(4, cpfAntigo);
			instrucao.execute();
			c.Desconectar();
			
			
		}catch(SQLException e) {
			System.out.println("Erro na alteracao" + e);
		}
		
	}
	
	public ArrayList<Cliente> clientesTotal(){
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            c.Conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(GERAL);
            while(rs.next()) {
                Cliente cli = new Cliente (rs.getString("email"), rs.getString("numero"), rs.getString("cpf"));
                lista.add(cli);
            }
            c.Desconectar();
        }catch(SQLException e){
            System.out.println("Erro na lista"+e);
        }
        return lista;
    }

	
	
}
