package pacote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static void main ( String[] args) throws IOException {
		ArrayList<Garagem> garagem = null;
		Empresa empresa = null;
		ArrayList<Cliente> clientes = new ArrayList();
		String nome, endereco, razaoSocial;
		int CNPJ, menu=0;
		Scanner sc = new Scanner(System.in);
		//empresa=Painel.criaEmpresa();
		empresa=Empresa.carregaDadosEmpresa();
		/*Painel.painelInicial();
		Painel.empresaCarregamentoFalha();
		Painel.empresaCarregamentoSucesso();
		Painel.menu();
		Painel.empresaMenu();
		Painel.veiculoMenu();
		Painel.inicialGaragemMenu();
		Painel.criaGaragem();
		Painel.inicialGaragemMenu();
		Painel.garagemMenu();
		Painel.contratosMenu();
		Painel.funcionariosMenu();
		Painel.clientesMenu();*/
		while(menu!=5 && Empresa.exist==true) {
			  System.out.println
			   ("+-------------------------\n"
			  + "|Selecione uma opção\n"
			  +	"|1 - Criar Veículo\n"
			  + "|2 - Criar Funcionário\n"
			  + "|3 - Criar Cliente\n"
			  + "|4 - Criar Contrato\n"
			  + "|5 - Sair\n"
			  + "+-------------------------");
			  menu=sc.nextInt();
			  sc.nextLine();
			  switch(menu) {
			  	case 1:
			  		String marca, modelo; 
			  		int ano,idV;
			  		System.out.println("Insica a marca, modelo e ano do veículo");
			  		marca=sc.nextLine();
			  		modelo=sc.nextLine();
			  		ano=sc.nextInt();
			  		sc.nextLine();
			  		idV=empresa.adicionaVeiculo(marca, modelo, ano,-1);
			  		System.out.println("Dados do veículo adicionado"
			  				+ "\nMarca: "+marca
			  				+ "\nModelo: "+modelo
			  				+ "\nAno: "+ano
			  				+ "\nID: "+idV);
			  		break;
			  	case 2:
			  		String name, end, funcao; 
			  		int CPF,idF;
			  		System.out.println("Insira o nome, endereço, função e CPF do funcionário");
			  		name=sc.nextLine();
			  		end=sc.nextLine();
			  		funcao=sc.nextLine();
			  		CPF=sc.nextInt();
			  		sc.nextLine();
			  		idF=empresa.adicionaFuncionario(name, end, CPF, funcao,-1);
			  		System.out.println("Dados do funcionário adicionado"
			  				+ "\nNome: "+name
			  				+ "\nEndereço: "+end
			  				+ "\nFunção: "+funcao
			  				+ "\nCPF: "+CPF
			  				+ "\nID: "+idF);
			  		break;
			  	case 3://Adicionar Cliente
			  		String nameCliente, endCliente, pagamento;
			  		int CPFcliente;
			  		Cliente cliente;
			  		System.out.println("Insira o nome, endereço, método de pagamento e CPF do cliente");
			  		nameCliente=sc.nextLine();
			  		endCliente=sc.nextLine();
			  		pagamento=sc.nextLine();
			  		CPFcliente=sc.nextInt();
			  		sc.nextLine();
			  		cliente=new Cliente(nameCliente,endCliente,CPFcliente,pagamento,0);
			  		System.out.println("Dados do cliente adicionado"
			  				+ "\nNome:"+nameCliente
			  				+ "\nEndereço:"+endCliente
			  				+ "\nCPF:"+CPFcliente
			  				+ "\nPagamento:"+pagamento
			  				+ "\nId Cliente:"+cliente.getCadastro());
			  		break;
			  	case 4://Adiciona Contrato
			  		/*int idVeiculo, idCliente, qtdParcelas,idContrato; 
			  		double valor;
			  		System.out.println("Insira o idVeículo, idCliente, quantidade de parcelas e valor");
			  		idVeiculo=sc.nextInt();
			  		idCliente=sc.nextInt();
			  		qtdParcelas=sc.nextInt();
			  		valor=sc.nextDouble();
			  		idContrato=empresa.adicionaContrato(idVeiculo, idCliente, qtdParcelas, valor,-1);
			  		System.out.println("Dados do Contrato adicionado"
			  				+ "\nID do Veículo:"+idVeiculo
			  				+ "\nID do Cliente:"+idCliente
			  				+ "\nQuantidade de Parcelas:"+qtdParcelas
			  				+ "\nValor:"+valor
			  				+ "\nID do contrato:"+idContrato);*/
			  		break;
			  	case 5://Sair
			  		break;
			  	case 6://CARREGA DADOS DA EMPRESA
			  		//empresa=Empresa.carregaDadosEmpresa();
			  		garagem=Garagem.carregaDadosGaragem();
			  		//ASSOCIA AS GARAGENS À EMPRESA
			  		for(int i=0; i<garagem.size();i++) {
						Garagem g = garagem.get(i);
						empresa.associaGaragem(g,empresa);
						garagem.set(i, g);
						}
			  		System.out.println("Numero de veiculos = "+empresa.retornaNumeroVeiculos());
			  		System.out.println("Numero de vagas garagem 1 = "+garagem.get(0).numeroDeVagas());
			  		System.out.println("Numero de vagas garagem 2 = "+garagem.get(1).numeroDeVagas());
			  		//FUNÇÃO PARA ASSOCIAR OS VEICULOS ( SALVOS SEM VAGA E GARAGEM ) À VAGA E GARAGEM
			  		for(int i=0; i<garagem.size();i++) {
			  			Garagem aux_garagem = garagem.get(i);
			  			for(int j=0;j<aux_garagem.numeroDeVagas();j++) {
			  				Vaga aux_vaga = aux_garagem.getVagas(j);
			  				if(aux_vaga.getOcupacao()==true) {
			  					//Veiculo aux_veiculo = empresa.retornaVeiculo(aux_vaga.getId_Veiculo());
			  					aux_garagem.preencheVaga(aux_vaga.getNumero(), aux_vaga.getId_Veiculo(),0);
			  					garagem.set(i, aux_garagem);
			  					Veiculo veic = empresa.retornaVeiculo(aux_vaga.getId_Veiculo());
			  					empresa.atualizaDadosVeiculo(veic, veic.getIdCadastro());
			  					System.out.println("Veiculo "+veic.getIdCadastro()+" Vaga : "+veic.getId_vaga()+" Garagem : "+veic.getId_garagem());
			  				}
			  			}
			  		}
			  		empresa.carregaDadosContratos();
			  		//Criar contrato
			  		/*int idVeiculo, idCliente, qtdParcelas,idContrato; 
			  		double valor;
			  		System.out.println("Insira o idVeículo, idCliente, quantidade de parcelas e valor");
			  		idVeiculo=sc.nextInt();
			  		idCliente=sc.nextInt();
			  		qtdParcelas=sc.nextInt();
			  		valor=sc.nextDouble();
			  		idContrato=empresa.adicionaContrato(idVeiculo, idCliente, qtdParcelas, qtdParcelas, valor, true,0);
			  		System.out.println("Dados do Contrato adicionado"
			  				+ "\nID do Veículo:"+idVeiculo
			  				+ "\nID do Cliente:"+idCliente
			  				+ "\nQuantidade de Parcelas:"+qtdParcelas
			  				+ "\nValor:"+valor	
			  				+ "\nID do contrato:"+idContrato);
			  		Paga Parcelas
			  		empresa.pagaParcelaContrato(22223, 1, 10.00);
			  		empresa.pagaParcelaContrato(22223, 2, 10.00);
			  		Finaliza Contrato
			  		empresa.encerraContrato(22223);	  		
			  		empresa=Painel.adicionaVeiculo(empresa);
			  		Painel.mostraDadosVeiculo(empresa);
			  		empresa=Painel.removeVeiculo(empresa);
			  		Painel.empresaMenu();
			  		Painel.mostraDadosEmpresa(empresa);
			  		empresa=Painel.alteraDadosEmpresa(empresa);
			  		Painel.escolheGaragem();
			  		garagem=Painel.criaGaragem(garagem);
			  		empresa.atualizaTotalGaragem(garagem);
			  		Painel.mostraDadosGaragem(empresa);
			  		Painel.mostraDadosVaga(empresa);
			  		garagem=Painel.alteraDadosGaragem(garagem);
			  		empresa.atualizaTotalGaragem(garagem);*/
			  		empresa.salvaDadosEmpresa();
			  		/*for(int i=0; i<garagem.size();i++) {//SALVA DADOS DAS GARAGEMS
						Garagem g = garagem.get(i);
						g.salvaDadosGaragem();
						}
						  		*/
			  		System.exit(0);
			  		break;
			  	default:
			  		System.out.println("Opção Invalida. Insira uma opção válida");
			  		break;
			  		
			  }
			  
		}
		
	}
}
