package pacote;

public class Cliente extends Pessoa{
	public static int cont=1;
	private int cadastro;
	private String pagamento;
	
	Cliente(String nome, String endereco, int CPF, String pagamento, int id){
		super(nome, endereco, CPF);
		setPagamento(pagamento);
		if(id!=0){cont=id;}
		setCadastro(cont++);
	}
	
	public String toString() {
		String s = "+-----------\n"+
				   "| Cliente "+getCadastro()+"\n"+
				   "+-----------\n"+
				   "|Nome: "+getNome()+"\n"+
				   "|CPF: "+getCPF()+"\n"+
				   "|Endereço: "+getEndereco()+"\n"+
				   "|Pagamento: "+getPagamento()+"\n"+
				   "+-----------";
		return s;
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
