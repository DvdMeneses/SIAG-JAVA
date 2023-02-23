package visao;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dominio.Agenda_Servico;
import dominio.Agendamento;
import dominio.Cliente;
import dominio.Funcionario;
import dominio.Servico;
import persistencia.Agenda_ServicoDAO;
import persistencia.AgendamentoDAO;
import persistencia.ClienteDAO;
import persistencia.FuncionarioDAO;
import persistencia.ServicoDAO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");	// FORMATADOR DA DATA
		SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm"); 		// FORMATADOR DA HORA
		
		Scanner teclado = new Scanner(System.in);// LER OS INPUTS DO TECLADO
		
		//  CLIENTE 
		ClienteDAO cliDAO = new ClienteDAO();
		Cliente cliSelect = new Cliente();
		ArrayList<Cliente> RelatorioDeClientes = new ArrayList<Cliente>();

		//  FUNCIONARIO
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionarioAux= new Funcionario();
		ArrayList<Funcionario> RelatorioDeFuncionarios = new ArrayList<>();
		
		//  SERVICO
		Servico servicoAux = new Servico();
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayList<Servico> RelatorioDeServicos = new ArrayList<Servico>();
		
		//  AGENDAMENTO 
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		ArrayList<Agendamento> RelatorioDeAgendamentos = new ArrayList<>();
		
		//  AGENDA_SERVICO 
		Agenda_Servico agendaServicoAux = new Agenda_Servico();
		Agenda_ServicoDAO agendaServicoDAO = new Agenda_ServicoDAO();
		ArrayList <Agenda_Servico> RelatorioDeAgenda_Servico = new ArrayList<>();
		
		// VARIAVEIS DE APOIO
		int op, senha, i, chance;
		boolean acertou;
		String CPFaux;
		
		// UTILIZAMOS PARA GERAR UM NUMERO RANDOMICO QUE SERVIRÁ PARA O CODIGO DO AGENDAMENTO
		Long codigoRandom;
		Random codigoRandomAux = new Random();
		Random codigo = new Random();

		
		do {// MENU INICIAL

			System.out.println("\t Menu Principal");
			System.out.println("------------------------------");
			System.out.println("1-Informações de Funcionario");
			System.out.println("2-Informações de cliente");
			System.out.println("3-Agendamentos");
			System.out.println("4-Serviços");
			System.out.println("5-Sair do Programa");
			System.out.print("--> ");
			op = teclado.nextInt();
			System.out.println("------------------------------");

			switch (op) {
			case 1:
				i = 0;
				do {
					System.out.println("Digite a senha");
					senha = teclado.nextInt();
					if (senha == 1234) {
						System.out.println("Acesso aceito\n");
						do {
							System.out.println("\nMenu de Gestão");
							System.out.println("--------------------------------");
							System.out.println("1-Adicionar Funcionario");
							System.out.println("2-Demitir Funcionario");
							System.out.println("3-Alterar Funcionario");
							System.out.println("4-Relatorio de Funcionario");
							System.out.println("5-Ver Funcionario por carteira");
							System.out.println("6-Gasto Total com Funcionarios");
							System.out.println("7-Voltar ao Menu Principal");

							System.out.print("--> ");
							op = teclado.nextInt();
							System.out.println("------------------------------");

							switch (op) {

							case 1:
								chance = 0;
								String identificadorAUX;
								teclado.nextLine();// comer o enter
								do {

									Funcionario f1 = new Funcionario();

									System.out.println("Carteira de Trabalho do Funcionario");
									f1.setCarteiraTrab(teclado.nextLine());
									identificadorAUX = f1.getCarteiraTrab();

									if (funcionarioDAO.buscarFun(identificadorAUX) == null) {// teste de se ja existe
										System.out.println("Salario do Funcionario");

										f1.setSalario(teclado.nextFloat());
										teclado.nextLine();
										System.out.println("E-mail do Funcionario");
										f1.setEmail(teclado.nextLine());

										System.out.println("Numero do Funcionario");
										f1.setNumero(teclado.nextInt());
										System.out.println(f1.getNumero());
										teclado.nextLine();
										funcionarioDAO.AdicionarFuncionario(f1);


										
										chance = 3;
									} else {
										chance++;
										i = 3 - chance;
										System.out.println("FUNCIONARIO JA CADASTRADO!!");
										System.out.println("\nVOCE TEM  : " + i + " tentativas!!");

									}
								} while (chance != 3);

								break;
							case 2:
								System.out.println("Nº CARTEIRA TRAB PARA DEMIITIR FUNCIONARIO");
								teclado.nextLine();
								funcionarioDAO.excluirFuncionario(teclado.nextLine());
								break;

							case 3:
								i = 0;
								String cartAux;
								Funcionario f2 = new Funcionario();
								System.out.println("DIGITE A CARTEIRA DE TRABALHO PARA ALTERAR");
								teclado.nextLine();
								cartAux = teclado.nextLine();
								f2.setCarteiraTrab(cartAux);
								do {
									if (funcionarioDAO.buscarFun(cartAux) != null) {
										System.out.println("Digite a carteira de Trabalho");
										f2.setCarteiraTrab(teclado.nextLine());
										System.out.println("Digite o Salario");
										f2.setSalario(teclado.nextFloat());
										teclado.nextLine();
										System.out.println("Digite o Email");
										f2.setEmail(teclado.nextLine());
										System.out.println("Digite Numero do Funcionario");
										f2.setNumero(teclado.nextInt());
										i = 3;
									}
								} while (i != 3);

								break;
							case 4:
								RelatorioDeFuncionarios = funcionarioDAO.RelatorioFuncionarios();
								System.out.println("=========================================================");
								for (i = 0; i < RelatorioDeFuncionarios.size(); i++) {
									System.out.println("Nº CARTEIRA DE TRABALHO  -> "
											+ RelatorioDeFuncionarios.get(i).getCarteiraTrab()
											+ "\n SALARIO DO FUNCIONARIO -> "
											+ RelatorioDeFuncionarios.get(i).getSalario() + "\n EMAIL DO FUNCIONARIO ->"
											+ RelatorioDeFuncionarios.get(i).getEmail() + "\n NUMERO DO FUNCIONARIO -> "
											+ RelatorioDeFuncionarios.get(i).getNumero());
									System.out.println("=========================================================");
								}
								break;
							case 5:
								Funcionario f1 = new Funcionario();
								System.out.println("N° CARTEIRA TRAB PARA ENCONTRAR FUNCIONARIO");
								teclado.nextLine();
								f1 = funcionarioDAO.buscarFun(teclado.nextLine());
								System.out.println("=========================================================");
								System.out.println("\nEMAIL: " + f1.getEmail());
								System.out.println("NUMERO: " + f1.getNumero());
								System.out.println("CARTEIRA TRAB: " + f1.getCarteiraTrab());
								System.out.println("SALARIO: " + f1.getSalario());
								
								System.out.println("=========================================================");
								break;

							case 6:
								float soma = 0;
								RelatorioDeFuncionarios = funcionarioDAO.RelatorioFuncionarios();
								System.out.println("=========================================================");
								for (i = 0; i < RelatorioDeFuncionarios.size(); i++) {
									soma = RelatorioDeFuncionarios.get(i).getSalario() + soma;

								}
								System.out.println("SALARIO TOTAL DOS FUNCIONARIOS -> " + soma);

								break;
							case 7:
								i = 3;
								break;
							default:
								System.out.println("opção invalida!!");

							}

						} while (op != 7);
					} else {
						System.out.println("Acesso negado");
						i++;
					}
				} while (i != 3);
				break;
			case 2:

				i = 0;
				do {
					System.out.println("Digite a senha");
					senha = teclado.nextInt();
					if (senha == 1234) {
						System.out.println("Acesso aceito");
						do {
							System.out.println("Menu Secundario 2");
							System.out.println("--------------------------------");
							System.out.println("1-Adicionar Cliente");
							System.out.println("2-Remover Cliente");
							System.out.println("3-Alterar Cliente");
							System.out.println("4-Ver todos os Clientes");
							System.out.println("5-Ver Cliente  por CPF");
							System.out.println("6-Voltar ao Menu Principal");
							op = teclado.nextInt();
							switch (op) {
							case 1:
								chance = 0;
								String identificadorAUX;
								teclado.nextLine();// comer o enter

								do {
									Cliente c1 = new Cliente();

									System.out.println("\nDigite seu CPF");
									identificadorAUX = teclado.nextLine();

									c1.setCpf(identificadorAUX);

									if (cliDAO.buscar(identificadorAUX) == null) {// teste para ver se ja existe

										System.out.println("Digite seu Email");
										c1.setEmail(teclado.nextLine());

										System.out.println("Digite seu Numero");
										c1.setNumero(teclado.nextLine());

										cliDAO.inserirCliente(c1);
										chance = 3;

									} else {
										chance++;
										i = 3 - chance;
										System.out.println("CLIENTE JA CADASTRADO!!");
										System.out.println("\nVOCE TEM  : " + i + " tentativas!!");

									}
								} while (chance != 3);

								break;
							case 2:
								int op2;
								System.out.println("CPF DO CLINETE QUE DESEJA REMOVER");
								teclado.nextLine();
								CPFaux = teclado.nextLine();// antes de exluir deve-se ver se ele não tem nem um
								// agendamento, ou seja buscamos o cliente verificamos se n
								// há agendamento e depois exluimos.
								System.out.println(
										"VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O CLIENTE?\nTODOS OS SEUS AGENDAMENTOS EM ABERTOS SERAM EXLUIDOS CONSEQUENTEMENTE!!\n 1-SIM, EU CONFIRMO|2-CANCELAR");
								op2 = teclado.nextInt();

								if (op2 == 1) {
									cliDAO.excluirCliente(CPFaux);
								} else {
									System.out.println("CANCELANDO...");
								}

								break;

							case 3:

								Cliente c1 = new Cliente();
								i = 0;

								do {
									System.out.println("CPF DO CLIENTE QUE DESEJA ALTERAR");
									teclado.nextLine();
									CPFaux = teclado.nextLine();
									
									c1.setCpf(CPFaux);
									if (cliDAO.buscar(CPFaux) != null) {
										System.out.println("Digite o CPF");
										c1.setCpf(teclado.nextLine());
										System.out.println("Digite o numero");
										c1.setNumero(teclado.nextLine());
										System.out.println("Digite o email");
										c1.setEmail(teclado.nextLine());
										cliDAO.alterarCliente(c1, CPFaux);
										i = 3;
									} else {
										System.out.println("CPF nao encontrado");
										i++;
									}
								} while (i != 3);

								break;
							case 4:

								System.out.println("Todos os clientes: ");

								RelatorioDeClientes = cliDAO.clientesTotal();

								System.out.println("=========================================================");
								for (i = 0; i < RelatorioDeClientes.size(); i++) {
									System.out.println("Email do cliente " + i + " -> "
											+ RelatorioDeClientes.get(i).getEmail() + "\nNumero do cliente:" + i + "-> "
											+ RelatorioDeClientes.get(i).getNumero() + "\ncpf do cliente" + i + "-> "
											+ RelatorioDeClientes.get(i).getCpf());
									System.out.println("=========================================================");
								}

								break;
							case 5:
								System.out.println("CPF DO CLIENTE QUE DESEJA BUSCAR");
								teclado.nextLine();
								cliSelect = cliDAO.buscar(teclado.nextLine());

								System.out.println("\nEMAIL: " + cliSelect.getEmail());
								System.out.println("NUMERO: " + cliSelect.getNumero());
								System.out.println("CPF: " + cliSelect.getCpf());

								break;
							case 6:
								i = 3;
								break;
							default:
								System.out.println("Opção invalida");
							}
						} while (op != 6);
					} else {
						System.out.println("Acesso negado");
						i++;
					}
				} while (i != 3);
				break;
			case 3:
				int opAux;
				do {
					System.out.println("Menu de Agendamento");
					System.out.println("---------------------");
					System.out.println("1-Marcar Agendamento");
					System.out.println("2-Excluir Agendamento");
					System.out.println("3-Alterar Agendamento");
					System.out.println("4-Relatorio Agendamentos");
					System.out.println("5-voltar ao menu principal");
					opAux = teclado.nextInt();

					switch (opAux) {
					case 1://marcar agendamento

						String cpfAux, dataNaoFormatada, horarioNaoFormatado;
						chance = 0;
						String identificadorAUX;
						java.sql.Date dataSQL;
						java.util.Date dataJava = new Date();
						Date timeUtil = null;
						Time timeSql;
						int IDAUX;

						//================================AGENDAR===========================================

						Agendamento a1 = new Agendamento();

						teclado.nextLine();// comer o enter

						codigoRandom = codigoRandomAux.nextLong() * 100;

						if (codigoRandom < 0) {
							codigoRandom = codigoRandom * -1;
						}
						System.out.println("-------------------------------------------------------");
						System.out.println("CODIGO AGENDAMENTO: " + codigoRandom);
						a1.setCodigo(codigoRandom);
						System.out.println("-------------------------------------------------------");
						System.out.println("DIGITE SEU CPF");
						cpfAux = teclado.nextLine();

						Cliente clienteAux = new Cliente();
						clienteAux = cliDAO.buscar(cpfAux);

						//================================VER SE O CLIENTE EXISTE PARA AGENDAR===========================================
						if (clienteAux == null) {

							System.out.println("CLIENTE NÃO CONSTA NO BANCO DADOS!!");


							Cliente c1 = new Cliente();
							System.out.println("DIGITE SEU CPF");
							cpfAux =(teclado.nextLine());
							c1.setCpf(cpfAux);
							System.out.println("Digite seu Email");
							c1.setEmail(teclado.nextLine());
							System.out.println("Digite seu Numero");
							c1.setNumero(teclado.nextLine());
							cliDAO.inserirCliente(c1);
						}
						
						// =========================TRATAR DATA E HORA DO AGENDAMENTO=====================================================
						System.out.println("Digite a Data - FORMATO: dd/MM/yyyy");
						dataNaoFormatada = teclado.nextLine();// PEGAMOS A STRING
						try {
							dataJava = formatadorData.parse(dataNaoFormatada);
						} catch (ParseException e) {
							System.out.println(e + "ERRO EM ALTERAR O AGENDAMENTO NA VISAO");
						} 
						dataSQL = new java.sql.Date(dataJava.getTime());// PASSAMOS A STRING PARA UM DATE JAVA UTIL
						a1.setData(dataSQL);// PREENCHEMOS DATA
						
						System.out.println("Digite o HORARIO  - FORMATO: HH:mm");
						horarioNaoFormatado = teclado.nextLine();
						try {
							timeUtil = formatadorHora.parse(horarioNaoFormatado);
						} catch (ParseException e) {
							System.out.println(e + "ERRO EM ALTERAR O AGENDAMENTO NA VISAO");
						}
						timeSql = new Time(timeUtil.getTime());
						a1.setHora(timeSql);
						
						//============================= SETAR SERVIÇO EM AGENDAMENTO ======================================================
						
						System.out.println("================================SERVIÇOS=================================");
						RelatorioDeServicos = servicoDAO.servicoTotal();
						
						System.out.println("=========================================================");
						for (i = 0; i < RelatorioDeServicos.size(); i++) {
							System.out.println("ID do SERVIÇO" + i + " -> "+ RelatorioDeServicos.get(i).getId() + 
									"\nTIPO do SERVIÇO:" + i + "-> "+ RelatorioDeServicos.get(i).getTipo());
							System.out.println("=========================================================");
						}
						int adicionar;
						boolean primeiraVez = true;
						do {
							adicionar = 0;	
							int servico1;
							Servico s2 = new Servico();
							System.out.println("Digite o SERVIÇO QUE DESEJA");
							//USO A DAO DE SERVICO PARA BUSCAR UM SRVIÇO COM ID QUE FOI ESCOLHIDO A DAO RETORNA UM OBJETO QUE JOGO PARA O DOMINIO DE AGENDAMENTO  LA EU ADICIONO DENTRO DA LISTA DE SERVICOS 
							servico1 = teclado.nextInt();
							servicoAux = servicoDAO.buscarServico(servico1);
	
							if(servicoDAO.buscarServico(servico1) != null){
								a1.setServicos(servicoAux);//preenchendo o array list do Agendamento 
								if(primeiraVez) {
								agendamentoDAO.Agendar(a1,cpfAux);// AGENDAMENTO TOTALEMNTE PREENCHIDO
								}
								agendaServicoAux.setFk_servico(servicoAux);// setou o FK_serviço NO OBJETO DA  TERCEIRA TABELA 
								agendaServicoAux.setFk_agendamento(a1);// setou o FK_agendamento NO OBJETO DA TERCEIRA TABELA 
								
								agendaServicoDAO.inserirAgendaServico(agendaServicoAux);
							}else {
								System.out.println("Servico ja existe");
							}
							System.out.println("DESEJA ADICIONAR MAIS UM SERVIÇO AO AGENDAMENTO? 1 - SIM | 2 - NÃO ");
								adicionar= teclado.nextInt();
								primeiraVez = false;
						}while(adicionar != 2);
						
						
						
						break;

					case 2://excluir
						System.out.println("CODIGO DO AGENDAMENTO");
						teclado.nextLine();// comer enter
						agendamentoDAO.ExcluirAgendamento(teclado.nextLong());
						System.out.println("EXCLUINDO...");

						break;

					case 3://alterar
						long CodigoAux;
						a1 = new Agendamento();

						i = 0;

						do {
							System.out.println("CODIGO DO AGENDAMENTO QUE DESEJA ALTERAR");
							teclado.nextLine();
							CodigoAux = teclado.nextLong();
							a1.setCodigo(CodigoAux);
							java.util.Date dataJavaAlterar = new Date();

							if (agendamentoDAO.BuscarAgendamento(CodigoAux) != null) {

								System.out.println("Digite a Data - FORMATO: dd/MM/yyyy");// TRATAMOS O A STRING PARA/ PODERMOS ALOCAR UMA DATE// SQL;
								teclado.nextLine();
								dataNaoFormatada = teclado.nextLine();// PEGAMOS A STRING


								try {
									dataJavaAlterar = formatadorData.parse(dataNaoFormatada);// PASSAMOS A STRING PARA UM DATE JAVA UTIL
								} catch (ParseException e) {
									System.out.println(e + "ERRO EM ALTERAR O AGENDAMENTO NA VISAO");

								} 

								dataSQL = new java.sql.Date(dataJavaAlterar.getTime());// PARA SQL
								a1.setData(dataSQL);// PREENCHEMOS DATA

								System.out.println("Digite o HORARIO  - FORMATO: HH:mm");
								horarioNaoFormatado = teclado.nextLine();

								try {
									timeUtil = formatadorHora.parse(horarioNaoFormatado);
									timeSql = new Time(timeUtil.getTime());
									a1.setHora(timeSql);
									agendamentoDAO.AlterarAgendamento(a1, CodigoAux);
								} catch (ParseException e) {

									System.out.println(e + "ERRO EM ALTERAR O AGENDAMENTO NA VISAO");
								}

								i = 3;
							} else {
								System.out.println("CODIGO NAO ENCONTRDO");
								i++;
							}
						} while (i != 3);
						
						break;

					case 4:

						RelatorioDeAgendamentos = agendamentoDAO.RelatorioDeAgendamentos();
						
						System.out.println("=========================================================");
						
						for (i = 0; i < RelatorioDeAgendamentos.size(); i++) {

							System.out.println("\t\tCODIGO\n\t" + RelatorioDeAgendamentos.get(i).getCodigo());
							System.out.println("\nDATA: " + RelatorioDeAgendamentos.get(i).getData());
							System.out.println("HORA:  " + RelatorioDeAgendamentos.get(i).getHora());
							System.out.println("CPF CLIENTE: " + RelatorioDeAgendamentos.get(i).getCliente().getCpf());
							System.out.println("EMAIL CLIENTE:" + RelatorioDeAgendamentos.get(i).getCliente().getEmail());
							System.out.println("NUMERO CLIENTE: " + RelatorioDeAgendamentos.get(i).getCliente().getNumero());
				
							
							for(int j = 0; j <RelatorioDeAgendamentos.get(i).getServicos().size() ;j++) {
								
								System.out.println("SERVIÇO " + j +": "+ RelatorioDeAgendamentos.get(i).getServicos().get(j).getTipo());// NOME DO SERVIÇO
							}

							System.out.println("\n=========================================================");
						}

						break;			
					
					}

				} while (opAux != 5);
				break;

			case 4:
				i=0;
				do {

					do {
						System.out.println("Menu Secundario SERVICO");
						System.out.println("--------------------------------");
						System.out.println("1-Adicionar Serviço");
						System.out.println("2-Remover Serviço");
						System.out.println("3-Alterar Serviço");
						System.out.println("4-Ver todos os Serviço");
						System.out.println("5-Ver Servico  por ID");
						System.out.println("6-Voltar ao Menu Principal");
						op = teclado.nextInt();
						switch (op) {
						case 1:
							chance = 0;
							int identificadorAUX;
							teclado.nextLine();// comer o enter
							
							do {
								
								Servico s1 = new Servico();

								System.out.println("\nDigite o ID do SERVIÇO");
								identificadorAUX = teclado.nextInt();
								teclado.nextLine();
								s1.setId(identificadorAUX);

								if (servicoDAO.buscarServico(identificadorAUX) == null) {// teste para ver se ja existe

									System.out.println("Digite o Tipo do Serviço");
									s1.setTipo(teclado.nextLine());
									
									//============================= SETAR FUNCIONARIO EM SERVIÇO =============================================
									System.out.println("================================FUNCIONARIOS=================================");
									RelatorioDeFuncionarios = funcionarioDAO.RelatorioFuncionarios();

									System.out.println("=========================================================");
									for (i = 0; i < RelatorioDeFuncionarios.size(); i++) {
										System.out.println("CARTEIRA DE TRABALHO DO FUNCIONARIO" + i + " -> "+ RelatorioDeFuncionarios.get(i).getCarteiraTrab() + 
												"\nNUMERO DO FUNCIONARIO:" + i + "-> "+ RelatorioDeFuncionarios.get(i).getNumero());
										System.out.println("=========================================================");
									}
									String funcionarioResponsavel;
									System.out.println("DIGITE  A FUNCIONARIO RESPONSAVEL");
									//USO A DAO DE 	FUNCIONARIO PARA BUSCAR UM FUNCIONARIO COM ID QUE FOI ESCOLHIDO A DAO RETORNA UM OBJETO QUE PREENCHO O FK_FUNCIOINARIO DE SERVIÇO;
									
									funcionarioResponsavel = teclado.nextLine();
									funcionarioAux = funcionarioDAO.buscarFun(funcionarioResponsavel);

									if(funcionarioDAO.buscarFun(funcionarioResponsavel) != null){
										
										servicoDAO.inserirServico(s1,funcionarioAux.getCarteiraTrab());
									
									}else {
										
										
										// ========== funcionario inexistente  ==========
										
										System.out.println("FUNCIONARIO INEXISTENTE | 1 - CADASTRAR NOVO FUNCIONARIO | 2 -VOLTAR ");
										if(op == 1) {
											System.out.println("Digite a carteira de Trabalho");
											funcionarioAux.setCarteiraTrab(teclado.nextLine());
											System.out.println("Digite o Salario");
											funcionarioAux.setSalario(teclado.nextFloat());
											teclado.nextLine();
											System.out.println("Digite o Email");
											funcionarioAux.setEmail(teclado.nextLine());
											System.out.println("Digite Numero do Funcionario");
											funcionarioAux.setNumero(teclado.nextInt());
											funcionarioDAO.AdicionarFuncionario(funcionarioAux);
										}else {
											System.out.println("VOLTANDO AO MENU DE SERVIÇO");
											chance = 3;
											
										}
										
									}


									chance = 3;

								} else {
									chance++;
									i = 3 - chance;
									System.out.println("SERVIÇO JA CADASTRADO!!");
									System.out.println("\nVOCE TEM  : " + i + " tentativas!!");

								}
							} while (chance != 3);

							break;
						case 2:
							String op2;
							System.out.println("ID DO SERVIÇO QUE DESEJA REMOVER");
							teclado.nextLine();
							int idAUX = teclado.nextInt();// antes de exluir deve-se ver se ele não tem nem um
							// agendamento, ou seja buscamos o cliente verificamos se n
							// há agendamento e depois exluimos.
							System.out.println(
									"VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O SERVICO ?\nTODOS OS SEUS AGENDAMENTOS EM ABERTOS SERAM EXLUIDOS CONSEQUENTEMENTE!!\n 1-SIM, EU CONFIRMO|2-CANCELAR");
							op2 = teclado.nextLine();

							if (op2 == "1") {
								servicoDAO.excluirServico(idAUX);
							} else {
								System.out.println("CANCELANDO...");
							}

							break;

						case 3:

							Servico s1 = new Servico();
							i = 0;

							do {
								System.out.println("ID DO SERVIÇO QUE DESEJA ALTERAR");
								teclado.nextLine();
								idAUX = teclado.nextInt();
								s1.setId(idAUX);
								if (servicoDAO.buscarServico(idAUX) != null) {
									System.out.println("DIGITE  O ID");
									s1.setId(teclado.nextInt());
									System.out.println("DIGITE O TIPO");
									s1.setTipo(teclado.nextLine());

									i = 3;
								} else {
									System.out.println("ID nao encontrado");
									i++;
								}
							} while (i != 3);

							break;
						case 4:

							System.out.println("Todos os SERVIÇOS: ");

							RelatorioDeServicos = servicoDAO.servicoTotal();

							System.out.println("=========================================================");
							for (i = 0; i < RelatorioDeServicos.size(); i++) {
								System.out.println("ID do SERVIÇO" + i + " -> "+ RelatorioDeServicos.get(i).getId() + 
										"\nTIPO do SERVIÇO:" + i + "-> "+ RelatorioDeServicos.get(i).getTipo()+ 
										"\nFUNCIONARIO RESPONSAVEL:" + i + "-> "+ RelatorioDeServicos.get(i).getFuncionario().getCarteiraTrab());
								System.out.println("=========================================================");
							}

							break;
						case 5:
							int servico1;
							Servico s2 = new Servico();
							System.out.println("ID DO SERVICO QUE DESEJA BUSCAR");
							teclado.nextLine();
							servico1 = teclado.nextInt();
							s2.setId(servico1);
							servicoAux = servicoDAO.buscarServico(servico1);

							if(servicoDAO.buscarServico(servico1)!= null){
								System.out.println("=========================================================");
								System.out.println("\nID: " + servicoAux.getId());
								System.out.println("TIPO: " + servicoAux.getTipo());
								System.out.println("=========================================================");
							}else {
								System.out.println("Servico nao entrado");
							}





							break;
						case 6:
							i = 3;
							break;
						default:
							System.out.println("Opção invalida");
						}
					} while (op != 6);

				} while (i != 3);
				break;


			case 5:
				System.out.println("ENCERRANDO...");

			}
		} while (op != 5);
	}

}