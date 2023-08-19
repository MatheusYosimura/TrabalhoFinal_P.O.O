package pacote;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Painel {
	static int aux_id_garagem=0;
	//0
	public static void painelInicial () {
		String[] s = {"Carregar dados da Empresa","Declarar nova Empresa","Sair"};
		int aux = JOptionPane.showOptionDialog(null, "Bem Vindo!\n Você deseja:","Concessionária", 0, JOptionPane.QUESTION_MESSAGE
				, null, s, s[1]);
		switch (aux) {
			case 0 : Principal.nav=2;//CARREGAR DADOS DA EMPRESA
				break;
			case 1 : Principal.nav=11;
				break;
			case 2 : System.exit(0);
				break;
		}
	}
//	----------------------------------	
	//1
	public static void menu() {
		String[] s = {"Empresa","Veículos","Garagem","Contratos","Funcionários","Clientes","Salvar","Sair"};
		int aux =  JOptionPane.showOptionDialog(null, "MENU","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[7]);
		switch (aux) {
			case 0 : Principal.nav=10;
				break;
			case 1 : Principal.nav=20;
				break;
			case 2 : Principal.nav=30;
				break;
			case 3 : Principal.nav=40;
				break;
			case 4 : Principal.nav=50;
				break;
			case 5 : Principal.nav=60;
				break;
			case 6 : Principal.nav=70;//SALVAR DADOS  DA EMPRESA E DA GARAGEM
				break;
			case 7 : System.exit(0);
				break;
		}
	}
//	----------------------------------
//	MÉTODOS TESTADOS //
	//10
	public static void menuEmpresa() {
		String[] s = {"Mostrar Dados","Alterar Dados","Voltar"};
		int aux=JOptionPane.showOptionDialog(null, "Empresa","Concessionária", 0, JOptionPane.PLAIN_MESSAGE, null, s, s[2]);
		switch (aux) {
			case 0 : Principal.nav=12;
				break;
			case 1 : Principal.nav=13;
				break;
			case 2 : Principal.nav=1;
				break;
		}
	}
	//11
	public static Empresa criaEmpresa() throws IOException {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome da empresa:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço da empresa:");
		String razaoSocial = JOptionPane.showInputDialog(null,"Insira a razão social da empresa:");
		String cnpj = JOptionPane.showInputDialog(null,"Insira o CNPJ da empresa:");
		if(nome.equals("")||endereco.equals("")||razaoSocial.equals("")||cnpj.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos, tente novamente");
			Principal.nav=0;
			return null;
		}
		Empresa emp = new Empresa(nome,endereco,razaoSocial,Integer.parseInt(cnpj),0);
		Principal.nav=1;
		return emp;
	}
	//12
	public static void mostraDadosEmpresa(Empresa emp) {
		String s = emp.toString();
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.INFORMATION_MESSAGE);
		Principal.nav=10;
	}
	//13
	public static Empresa alteraDadosEmpresa(Empresa emp) { 
		String s = "AVISO\nO CNPJ e Razão Social não são alteráveis!";
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome","",JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals(""))){
			emp.setNome(aux);
		}
		aux =  JOptionPane.showInputDialog(null,"Insira o novo endereço","",JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals(""))){
			emp.setEndereco(aux);
		}
		Principal.nav=10;
		return emp;
	}
	
