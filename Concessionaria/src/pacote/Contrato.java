package pacote;
import java.util.*;

public class Contrato {
	//TENHO QUE SALVAR O NUMERO DE PARCELAS QUE AINDA FALTAM SER PAGAS
	//TENHO QUE SALVAR A VALIDADE DO CONTRATO
	public static int cont=1;
	private int idCliente, idVeiculo, idContrato, parcelasRestantes;
	private double[] parcela_semanal;
	private double valor;
	private Boolean validade;
	Contrato (int idVeiculo, int idCliente, int qtdParcelas, int parcelasRestantes, double valor, Boolean validade, int id){
		setIdVeiculo(idVeiculo);
		setIdCliente(idCliente);
		parcela_semanal = new double[qtdParcelas];
		Arrays.fill(parcela_semanal,0);
		if(parcelasRestantes==qtdParcelas) {
			for(int i=0; i< qtdParcelas; i++){
				parcela_semanal[i]=valor;
			}
		}else {
			for(int i=(parcelasRestantes), j=(qtdParcelas-1); i>0 ; i--, j--){
				parcela_semanal[j]=valor;
			}
		}
		setParcelasRestantes(parcelasRestantes);
		setValor(valor);
		if(id==0) {
			setIdContrato(cont++);
		}else {
			cont=id;
			setIdContrato(cont++);
		}
		//Enviar true pra quando for criar o contrato
		setValidade(validade);
	}
	
	public String toString(int Carro, int CPF) {
		String s = "+-----------\n"+
				   "| Contrato n: "+getIdContrato()+"\n"+
				   "+-----------\n"+
				   "|Veiculo: *********"+"\n"+
				   "|CPF Cliente: "+CPF+"\n"+
				   "|Validade: "+getValidade()+"\n"+
				   "+-----------\n"+
				   "| Parcelas \n"+
				   "+------------\n";
		for(int i=0;i<parcela_semanal.length;i++) {
			s = s.concat("|["+(i+1)+"] = "+parcela_semanal[i]+"\n");
		}
		s = s.concat("+------------\n");
		return s;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public int getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}
	
	public int quantidadeDeParcelas() {
		return this.parcela_semanal.length;
	}
	
	public double getValor() {
		return valor;
	}	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public int pagaParcela(int parcela) {
		if(parcela_semanal[parcela-1]!=0) {
			parcela_semanal[parcela-1]=0;
			return 0;
		}
		return 1;
	} 
	public int parcelasRestantes() {
		int qtd=0;
		for(int i=0; i<parcela_semanal.length;i++) {
			if(parcela_semanal[i]!=0) {
				qtd++;
			}
		}
		return qtd;
	}

	public Boolean getValidade() {
		return validade;
	}

	public void setValidade(Boolean validade) {
		this.validade = validade;
	}
	
	public void setParcelasRestantes(int parcelas) {
		this.parcelasRestantes=parcelas;
	}
	public int getParcelasRestantes() {
		return parcelasRestantes;
	}
}
