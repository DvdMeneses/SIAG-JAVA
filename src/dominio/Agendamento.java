
	
package dominio;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import persistencia.ClienteDAO;

public class Agendamento {
	
	private long codigo;
	private Date data;
	private Time hora;
	private Cliente fk_cliente;
	ArrayList <Servico> servicos; 
	
	
	public Agendamento(long codigo,Date data, Time hora) {
		this.codigo=codigo;
		this.data = data;
		this.hora = hora;
	}
		
	public Agendamento(long codigo,Date data, Time hora,Cliente fk_cliente) {
		this.codigo =codigo;
		this.data = data;
		this.hora = hora;
		this.servicos = new ArrayList<Servico>();	
	}
	
	public Agendamento() {
		this.servicos = new ArrayList<Servico>();	
	}
	

	
	
	// metodos
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public Cliente getCliente() {
		return fk_cliente;
	}
	public void setCliente(Cliente cliente) {
		
		ClienteDAO cliDAO= new ClienteDAO();
		
		this.fk_cliente = cliDAO.buscar(cliente.getCpf());
	}
	
	public void setServicos(Servico servico) {
		this.servicos.add(servico);
	
		
	}
	
	public ArrayList<Servico> getServicos( ) {
		return this.servicos;
	}

}

	

	


	