// -----------------------------------
//	MÉTODOS TESTADOS //
	//20
	public static void menuVeiculo() {
		String[] s = {"Mostrar Dados","Adicionar Veiculo","Remover Veiculo","Voltar"};
		int aux = JOptionPane.showOptionDialog(null, "Veiculos","Concessionária", 0, JOptionPane.PLAIN_MESSAGE, null, s, s[3]);
		switch (aux) {
			case 0 : Principal.nav=21;
				break;
			case 1 : Principal.nav=22;
				break;
			case 2 : Principal.nav=23;
				break;
			case 3 : Principal.nav=1;
				break;
		}
	}
	//21
	public static void mostraDadosVeiculo(Empresa emp) {
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todos os Veiculos?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		System.out.println(i);
		if(i==0) {
			String teste = JOptionPane.showInputDialog(null,"Insira o ID do veículo","");
			if(teste.equals("")) {
				new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
				Principal.nav=20;
				return ;
			}
			int aux = Integer.parseInt(teste);
			if(emp.retornaVeiculo(aux)==null) {
				new TratamentoExcecoes("Veiculo não encontrado\n Insira um ID válido");
				Principal.nav=20;
				return;
			}
			s = emp.dadosVeiculo(aux,-1);
			JOptionPane.showMessageDialog(null, s,"Concessionária",JOptionPane.INFORMATION_MESSAGE);
		}
		if(i==1) {
			int aux;
			for(int j=0;j<emp.retornaNumeroVeiculos()&&j>=0;) {
				s = emp.dadosVeiculo(-1, j);
				option = new String[] {"Voltar","Avançar","Sair"};
				aux=JOptionPane.showOptionDialog(null, s, "Concessionária", 0, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
				if(aux==0 && j>0) {j--;}
				if(aux==1) {j++;}
				if(aux==2) {j=(-1);}
			}
		}
		Principal.nav=20;
	}
	//22
	public static Empresa adicionaVeiculo(Empresa emp) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome do Veiculo: ","");
		String modelo = JOptionPane.showInputDialog(null,"Insira o modelo do Veículo:","");
		String ano =JOptionPane.showInputDialog(null,"Insira o ano do Veículo:","");
		if(nome.equals("")||nome==null||modelo==null||modelo.equals("")||ano==null||ano.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos, tente novamente");
			Principal.nav=20;
			return emp;
		}
		emp.adicionaVeiculo(nome,modelo,Integer.parseInt(ano),-1);
		Principal.nav=20;
		return emp;
	}
	//23
	public static Empresa removeVeiculo(Empresa emp) {
		String teste=JOptionPane.showInputDialog(null,"Insira o ID(PLACA POSTERIORMENTE) do Veículo:");
		if(teste.equals("")) {
			new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
			Principal.nav=20;
			return emp;
		}
		int aux=Integer.parseInt(teste);
		Veiculo veic=emp.retornaVeiculo(aux);
		if(veic==null) {
			JOptionPane.showMessageDialog(null, "Veículo não encontrado","Concessionária",JOptionPane.WARNING_MESSAGE);
		}else {
			if(veic.getId_vaga()==0) {
				JOptionPane.showMessageDialog(null, "Veículo em contrato ou não pertencente a nenhuma vaga","Concesionária",JOptionPane.WARNING_MESSAGE);
			}else {
				emp.removeVeiculo(aux);
				JOptionPane.showMessageDialog(null, "Veículo removido com Sucesso","Concesionária",JOptionPane.WARNING_MESSAGE);
			}
		}
		Principal.nav=20;
		return emp;
	}
	
