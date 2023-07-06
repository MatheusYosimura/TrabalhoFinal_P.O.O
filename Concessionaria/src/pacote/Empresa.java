package pacote;
import java.util.ArrayList;

public class Empresa {
	public static Boolean exist=false;
	private int CNPJ;
	private String nome, endereco, razaoSocial;
	private ArrayList<Funcionario>  funcionarios = new ArrayList();
	private ArrayList<Veiculo> veiculos = new ArrayList();
	private ArrayList<Contrato> contratos = new ArrayList();
	
	Empresa(String nome, String endereco, String razaoSocial, int CNPJ){
		setNome(nome);
		setEndereco(endereco);
		setRazaoSocial(razaoSocial);
		setCNPJ(CNPJ);
		exist=true;
	}
	
	public void adicionaVeiculo(String marca, String modelo, int ano) {
		veiculos.add(new Veiculo(marca, modelo, ano));
	}
	public Veiculo retornaVeiculo(int idVeiculo) {
		Veiculo comparacao;
		for(int i=0; i<veiculos.size(); i ++) {
			comparacao=veiculos.get(i);
			if(comparacao.getIdCadastro()==idVeiculo) {
				return comparacao;
			}
		}
		System.out.println("Veículo não encontrado");
		return null;
	}
	
	public void adicionaFuncionario(String nome, String endereco, int CPF, String funcao) {
		funcionarios.add(new Funcionario(nome, endereco, CPF, funcao));
	}
	public Funcionario retornaFuncionario(int idFuncionario) {
		Funcionario comparacao;
		for(int i=0; i<funcionarios.size();i++){
			comparacao = funcionarios.get(i);
			if(comparacao.getIdCadastro()==idFuncionario) {
				return comparacao;
			}
		}
		System.out.println("Funcionario não encontrado");
		return null;
	}
	
	public void adicionaContrato(int idVeiculo, int idCliente, int parcelas, double valor){
		contratos.add(new Contrato(idVeiculo, idCliente, parcelas, valor));
	}
	public Contrato retornaContrato(int idContrato){
		Contrato comparacao;
		for (int i=0; i<contratos.size(); i++) {
			comparacao=contratos.get(i);
			if(comparacao.getIdContrato()==idContrato) {
				return comparacao;
			}
		}
		System.out.println("Contrato não encontrado");
		return null;
	}
	
	
	public int getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(int cNPJ) {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
}
