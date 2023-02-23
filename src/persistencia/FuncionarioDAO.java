package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.Funcionario;
import dominio.Servico;

public class FuncionarioDAO {
	
	private Conexao c;
	private final String INSERIR = "insert into \"funcionario\" "+"(\"carteiradetrabalho\",\"salario\",\"email\",\"numero\") values (?,?,?,?)";
	private final String EXCLUIR = "delete from \"funcionario\"where \"carteiradetrabalho\"=?";	
	private final String GERAL = "select * from \"funcionario\"";
	private final String BUSCAR = "select * from \"funcionario\" where \"carteiradetrabalho\"=?";
	private final String ALTERAR = "update \"funcionario\" set \"carteiradetrabalho\"=?, \"salario\"=?, \"email\"=?, \"numero\"=? where \"carteiradetrabalho\"=?";
	
	
	
	
	
	public FuncionarioDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","david");
	}
	
	
	
	public void AdicionarFuncionario(Funcionario f) {
		try {
			c.Conectar();
	
			PreparedStatement instrucao = c.getConexao().prepareStatement(INSERIR);
			instrucao.setString(1,f.getCarteiraTrab());
			instrucao.setFloat(2,f.getSalario());
			instrucao.setString(3,f.getEmail());
			instrucao.setInt(4,f.getNumero());
			
			instrucao.execute();
			c.Desconectar();
			
		}catch(SQLException e) {
			System.out.println("ERRO AO INSERIR O USUARIO "+e);
		};
	}
	
	public void alterarFuncionario(Funcionario f, String trab) {
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1,f.getCarteiraTrab());
			instrucao.setFloat(2,f.getSalario());
			instrucao.setString(3,f.getEmail());
			instrucao.setInt(4,f.getNumero());
			
			instrucao.setString(6, trab);
			instrucao.execute();
			c.Desconectar();
			
		}catch(SQLException e) {
			System.out.println("ERRO AO ALTERAR" + e);
		}
	}
	
	public void excluirFuncionario(String carteiraTrab) {
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(EXCLUIR);
			instrucao.setString (1, carteiraTrab);
			instrucao.execute();
			c.Desconectar();
		}catch(SQLException e) {
			System.out.println("Erro para excluir");
		}
	}
	
	public ArrayList<Funcionario> RelatorioFuncionarios(){
		ArrayList<Funcionario> RelatorioDeFuncionarios = new ArrayList<>();
		FuncionarioDAO funcionarioDAOaux = new FuncionarioDAO();
		
		try {
			c.Conectar();
			
			Statement instrucao = c.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(GERAL);
			
			while(rs.next()) {
				Funcionario f1 = new Funcionario(
				rs.getString("carteiradetrabalho"),
				rs.getFloat("salario"),
				rs.getString("email"),
				rs.getInt("numero"));
				RelatorioDeFuncionarios.add(f1);
				
			}
			
			
			
			
			c.Desconectar();
		}catch(SQLException e){
			System.out.println("ERRO NO RELATORIO "+e);
		}
		return RelatorioDeFuncionarios;
	}
	
	public Funcionario buscarFun(String carteiraTrab) {
		Funcionario f = null;
        try {
            c.Conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, carteiraTrab);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()) {
                 f = new Funcionario (rs.getString("carteiradetrabalho"), rs.getFloat("salario"), rs.getString("email"), rs.getInt("numero"));
            }
            c.Desconectar();
        }catch(SQLException e) {
            System.out.println("Erro na busca " +e);
        }
        return f;

    }
	
	
}


