package pacote;

public class Funcionario extends Pessoa{
	public static int cont=1;
	private int idCadastro;
	private String funcao;
	
	Funcionario(String nome, String endereco, int CPF, String funcao){
		super(nome, endereco, CPF);
		setFuncao(funcao);
		setIdCadastro(cont++);
	}
	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public int getIdCadastro() {
		return idCadastro;
	}
	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}
}
