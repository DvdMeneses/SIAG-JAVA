package persistencia;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.Funcionario;
import dominio.Servico;

public class ServicoDAO {
	private Conexao c;
	private final String INSERIR = "insert into \"servico\" "+"(\"id\",\"tipo\",\"fk_funcionario\") values (?,?,?)";
	private final String EXCLUIR = "delete from \"servico\"where \"id\"=?";	
	private final String GERAL = "select * from \"servico\"";
	private final String BUSCAR = "select * from \"servico\" where \"id\"=?";
	private final String ALTERAR = "update \"servico\" set \"id\"=?, \"tipo\"=?, where \"id\"=?";


	public ServicoDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","david");
	}


	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	Funcionario funcionarioAux = new Funcionario();
	
	public void inserirServico(Servico servico,String carteiraTrab) {
		
		try {
			
			c.Conectar();
			
			
			
			
			funcionarioAux = funcionarioDAO.buscarFun(carteiraTrab);
			PreparedStatement instrucao = c.getConexao().prepareStatement(INSERIR);
			instrucao.setInt(1, servico.getId());
			instrucao.setString(2, servico.getTipo());
			instrucao.setString(3,funcionarioAux.getCarteiraTrab());
			instrucao.execute();
			
			c.Desconectar();
		}catch(SQLException e) {
			System.out.println("Erro na inclusao" + e);
		}
	}
	
	public void excluirServico(int id) {
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(EXCLUIR);
			instrucao.setInt(1, id);
			instrucao.execute();
			c.Desconectar();
			
		}catch(SQLException e){
			System.out.println("Erro na exclusao do servico"+e);
		}
		
	}
	
	public Servico  buscarServico(int id) {
        Servico servico = null;
        try {
            c.Conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCAR);
            instrucao.setInt(1, id);
            ResultSet rs = instrucao.executeQuery();
            
            if(rs.next()) {
                servico = new Servico (rs.getInt("id"), rs.getString("tipo"));
                funcionarioAux.setCarteiraTrab(rs.getString("fk_funcionario"));
            }
            c.Desconectar();
        }catch(SQLException e) {
            System.out.println("Erro na busca"+e);
        }
        return servico;

    }

	public void alterarCliente(Servico servico, int idDeBusca) {
			
			try {
				c.Conectar();
				PreparedStatement instrucao = c.getConexao().prepareStatement(ALTERAR);
				instrucao.setInt(1, servico.getId());
				instrucao.setString(2, servico.getTipo());
				instrucao.setInt(2, idDeBusca);
				
				instrucao.execute();
				c.Desconectar();
				
				
			}catch(SQLException e) {
				System.out.println("Erro na alteracao" + e);
			}
			
		}
		
	public ArrayList<Servico> servicoTotal(){
        ArrayList<Servico> lista = new ArrayList<>();

        try {
            c.Conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(GERAL);
            while(rs.next()) {
                Servico servico = new Servico (
                		rs.getInt("id"),
                		rs.getString("tipo"),
                		funcionarioAux = funcionarioDAO.buscarFun(rs.getString("fk_funcionario")));
				
                servico.setFuncionario(funcionarioAux);
                
                lista.add(servico);
            }
            c.Desconectar();
        }catch(SQLException e){
            System.out.println("Erro na lista "+e);
        }
        return lista;
    }
		

}
