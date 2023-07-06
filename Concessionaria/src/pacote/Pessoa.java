package pacote;

public abstract class Pessoa {
	private String nome, endereco;
	private int CPF;
	
	public Pessoa(String nome, String endereco, int CPF){
		setNome(nome);
		setEndereco(endereco);
		setCPF(CPF);
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
	
	public int getCPF() {
		return CPF;
	}
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	
}
