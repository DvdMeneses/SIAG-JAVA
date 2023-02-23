package persistencia;
import persistencia.Conexao;
import dominio.Agendamento;
import dominio.Cliente;
import dominio.Servico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.postgresql.util.PSQLException;

import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgendamentoDAO {
		private	Conexao c;
		private final String INSERIR = "insert into\"agendamento\" "+"(\"codigo\", \"data\", \"hora\", \"fk_cliente\") values (?,?,?,?)";
		private final String EXCLUIR = "delete from \"agendamento\" where \"codigo\" = ?";
		private final String BUSCAR = "select * from \"agendamento\" where \"codigo\" = ?";
		private final String GERAL = "select * from \"agendamento\"";
		private final String ALTERAR = "update \"agendamento\"set \"data\" = ?,\"hora\" = ? where \"codigo\" = ?" ;
		Scanner teclado = new Scanner(System.in);						
		
		ClienteDAO cliDAO = new ClienteDAO();
		Cliente clienteAux = new Cliente();
		Agenda_ServicoDAO agendaServicoDAOAux = new Agenda_ServicoDAO();
		
		public AgendamentoDAO() {
			c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","david");	
		}
		
		public void Agendar(Agendamento a, String cpf) {
			
			try {
				c.Conectar();
				ClienteDAO cliDAO = new ClienteDAO();
				
				cliDAO.buscar(cpf);
				Cliente clienteAux = new Cliente();
				clienteAux = cliDAO.buscar(cpf);
				
				
				
				
				
				PreparedStatement instrucao = c.getConexao().prepareStatement(INSERIR);
				instrucao.setLong(1,a.getCodigo());
				instrucao.setDate(2, a.getData());
				instrucao.setTime(3,a.getHora());
				instrucao.setString(4,clienteAux.getCpf());
				instrucao.execute();
				c.Desconectar();
			}catch(Exception e) {
				System.out.println("erro de conexão"+ e);
			}
			
		}
		
		
		public  void AlterarAgendamento(Agendamento agendamento,long codigo) {
			
			
			try {
				
				c.Conectar();
				
				PreparedStatement  instrucao = c.getConexao().prepareStatement(ALTERAR);
				instrucao.setDate(1,agendamento.getData());
				instrucao.setTime(2,agendamento.getHora());
				instrucao.setLong(3,codigo);
				instrucao.execute();
				
				c.Desconectar();
			}catch(SQLException  e) {
				System.out.println("ERRO EM ALTERAR AGENDAMENTO"+ e);
			}
			
			
		}
		
		
		public void ExcluirAgendamento(long codigo) {
			try {
				
				c.Conectar();
				PreparedStatement instrucao = c.getConexao().prepareStatement(EXCLUIR);
				instrucao.setLong(1,codigo);
				instrucao.execute();
				c.Desconectar();
				
			}catch(Exception e){
				System.out.println("erro em excluir agendamento!!"+ e);
			}
			
			
			
		}
		
		public Agendamento BuscarAgendamento(Long codigo) {
			Agendamento agenda = null;
			
			
			
			try {
				c.Conectar();
				PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCAR);
				Cliente c1 = new Cliente();
				instrucao.setLong(1, codigo);
				ResultSet rs = instrucao.executeQuery();
				
				
				if(rs.next()) {
					agenda = new Agendamento(rs.getLong("codigo"),rs.getDate("data"),rs.getTime("hora"));
					
					c1.setCpf(rs.getString("fk_cliente"));
					agenda.setCliente(c1);
				}
				
	
			}catch( Exception e){// n to conseguindo tratar essa exe
				System.out.println("CLIENTE NÃO POSSUI AGENDAMENTOS EM ABERTO!"+e);
			}
			
			return agenda;
			
		}
		
		public ArrayList<Agendamento> RelatorioDeAgendamentos(){
			
			ArrayList<Agendamento> RelatorioDeAgendamentos = new ArrayList<Agendamento>();
			ArrayList<Servico> ServicosDeUmAgendamento = new ArrayList<Servico>();
			Servico servicoAux;
			ServicoDAO servicoDAO = new ServicoDAO();
			
			try {
				
				c.Conectar();
				Statement instrucao = c.getConexao().createStatement();
				ResultSet rs = instrucao.executeQuery(GERAL); 
				while(rs.next()) {
				
					Agendamento a = new Agendamento(
							rs.getLong("codigo"),
							rs.getDate("data"),
							rs.getTime("hora"),
							clienteAux = cliDAO.buscar(rs.getString("fk_cliente")));
							a.setCliente(clienteAux);
					
					ServicosDeUmAgendamento = agendaServicoDAOAux.ServicosDeUmAgendamento(rs.getLong("codigo"));
					
					for(int i = 0 ; i < ServicosDeUmAgendamento.size();i++){// for percorrendo o Array de Serviços 
						servicoAux = new Servico();//instancio um novo obj serviço
						servicoAux = servicoDAO.buscarServico(ServicosDeUmAgendamento.get(i).getId()); // preencho ele 
						a.setServicos(servicoAux);// coloco ele no array list do objeto 
					}
					
					RelatorioDeAgendamentos.add(a);
				}
				c.Desconectar();
			}catch(Exception e) {System.out.println("ERRO NA BUSCA GERAL!!" + e);}
			
			
			
			return RelatorioDeAgendamentos;
			
		}
		
		
	
}


