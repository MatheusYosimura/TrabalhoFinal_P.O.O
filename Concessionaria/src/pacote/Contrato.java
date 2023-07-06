package pacote;

public class Contrato {
	public static int cont=1;
	private int idCliente, idVeiculo, idContrato;
	private double[] parcelas;
	Contrato (int idVeiculo, int idCliente, int qtdParcelas, double valor){
		setIdVeiculo(idVeiculo);
		setIdCliente(idCliente);
		parcelas = new double[qtdParcelas];
		for(int i=0; i< qtdParcelas; i++){
			parcelas[i]=valor;
		}
		setIdContrato(cont++);
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
	
	public void imprime() {
		System.out.println("+----------"
				+ "\n|CONTRATO DE LOCAÇÃO"
				+ "\n|Número do contrato: "+getIdContrato()
				+ "\n|Cliente: "+getIdCliente()
				+ "\n|Veículo: "+getIdVeiculo()
				+ "\n|Parcelas: #####"
				+ "\n+----------");
	}
}
