package pacote;

public class Vaga {
	public static int cont = 1;
	private int numero;
	private Boolean ocupacao=false;
	private Veiculo veiculo;
	Vaga(){
		setNumero(cont++);
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
