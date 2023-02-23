package dominio;

public class Agenda_Servico {
	private int id;
	private Agendamento fk_agendamento;
	private Servico fk_servico;
	
	
	public Agenda_Servico() {
		
	}
	
	public Agenda_Servico(int id, Agendamento fk_agendamento, Servico fk_servico) {
		this.id = id;
		this.fk_agendamento = fk_agendamento;
		this.fk_servico = fk_servico;

	}


	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Agendamento getFk_agendamento() {
		return fk_agendamento;
	}
	public void setFk_agendamento(Agendamento fk_agendamento) {
		this.fk_agendamento = fk_agendamento;
	}
	public Servico getFk_servico() {
		return fk_servico;
	}
	public void setFk_servico(Servico fk_servico) {
		this.fk_servico = fk_servico;
	}

}
