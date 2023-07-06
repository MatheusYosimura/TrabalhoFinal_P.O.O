package pacote;

public class Cliente extends Pessoa{
	public static int cont=1;
	private int cadastro;
	private String pagamento;
	
	Cliente(String nome, String endereco, int CPF, String pagamento){
		super(nome, endereco, CPF);
		setPagamento(pagamento);
		setCadastro(cont++);
	}
	
	public int getCadastro() {
		return cadastro;
	}
 	public void setCadastro(int cadastro) {
		this.cadastro = cadastro;
	}
	
 	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
}
