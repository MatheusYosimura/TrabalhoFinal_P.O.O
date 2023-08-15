package pacote;

public class Vaga {
	public static int cont = 1;
	private int numero, id_veiculo=0;
	private Boolean ocupacao=false;
	
	Vaga(){
		setNumero(cont++);
	}
	Vaga(int num,int id_veiculo,Boolean oc){
		setNumero(num);
		setId_Veiculo(id_veiculo);
		this.ocupacao=oc;
	}
	public String toString() {
		String s = "+-----------\n"+
				   "| Vaga "+getNumero()+"\n"+
				   "+-----------\n"+
				   "|Ocupação: "+getOcupacao()+"\n"+
				   "|Veiculo: "+getId_Veiculo()+"\n"+
				   "+-----------";
		
		return s;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getId_Veiculo() {
		return id_veiculo;
	}
	public void setId_Veiculo(int id_veiculo) {//EXCESSÃO PARA CASO A VAGA JÁ ESTEJA PREENCHIDA
		if(ocupacao==false) {
			this.id_veiculo = id_veiculo;
			ocupacao=true;
		}else {
			if(id_veiculo==0) {
				this.id_veiculo=id_veiculo;
			}else{
				System.out.println("Esta vaga já está preenchida");
			}
		}
	}
	public void liberaVaga() {
		this.ocupacao=false;
	}
	public Boolean getOcupacao() {
		return ocupacao;
	}
}