// -----------------------------------
//	MÉTODOS TESTADOS //
	//30
	public static void menuInicialGaragem() {
		String[] s = {"Declarar nova garagem","Escolher uma Garagem", "Voltar"};
		int aux= JOptionPane.showOptionDialog(null, "Garagem\nVocê deseja:","Concessionária", 0, JOptionPane.QUESTION_MESSAGE
				, null, s, s[2]);
		switch (aux) {
			case 0 : Principal.nav=32;
				break;
			case 1 : Principal.nav=31;
				break;
			case 2 : Principal.nav=1;
				break;
		}
	}
	//31
	public static void escolheGaragem(Empresa emp) {
		String teste = JOptionPane.showInputDialog(null,"Digite o ID da garagem:","",JOptionPane.INFORMATION_MESSAGE);
		if(teste.equals("")) {
			new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
			Principal.nav=30;
			return ;
		}
		aux_id_garagem=Integer.parseInt(teste);
		if(emp.retornaGaragem(aux_id_garagem)==null) {
			new TratamentoExcecoes("Garagem não encontrada\n Insira um ID válido");
			Principal.nav=30;
			aux_id_garagem=0;
			return;
		}
		Principal.nav=34;
	}
	//32
	public static ArrayList<Garagem> criaGaragem(ArrayList<Garagem> garagem, Empresa empresa) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome da garagem:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço da garagem:");
		String numVagas = JOptionPane.showInputDialog(null,"Insira o número de vagas");
		if(nome.equals("")||endereco.equals("")||numVagas.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=30;
			return garagem;
		}
		Garagem aux_gar = new Garagem(nome,endereco,Integer.parseInt(numVagas),0);
		empresa.associaGaragem(aux_gar, empresa);
		garagem.add(aux_gar);
		aux_id_garagem = aux_gar.getIdGaragem();
		Principal.nav=34;
		return garagem;//Atualizar empresa com garagem retornada;
		
	}
	//34
	public static void garagemMenu(Empresa emp) {
		Garagem aux_gar = emp.retornaGaragem(aux_id_garagem);
		String[] s = {"Mostrar Dados","Mostrar Vagas","Alterar Dados","Voltar"};
		int aux=JOptionPane.showOptionDialog(null, "Garagem "+aux_gar.getIdGaragem(),"Concessionária", 0, JOptionPane.PLAIN_MESSAGE, null, s, s[3]);
		switch (aux) {
			case 0 : Principal.nav=35;
				break;
			case 1 : Principal.nav=36;
				break;
			case 2 : Principal.nav=37;
				break;
			case 3 : Principal.nav=1;
				break;
		}
	}
	//35
	public static void mostraDadosGaragem(Empresa emp) {
		Garagem aux_gar = emp.retornaGaragem(aux_id_garagem);
		JOptionPane.showMessageDialog(null, aux_gar.toString(),"Consessionária",JOptionPane.INFORMATION_MESSAGE);
		Principal.nav=34;
	}
	//36
	public static void mostraDadosVaga(Empresa emp) {
		Garagem aux_gar = emp.retornaGaragem(aux_id_garagem);
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todas as vagas?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		if(i==0) {
			String teste = JOptionPane.showInputDialog(null,"Insira o ID da vaga","");
			if(teste.equals("")) {
				new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
				Principal.nav=34;
				return ;
			}
			int aux = Integer.parseInt(teste);
			if(aux_gar.retornaVaga(aux)==null) {
				new TratamentoExcecoes("Vaga não encontrado\n Insira um ID válido");
				Principal.nav=34;
				return;
			}
			s = aux_gar.DadosVaga(aux, -1);
			JOptionPane.showMessageDialog(null, s,"Concessionária",JOptionPane.INFORMATION_MESSAGE);
		}
		if(i==1) {
			int aux;
			for(int j=0;j<aux_gar.numeroDeVagas()&&j>=0;) {
				s = aux_gar.DadosVaga(-1, j);
				option = new String[] {"Voltar","Avançar","Sair"};
				aux=JOptionPane.showOptionDialog(null, s, "Concessionária", 0, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
				if(aux==0 && j>0) {j--;}
				if(aux==1) {j++;}
				if(aux==2) {j=(-1);}
			}
		}
		Principal.nav=34;
	}
	//37
	public static ArrayList<Garagem> alteraDadosGaragem(ArrayList<Garagem> garagem){
		Garagem aux_gar = Garagem.retornaGaragem(aux_id_garagem,garagem);
		String s ="AVISO\nO ID da garagem, empresa associada e número de vagas não são alteráveis";
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome:","",JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals(""))){
			aux_gar.setNome(aux);
		}
		aux =  JOptionPane.showInputDialog(null,"Insira o novo endereço",null,JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals(""))){
			aux_gar.setEndereco(aux);
		}
		Garagem.atualizaDadosGaragem(aux_gar, aux_id_garagem, garagem);
		Principal.nav=34;
		return garagem;
	}
