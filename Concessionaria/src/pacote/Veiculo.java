package pacote;

public class Veiculo {
	public static int cont = 1;
	private String marca, modelo;
	private int ano, idCadastro;
	
	Veiculo(String marca, String modelo, int ano){
		setMarca(marca);
		setModelo(modelo);
		setAno(ano);
		setIdCadastro(cont++);
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
}
