package bancario.projeto.app;

import java.util.ArrayList;
import java.util.Scanner;

import bancario.projeto.model.Cliente;
import bancario.projeto.model.ContaBancaria;
import bancario.projeto.persistencia.PersistenciaCliente;

public class Programa {

	public static void main(String[] args) {
		
		PersistenciaCliente p = new PersistenciaCliente();
		
		Scanner sc = new Scanner(System.in);
		boolean sair = true;
		int o = 0;
		
		System.out.println("[Sistema Bancário]");
		while(sair) {
			System.out.println("\nDigite sua opção [Cliente]:\n"
				    + "\n1 - Cadastrar um novo cliente;\n"
				    + "2 - Remover seu cadastro;\n"
				    + "3 - Consultar cliente pelo CPF;\n"
				    + "4 - Listagem de clientes;\n"
				    +"\nDigite sua opção [Conta]:\n\n"
				    + "5 - Adicionar conta ao cliente;\n"
				    + "6 - Listar contas de um cliente;\n"
				    + "7 - Remover conta de um cliente;\n"
				    + "8 - Depositar em conta;\n"
				    + "9 - Sacar de conta;\n"
				    + "10 - Transferir entre contas;\n"
				    + "11 - Consultar saldo de conta;\n"
				    + "12 - Consultar balanço total do cliente;\n"
				    + "13 - Sair do sistema.\n");
			o = sc.nextInt();
			switch (o) {
			
			
			case 1: {
				String cpf;
				String nome;
				System.out.println("Coloque seu cpf: ");
				cpf = sc.next();
				System.out.println("Coloque seu nome: ");
				nome = sc.next();
				p.adicionarCliente(new Cliente(cpf,nome));
				break;
			}
			
			
			case 2: {
				Cliente temp;
				String cpf;
				System.out.println("Insira seu cpf: ");
				cpf = sc.next();
				temp = new Cliente();
				temp.setCpf(cpf);
				p.removerCliente(temp);
				break;
			}
			
			
			case 3: {
			    System.out.println("Digite o CPF do cliente que deseja consultar:");
			    String cpf = sc.next();
			    
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Cliente encontrado:");
			        System.out.println("CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 4: {
			    System.out.println("Clientes cadastrados:");
			    ArrayList<Cliente> listaClientes = p.listarClientes();
			    if (listaClientes.isEmpty()) {
			        System.out.println("Nenhum cliente cadastrado.");
			    } else {
			        for (Cliente cliente : listaClientes) {
			            System.out.println("CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
			        }
			    }
			    break;
			}
			
			
			case 5: {
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Digite o número da nova conta:");
			        int numeroConta = sc.nextInt();
			        ContaBancaria conta = new ContaBancaria(numeroConta);
			        cliente.adicionarConta(conta);
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 6: {
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Contas do cliente " + cliente.getNome() + ":");
			        for (ContaBancaria conta : cliente.getContas()) {
			            System.out.println(conta);
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 7: {
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Digite o número da conta para remover:");
			        int numeroConta = sc.nextInt();
			        ContaBancaria conta = cliente.localizarContaPorNumero(numeroConta);
			        if (conta != null) {
			            cliente.removerConta(conta);
			        } else {
			            System.out.println("Conta não encontrada.");
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 8: {
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Digite o número da conta para depósito:");
			        int numeroConta = sc.nextInt();
			        ContaBancaria conta = cliente.localizarContaPorNumero(numeroConta);
			        if (conta != null) {
			            System.out.println("Digite o valor a ser depositado:");
			            float quantia = sc.nextFloat();
			            conta.depositar(quantia);
			        } else {
			            System.out.println("Conta não encontrada.");
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 9: {
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Digite o número da conta para saque:");
			        int numeroConta = sc.nextInt();
			        ContaBancaria conta = cliente.localizarContaPorNumero(numeroConta);
			        if (conta != null) {
			            System.out.println("Digite o valor a ser sacado:");
			            float quantia = sc.nextFloat();
			            conta.sacar(quantia);
			        } else {
			            System.out.println("Conta não encontrada.");
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 10: { 
			    System.out.println("Digite o CPF do cliente de origem:");
			    String cpfOrigem = sc.next();
			    Cliente clienteOrigem = p.localizarClientePorCpf(cpfOrigem);
			    if (clienteOrigem != null) {
			        System.out.println("Digite o número da conta de origem:");
			        int numeroContaOrigem = sc.nextInt();
			        ContaBancaria contaOrigem = clienteOrigem.localizarContaPorNumero(numeroContaOrigem);
			        if (contaOrigem != null) {
			            System.out.println("Digite o CPF do cliente de destino:");
			            String cpfDestino = sc.next();
			            Cliente clienteDestino = p.localizarClientePorCpf(cpfDestino);
			            if (clienteDestino != null) {
			                System.out.println("Digite o número da conta de destino:");
			                int numeroContaDestino = sc.nextInt();
			                ContaBancaria contaDestino = clienteDestino.localizarContaPorNumero(numeroContaDestino);
			                if (contaDestino != null) {
			                    System.out.println("Digite o valor a ser transferido:");
			                    float quantia = sc.nextFloat();
			                    contaOrigem.transferir(contaDestino, quantia);
			                } else {
			                    System.out.println("Conta de destino não encontrada.");
			                }
			            } else {
			                System.out.println("Cliente de destino não encontrado.");
			            }
			        } else {
			            System.out.println("Conta de origem não encontrada.");
			        }
			    } else {
			        System.out.println("Cliente de origem não encontrado.");
			    }
			    break;
			}
			
			
			case 11: { 
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        System.out.println("Digite o número da conta para consultar o saldo:");
			        int numeroConta = sc.nextInt();
			        ContaBancaria conta = cliente.localizarContaPorNumero(numeroConta);
			        if (conta != null) {
			            System.out.println("Saldo da conta: " + conta.getSaldo());
			        } else {
			            System.out.println("Conta não encontrada.");
			        }
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			
			case 12: { 
			    System.out.println("Digite o CPF do cliente:");
			    String cpf = sc.next();
			    Cliente cliente = p.localizarClientePorCpf(cpf);
			    if (cliente != null) {
			        float balancoTotal = 0;
			        for (ContaBancaria conta : cliente.getContas()) {
			            balancoTotal += conta.getSaldo();
			        }
			        System.out.println("Balanço total das contas do cliente: " + balancoTotal);
			    } else {
			        System.out.println("Cliente não encontrado.");
			    }
			    break;
			}
			
			case 13: {
                sair = false;
                System.out.println("Você saiu do sistema.");
                p.salvarClientes();
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + o);
				}
			}
			sc.close();
	}
}
