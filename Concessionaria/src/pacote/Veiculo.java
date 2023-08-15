package pacote;

public class Veiculo {
	public static int cont = 1;
	private String marca, modelo;
	private int ano, idCadastro,id_vaga,id_garagem;//id_vaga=0 significa que o carro não está em nenhuma vaga
	
	Veiculo(String marca, String modelo, int ano, int id){//id=-1 para criar um novo veículo
		setMarca(marca);
		setModelo(modelo);
		setAno(ano);
		if(id==-1){
			setIdCadastro(cont++);
			setId_garagem(0);
		}else{
			cont=id;
			setIdCadastro(cont++);
		}
		setId_vaga(0);
	}
	public String toString(String nomeGaragem, String numeroVaga) {
		String s = "+-----------\n"+
				   "| Veiculo "+getIdCadastro()+"\n"+
				   "+-----------\n"+
				   "|Marca: "+getMarca()+"\n"+
				   "|Modelo: "+getModelo()+"\n"+
				   "|Placa: *********"+"\n"+
				   "|Ano: "+getAno()+"\n"+
				   "|Garagem: "+nomeGaragem+"\n"+
				   "|Vaga: "+numeroVaga+"\n"+
				   "+-----------";
				   
		return s;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getIdCadastro() {
		return idCadastro;
	}
	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}

	public int getId_vaga() {
		return id_vaga;
	}
	public void setId_vaga(int id_vaga) {
		this.id_vaga = id_vaga;
	}

	public int getId_garagem() {
		return id_garagem;
	}
	public void setId_garagem(int id_garagem) {
		this.id_garagem = id_garagem;
	}
}
