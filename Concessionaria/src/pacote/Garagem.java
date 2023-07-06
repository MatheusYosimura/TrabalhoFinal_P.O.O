package pacote;
import java.util.ArrayList;

public class Garagem {
	private String nome, endereco;
	private ArrayList<Vaga> vagas = new ArrayList();
	
	Garagem(String nome, String endereco, int num){
		setNome(nome);
		setEndereco(endereco);
		for(int i=0; i<num; i++) {
			vagas.add(new Vaga());
		}
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

}
