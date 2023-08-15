package pacote;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Painel {
	static int aux_id_garagem=0;
	public static int painelInicial () {
		String[] s = {"Carregar dados da Empresa","Declarar nova Empresa"};
		return JOptionPane.showOptionDialog(null, "Bem Vindo!\n Você deseja:","Concessionária", 0, JOptionPane.QUESTION_MESSAGE
				, null, s, s[1]);
	}
	public static void empresaCarregamentoSucesso() {
		JOptionPane.showMessageDialog(null,"Dados da empressa carregados com sucesso.","Concessionária", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void empresaCarregamentoFalha() {
		JOptionPane.showMessageDialog(null,"Dados da empresa não foram encontrados.\nVerifique o Backup ou declare uma nova"
				+ " empresa.","Concessionária", JOptionPane.WARNING_MESSAGE);
	}
//	----------------------------------	
	public static int menu() {
		String[] s = {"Empresa","Veículos","Garagem","Contratos","Funcionários","Clientes","Salvar","Sair"};
		return JOptionPane.showOptionDialog(null, "MENU","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[7]);
	}
//	----------------------------------
//	MÉTODOS TESTADOS //
	public static int empresaMenu() {
		String[] s = {"Mostrar Dados","Alterar Dados","Voltar"};
		return JOptionPane.showOptionDialog(null, "Empresa","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[2]);
	}
	public static Empresa criaEmpresa() throws IOException {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome da empresa:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço da empresa:");
		String razaoSocial = JOptionPane.showInputDialog(null,"Insira a razão social da empresa:");
		int cnpj = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o CNPJ da empresa:"));
		Empresa emp = new Empresa(nome,endereco,razaoSocial,cnpj,0);
		return emp;
	}
	public static void mostraDadosEmpresa(Empresa emp) {
		String s = emp.toString();
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.INFORMATION_MESSAGE);
	}
	public static Empresa alteraDadosEmpresa(Empresa emp) { // A EMPRESA RETORNADA DEVE SER SALVADA.=> empresa = return emp
		String s = "AVISO\nO CNPJ e Razão Social não são alteráveis!\nCaso não queira alterar o dado, digite -1";
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome","Concessionária",JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals("-1"))){
			emp.setNome(aux);
		}
		aux =  JOptionPane.showInputDialog(null,"Insira o novo endereço","Concessionária",JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals("-1"))){
			emp.setEndereco(aux);
		}
		return emp;
	}
	
// -----------------------------------
//	MÉTODOS TESTADOS //
	public static int menuVeiculo() {
		String[] s = {"Mostrar Dados","Adicionar Veiculo","Remover Veiculo","Voltar"};
		return JOptionPane.showOptionDialog(null, "Veiculos","Concessionária", 0, JOptionPane.PLAIN_MESSAGE, null, s, s[3]);
	}
	public static void mostraDadosVeiculo(Empresa emp) {
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todos os Veiculos?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		System.out.println(i);
		if(i==0) {
			int aux = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o ID do veículo","Concessionária"));
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
	}
	public static Empresa adicionaVeiculo(Empresa emp) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome do Veiculo: ","Concessionária");
		String modelo = JOptionPane.showInputDialog(null,"Insira o modelo do Veículo:","Concessionária");
		int ano = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o ano do Veículo:","Concessionária"));
		emp.adicionaVeiculo(nome,modelo,ano,-1);
		return emp;
	}
	public static Empresa removeVeiculo(Empresa emp) {
		int aux=Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o ID(PLACA POSTERIORMENTE) do Veículo:"));
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
		return emp;
	}
	
// -----------------------------------
//	MÉTODOS TESTADOS //
	public static int inicialGaragemMenu() {
		String[] s = {"Declarar nova garagem","Escolher uma Garagem", "Voltar"};
		return JOptionPane.showOptionDialog(null, "Garagem\nVocê deseja:","Concessionária", 0, JOptionPane.QUESTION_MESSAGE
				, null, s, s[2]);
	}
	public static int escolheGaragem() {
		aux_id_garagem=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o ID da garagem:","Concessionária",JOptionPane.INFORMATION_MESSAGE));
		return aux_id_garagem;
	}
	public static ArrayList<Garagem> criaGaragem(ArrayList<Garagem> garagem) {
		String nome = JOptionPane.showInputDialog(null,"Insira o nome da garagem:");
		String endereco = JOptionPane.showInputDialog(null,"Insira o endereço da garagem:");
		int numVagas = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o número de vagas"));
		Garagem aux_gar = new Garagem(nome,endereco,numVagas,0);
		garagem.add(aux_gar);
		aux_id_garagem = aux_gar.getIdGaragem();
		return garagem;//Atualizar empresa com garagem retornada;
		
	}
	public static void garagemCarregamentoFalha() {//ALTERAR METODO PARA ACEITAR OPÇÃO "INFORME NOVA GARAGEM"
		JOptionPane.showMessageDialog(null,"Dados da garagem não foram encontrados.\nCrie ou informe uma nova garagem","Concessionária"
				, JOptionPane.WARNING_MESSAGE);
	}
	public static int garagemMenu() {
		String[] s = {"Mostrar Dados","Mostrar Vagas","Alterar Dados","Voltar"};
		return JOptionPane.showOptionDialog(null, "Garagem"+"[ID_GARAGEM]","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[3]);
	}
	public static void mostraDadosGaragem(Empresa emp) {
		Garagem aux_gar = emp.retornaGaragem(aux_id_garagem);
		JOptionPane.showMessageDialog(null, aux_gar.toString(),"Consessionária",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void mostraDadosVaga(Empresa emp) {
		Garagem aux_gar = emp.retornaGaragem(aux_id_garagem);
		String[] option = {"Inserir ID","Mostrar Todos"};
		String s = "Você deseja inserir um ID ou mostrar todas as vagas?";
		int i = JOptionPane.showOptionDialog(null,s,"Concessionaria", 0, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		System.out.println(i);
		if(i==0) {
			int aux = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o ID da vaga","Concessionária"));
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
	}
	public static ArrayList<Garagem> alteraDadosGaragem(ArrayList<Garagem> garagem){
		Garagem aux_gar = Garagem.retornaGaragem(aux_id_garagem,garagem);
		String s ="AVISO\nO ID da garagem, empresa associada e número de vagas não são alteráveis\nCaso não queira alterar um dado, insira -1";
		JOptionPane.showMessageDialog(null,s,"Concessionária",JOptionPane.WARNING_MESSAGE);
		String aux = JOptionPane.showInputDialog(null,"Insira o novo nome:",null,JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals("-1"))){
			aux_gar.setNome(aux);
		}
		aux =  JOptionPane.showInputDialog(null,"Insira o novo endereço",null,JOptionPane.PLAIN_MESSAGE);
		if(!(aux.equals("-1"))){
			aux_gar.setEndereco(aux);
		}
		Garagem.atualizaDadosGaragem(aux_gar, aux_id_garagem, garagem);
		return garagem;
	}
// -----------------------------------
	public static int contratosMenu() {
		String[] s = {"Mostrar Contrato","Criar Contrato","Encerrar Contrato","Pagar Parcela","Voltar"};
		return JOptionPane.showOptionDialog(null, "Contratos","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[4]);
	}

	public static int funcionariosMenu() {
		String[] s = {"Mostrar Dados do Funcionário","Adicionar Funcionario","Remover Funcionario","Voltar"};
		return JOptionPane.showOptionDialog(null, "Funcionários","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[3]);
	}
	
	public static int clientesMenu() {
		String[] s = {"Mostrar Dados do Cliente","Adicionar Cliente","Remover Cliente","Voltar"};
		return JOptionPane.showOptionDialog(null, "Clientes","Concessionária", 0, JOptionPane.PLAIN_MESSAGE
				, null, s, s[3]);
	}


}

