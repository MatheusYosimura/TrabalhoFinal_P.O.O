package pacote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static int nav=0;//Variável para navegar pelos paineis
	public static void main ( String[] args) throws IOException {
		ArrayList<Garagem> garagem = null;
		Empresa empresa = null;
		while(true) {
			switch (nav) {
				case 0 ://Painel Incial 
					Painel.painelInicial();
					break;
				case 1 ://Menu Principal
					Painel.menu();
					break;
				case 2 ://Carrega Dados Empresa
					empresa=Empresa.carregaDadosEmpresa();
					if(empresa==null) {
						Principal.nav=0;
						break;
					}
					garagem=Garagem.carregaDadosGaragem();
					if(garagem==null) {
						Principal.nav=0;
						break;
					}
					//ASSOCIA AS GARAGENS À EMPRESA
			  		for(int i=0; i<garagem.size();i++) {
						Garagem g = garagem.get(i);
						empresa.associaGaragem(g,empresa);
						garagem.set(i, g);
			  			}
			  		//FUNÇÃO PARA ASSOCIAR OS VEICULOS ( SALVOS SEM VAGA E GARAGEM ) À VAGA E GARAGEM
			  		for(int i=0; i<garagem.size();i++) {
			  			Garagem aux_garagem = garagem.get(i);
			  			for(int j=0;j<aux_garagem.numeroDeVagas();j++) {
			  				Vaga aux_vaga = aux_garagem.getVagas(j);
			  				if(aux_vaga.getOcupacao()==true) {
			  					aux_garagem.preencheVaga(aux_vaga.getNumero(), aux_vaga.getId_Veiculo(),0);
			  					garagem.set(i, aux_garagem);
			  					Veiculo veic = empresa.retornaVeiculo(aux_vaga.getId_Veiculo());
			  					empresa.atualizaDadosVeiculo(veic, veic.getIdCadastro());
			  					System.out.println("Veiculo "+veic.getIdCadastro()+" Vaga : "+veic.getId_vaga()+" Garagem : "+veic.getId_garagem());
			  				}
			  			}
			  		}
			  		empresa.carregaDadosContratos();
			  		nav=1;
					break;
				case 10 : 
					Painel.menuEmpresa();
					break;
				case 11 : 
					empresa=Painel.criaEmpresa();
					break;
				case 12 : 
					Painel.mostraDadosEmpresa(empresa);
					break;
				case 13 : 
					empresa = Painel.alteraDadosEmpresa(empresa);
					break;
				case 20 : 
					Painel.menuVeiculo();
					break;
				case 21 : 
					Painel.mostraDadosVeiculo(empresa);
					break;
				case 22 : 
					empresa = Painel.adicionaVeiculo(empresa);
					break;
				case 23 : 
					empresa = Painel.removeVeiculo(empresa);
					break;
				case 30 : 
					Painel.menuInicialGaragem();
					break;
				case 31 : 
					Painel.escolheGaragem(empresa);
					break;
				case 32 : 
					garagem = Painel.criaGaragem(garagem,empresa);
					empresa.atualizaTotalGaragem(garagem);
					break;
				case 34 : 
					Painel.garagemMenu(empresa);
					break;
				case 35 :
					Painel.mostraDadosGaragem(empresa);
					break;
				case 36 : 
					Painel.mostraDadosVaga(empresa);
					break;
				case 37 : 
					garagem = Painel.alteraDadosGaragem(garagem);
					empresa.atualizaTotalGaragem(garagem);
					break;
				case 40 : 
					Painel.menuContratos();
					break;
				case 41 : 
					Painel.mostraDadosContrato(empresa);
					break;
				case 42 : 
					empresa = Painel.criaContrato(empresa);
					break;
				case 43 : 
					empresa = Painel.pagarParcela(empresa);
					break;
				case 44 : 
					empresa = Painel.encerraContrato(empresa);
					break;
				case 50 : 
					Painel.menuFuncionario();
					break;
				case 51 : 
					Painel.mostraDadosFuncionario(empresa);
					break;
				case 52 : 
					empresa = Painel.adicionaFuncionario(empresa);
					break;
				case 53 : 
					empresa = Painel.removeFuncionario(empresa);
					break;
				case 54 : 
					empresa = Painel.alteraDadosFuncionario(empresa);
					break;
				case 60 : 
					Painel.menuCliente();
					break;
				case 61 : 
					Painel.mostraDadosCliente(empresa);
					break;
				case 62 : 
					empresa = Painel.adicionaCliente(empresa);
					break;
				case 63 : 
					empresa = Painel.removeCliente(empresa);
					break;
				case 64 : 
					empresa = Painel.alteraDadosCliente(empresa);
					break;
				case 70 : 
					empresa.salvaDadosEmpresa();
					for(int i=0; i<garagem.size();i++) {//SALVA DADOS DAS GARAGEMS
						Garagem g = garagem.get(i);
						g.salvaDadosGaragem();
						}
					System.exit(0);
					break;
				default :
					break;
			}
		}  
		}
		
	}
	