// -----------------------------------
//	MÉTODOS TESTADOS //
	//40
	public static void menuContratos() {
		String[] s = {"Mostrar Contrato","Criar Contrato","Encerrar Contrato","Pagar Parcela","Voltar"};
		int aux = JOptionPane.showOptionDialog(null, "Contratos","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[4]);
		switch (aux) {
			case 0 : Principal.nav=41;
				break;
			case 1 : Principal.nav=42;
				break;
			case 2 : Principal.nav=44;
				break;
			case 3 : Principal.nav=43;
				break;
			case 4 : Principal.nav=1;
				break;
		}
	}
	//41
	public static void mostraDadosContrato(Empresa emp) {
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todos os contratos?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		if(i==0) {
			String teste = JOptionPane.showInputDialog(null,"Insira o ID do contrato","");
			if(teste.equals("")) {
				new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
				Principal.nav=40;
				return ;
			}
			int aux = Integer.parseInt(teste);
			if(emp.retornaContrato(aux)==null) {
				new TratamentoExcecoes("Contrato não encontrado\n Insira um ID válido");
				Principal.nav=40;
				return;
			}
			s = emp.dadosContrato(aux, -1);
			JOptionPane.showMessageDialog(null, s,"Concessionária",JOptionPane.INFORMATION_MESSAGE);
		}
		if(i==1) {
			int aux;
			for(int j=0;j<emp.numeroDeContratos()&&j>=0;) {
				s = emp.dadosContrato(-1, j);
				option = new String[] {"Voltar","Avançar","Sair"};
				aux=JOptionPane.showOptionDialog(null, s, "Concessionária", 0, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
				if(aux==0 && j>0) {j--;}
				if(aux==1) {j++;}
				if(aux==2) {j=(-1);}
			}
		}
		Principal.nav=40;
	}
	//42
	public static Empresa criaContrato(Empresa emp) {
		String idVeiculo = JOptionPane.showInputDialog(null,"Insira o ID do veículo:");
		String idCliente = JOptionPane.showInputDialog(null,"Insira o ID do cliente:");
		String qtdParcelas = JOptionPane.showInputDialog(null,"Insira a quantidade de parcelas:");
		String valor = JOptionPane.showInputDialog(null,"Insira o valor de cada parcela:");
		if(idVeiculo.equals("")||idCliente.equals("")||qtdParcelas.equals("")||valor.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=40;
			return emp;
		}
		if(emp.retornaVeiculo(Integer.parseInt(idVeiculo))==null) {
			new TratamentoExcecoes("Veiculo não encontrado\n Insira um ID válido");
			Principal.nav=40;
			return emp;
			}
		if(emp.retornaCliente(Integer.parseInt(idCliente))==null) {
			new TratamentoExcecoes("Cliente não encontrado\n Insira um ID válido");
			Principal.nav=40;
			return emp;
			}
		emp.adicionaContrato(Integer.parseInt(idVeiculo), Integer.parseInt(idCliente), Integer.parseInt(qtdParcelas), 
				Integer.parseInt(qtdParcelas), Double.parseDouble(valor), true, 0);
		Principal.nav=40;
		return emp;
	}
	//43
	public static Empresa pagarParcela(Empresa emp) {
		String aux_id =JOptionPane.showInputDialog(null,"Insira o ID do contrato:");
		String aux_ind = JOptionPane.showInputDialog(null,"Insira a parcela a ser paga:");
		String aux_valor =JOptionPane.showInputDialog(null,"Insira o valor a ser pago:");
		if(aux_id.equals("")||aux_ind.equals("")||aux_valor.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=40;
			return emp;
		}
		if(emp.retornaContrato(Integer.parseInt(aux_id))==null) {
			new TratamentoExcecoes("Contrato não encontrado\n Insira um ID válido");
			Principal.nav=40;
			return emp;
		}
		emp.pagaParcelaContrato(Integer.parseInt(aux_id), Integer.parseInt(aux_ind), Double.parseDouble(aux_valor));
		Principal.nav=40;
		return emp;
	}
	//44
	public static Empresa encerraContrato(Empresa emp) {//GRANDES CHANCES DE NÃO FUNCIONAR
		String teste = JOptionPane.showInputDialog(null,"Insira o ID do contrato:");
		if(teste.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=40;
			return emp;
		}
		int aux_id = Integer.parseInt(teste);
		if(emp.retornaContrato(aux_id)==null) {
			new TratamentoExcecoes("Contrato não encontrado\n Insira um ID válido");
			Principal.nav=40;
			return emp;
		}
		emp.encerraContrato(aux_id);
		Principal.nav=40;
		return emp;
	}
	
// -----------------------------------
//	MÉTODOS TESTADOS //
	//50
	public static void menuFuncionario() {
		String[] s = {"Mostrar Dados do Funcionário","Adicionar Funcionario","Remover Funcionario","Alterar Funcionário","Voltar"};
		int aux = JOptionPane.showOptionDialog(null, "Funcionários","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[4]);
		switch (aux) {
			case 0 : Principal.nav=51;
				break;
			case 1 : Principal.nav=52;
				break;
			case 2 : Principal.nav=53;
				break;
			case 3 : Principal.nav=54;
				break;
			case 4 : Principal.nav=1;
				break;
		}
	}
	//51
	public static void mostraDadosFuncionario(Empresa emp) {
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todos os funcionários?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		if(i==0) {
			String teste = JOptionPane.showInputDialog(null,"Dados Funcionário\nInsira o ID do funcionário:","");
			if(teste.equals("")) {
				new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
				Principal.nav=50;
				return ;
			}
			int aux = Integer.parseInt(teste);
			if(emp.retornaFuncionario(aux)==null) {
				new TratamentoExcecoes("Funcionário não encontrado\n Insira um ID válido");
				Principal.nav=50;
				return;
			}
			s = emp.dadosFucionario(aux, -1);
			JOptionPane.showMessageDialog(null, s,"Dados Funcionário",JOptionPane.INFORMATION_MESSAGE);
		}
		if(i==1) {
			int aux;
			for(int j=0;j<emp.numeroDeFuncionarios()&&j>=0;) {
				s = emp.dadosFucionario(-1, j);
				option = new String[] {"Voltar","Avançar","Sair"};
				aux=JOptionPane.showOptionDialog(null, s, "Dados Funcionários", 0, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
				if(aux==0 && j>0) {j--;}
				if(aux==1) {j++;}
				if(aux==2) {j=(-1);}
			}
		}
		Principal.nav=50;
	}
	//52
	public static Empresa adicionaFuncionario(Empresa emp) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome do Funcionário:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço do Funcionário:");
		String cpf = JOptionPane.showInputDialog(null,"Insira o CPF do Funcionário:");
		String funcao = JOptionPane.showInputDialog(null,"Insira a função do Funcionário:");
		if(nome.equals("")||endereco.equals("")||cpf.equals("")||funcao.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=50;
			return emp;
		}
		emp.adicionaFuncionario(nome, endereco, Integer.parseInt(cpf), funcao, -1);
		Principal.nav=50;
		return emp;
	}
	//53
	public static Empresa removeFuncionario(Empresa emp) {
		String teste = JOptionPane.showInputDialog(null,"Remove Funcionário\nInsira o ID do funcionário:","");
		if(teste.equals("")) {
			new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
			Principal.nav=50;
			return emp;
		}
		int aux_ind = Integer.parseInt(teste);
		if(emp.retornaFuncionario(aux_ind)==null) {
			new TratamentoExcecoes("Funcionário não encontrado\n Insira um ID válido");
			Principal.nav=50;
			return emp;
		}
		emp.removeFuncionario(aux_ind);
		Principal.nav=50;
		return emp;
	}
	//54
	public static Empresa alteraDadosFuncionario(Empresa emp) {
		String teste = JOptionPane.showInputDialog(null,"Altera Dados Funcionário\nInsira o ID do funcionário:","");
		if(teste.equals("")) {
			new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
			Principal.nav=50;
			return emp;
		}
		int aux_id = Integer.parseInt(teste);
		if(emp.retornaFuncionario(aux_id)==null) {
			new TratamentoExcecoes("Funcionário não encontrado\n Insira um ID válido");
			Principal.nav=50;
			return emp;
		}
		Funcionario aux_func = emp.retornaFuncionario(aux_id);
		String s = "AVISO\n O CPF e ID de cadastro do Funcionário não podem ser alterados.";
		JOptionPane.showMessageDialog(null, s,"Alterar Dados Funcionário",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome do Funcionário:");
		if(!(aux.equals(""))){
			aux_func.setNome(aux);
		}
		aux = JOptionPane.showInputDialog(null,"Insira o novo endereço do Funcionário:");
		if(!(aux.equals(""))){
			aux_func.setEndereco(aux);
		}
		aux = JOptionPane.showInputDialog(null,"Insira a nova função do Funcionário:");
		if(!(aux.equals(""))){
			aux_func.setFuncao(aux);
		}
		emp.atualizaDadosFuncionario(aux_func, aux_id);
		Principal.nav=50;
		return emp;
	}
	
// -----------------------------------
// MÉTODOS TESTADOS //
	//60
	public static void menuCliente() {
		String[] s = {"Mostrar Dados do Cliente","Adicionar Cliente","Remover Cliente","Alterar Cliente","Voltar"};
		int aux = JOptionPane.showOptionDialog(null, "Clientes","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[3]);
		switch (aux) {
			case 0 : Principal.nav=61;
				break;
			case 1 : Principal.nav=62;
				break;
			case 2 : Principal.nav=63;
				break;
			case 3 : Principal.nav=64;
				break;
			case 4 : Principal.nav=1;
				break;
		}
	}
	//61
	public static void mostraDadosCliente(Empresa emp) {
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todos os clientes?";
		int i = JOptionPane.showOptionDialog(null,s,"Dados Clientes", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		if(i==0) {
			String teste = JOptionPane.showInputDialog(null,"Dados Cliente\nInsira o ID do cliente:","");
			if(teste.equals("")) {
				new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
				Principal.nav=60;
				return ;
			}
			int aux= Integer.parseInt(teste);
			if(emp.retornaCliente(aux)==null) {
				new TratamentoExcecoes("Cliente não encontrado\n Insira um ID válido");
				Principal.nav=60;
				return ;
			}
			s = emp.dadosCliente(aux, -1);
			JOptionPane.showMessageDialog(null, s,"Dados Cliente",JOptionPane.INFORMATION_MESSAGE);
		}
		if(i==1) {
			int aux;
			for(int j=0;j<emp.numeroDeClientes()&&j>=0;) {
				s = emp.dadosCliente(-1, j);
				option = new String[] {"Voltar","Avançar","Sair"};
				aux=JOptionPane.showOptionDialog(null, s, "Dados Clientes", 0, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
				if(aux==0 && j>0) {j--;}
				if(aux==1) {j++;}
				if(aux==2) {j=(-1);}
			}
		}
		Principal.nav=60;
	}
	//62
	public static Empresa adicionaCliente(Empresa emp) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome do Cliente:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço do Cliente:");
		String cpf = JOptionPane.showInputDialog(null,"Insira o CPF do Cliente:");
		String pagamento = JOptionPane.showInputDialog(null,"Insira a forma de pagamento do Cliente:");
		if(nome.equals("")||endereco.equals("")||cpf.equals("")||pagamento.equals("")) {
			new TratamentoExcecoes("Valores inseridos invalidos\nTente novamente");
			Principal.nav=60;
			return emp;
		}
		emp.adicionaCliente(nome, endereco, Integer.parseInt(cpf), pagamento, -1);
		Principal.nav=60;
		return emp;
	}
	//63
	public static Empresa removeCliente(Empresa emp) {
		String teste = JOptionPane.showInputDialog(null,"Remove Cliente\nInsira o ID do cliente:","");
		if(teste.equals("")) {
			new TratamentoExcecoes("Valor inserido invalido\nTente novamente");
			Principal.nav=60;
			return emp;
		}
		int aux_ind= Integer.parseInt(teste);
		if(emp.retornaCliente(aux_ind)==null) {
			new TratamentoExcecoes("Cliente não encontrado\n Insira um ID válido");
			Principal.nav=60;
			return emp;
		}
		emp.removeCliente(aux_ind);
		Principal.nav=60;
		return emp;
	}
	//64
	public static Empresa alteraDadosCliente(Empresa emp) {
		int aux_id = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o ID do Cliente:"));
		Cliente aux_cli = emp.retornaCliente(aux_id);
		String s = "AVISO\n O CPF e ID de cadastro do Cliente não podem ser alterados.";
		JOptionPane.showMessageDialog(null, s,"Alterar Dados Cliente",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome do Cliente:");
		if(!(aux.equals(""))){
			aux_cli.setNome(aux);
		}
		aux = JOptionPane.showInputDialog(null,"Insira o novo endereço do Cliente:");
		if(!(aux.equals(""))){
			aux_cli.setEndereco(aux);
		}
		aux = JOptionPane.showInputDialog(null,"Insira a nova forma de pagamento do Cliente:");
		if(!(aux.equals(""))){
			aux_cli.setPagamento(aux);
		}
		emp.atualizaDadosCliente(aux_cli, aux_id);
		Principal.nav=60;
		return emp;
	}
}