package pacote;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.*;

public class Garagem {
	public static int cont=1;
	private  int associado=0;// 0 = Não associado / 1 = Associado
	private int idGaragem;
	private String nome, endereco;
	private ArrayList<Vaga> vagas = new ArrayList<Vaga>();
	private Empresa empresa = null; 
	
	Garagem(String nome, String endereco, int num, int id_garagem){
		setNome(nome);
		setEndereco(endereco);
		for(int i=0; i<num; i++) {
			vagas.add(new Vaga());
		}
		if(id_garagem==0) {
			setIdGaragem(cont);
			cont++;
		}else {
			//if(cont<id_garagem) {cont=id_garagem;}
			setIdGaragem(id_garagem);
			cont++;
		}
	} 
	public static Garagem retornaGaragem(int idGaragem, ArrayList<Garagem> garagem) {
		for(int i=0; i<garagem.size();i++) {
			Garagem comparacao = garagem.get(i);
			if(comparacao.getIdGaragem()==idGaragem) {
				return comparacao;
			}
		}
		return null;
	}
	public static void atualizaDadosGaragem(Garagem g, int idGaragem, ArrayList<Garagem> garagem) {
		for(int i=0; i<garagem.size();i++) {
			Garagem comparacao = garagem.get(i);
			if(comparacao.getIdGaragem()==idGaragem) {
				garagem.set(i, g);
			}
		}
		//return garagem;//Chama por "empresa" mas quem recebe o retorno é "garagem"
	}
	public static ArrayList<Garagem> carregaDadosGaragem()throws IOException {
		File f = new File("dados/garagem");
		String[] lista = f.list();
		ArrayList<Garagem> garagem = new ArrayList<Garagem>(); 
		for(int i=0; i<lista.length;i++) {
			if(TratamentoExcecoes.verificaArquivo("dados/garagem", "garagem_"+(i+1))){
				return null;
			}
			Garagem gar;
			InputStream is = new FileInputStream("dados/garagem/"+lista[i]);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String[] g = (br.readLine()).split(";");
			gar  = new Garagem(g[0], g[1], Integer.parseInt(g[2]), Integer.parseInt(g[3]));
			if(gar.carregaDadosVagas(Integer.parseInt(g[3]))) {
				JOptionPane.showMessageDialog(null, "Vagas da garagem não carregadas\nCarregamento das garagens interrompido");
				return null;
			}
			/*for (int j=0;j<gar[i].vagas.size();i++) {//PRECISA TER UMA EMPRESA ASSOCIADA PARA CHAMAR ESSE MÉTODO
				Vaga aux_vaga = gar[i].vagas.get(j);
				gar[i].preencheVaga(aux_vaga.getNumero(),aux_vaga.getId_Veiculo());
				gar[i].vagas.set(j, aux_vaga);
			}*/
			garagem.add(gar);
			br.close();
			isr.close();
			is.close();
		}
		System.out.println("CarregaDadosGaragem V");
		return garagem;//APOS RETORNAR, AS VAGAS DEVEM SER ASSOCIADAS AO SEUS VEICULOS
	}
	public void salvaDadosGaragem()throws IOException {
		OutputStream os = new FileOutputStream("dados/garagem/garagem_"+getIdGaragem()+"xx.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(getNome()+";"+getEndereco()+";"+vagas.size()+";"+getIdGaragem()+";\n");
		//salvaDadosVagas();
		bw.close();
		osw.close();
		os.close();
		System.out.println("SalvaDadosGaragem");
		salvaDadosVagas();
	}
	public String toString() {
		String s = "+-----------\n"+
				   "| Garagem "+getIdGaragem()+"\n"+
				   "+-----------\n"+
				   "|Nome: "+getNome()+"\n"+
				   "|Associada: "+getEmpresa().getNome()+"\n"+
				   "|Endereço: "+getNome()+"\n"+
				   "|Número de vagas: "+numeroDeVagas()+"\n"+
				   "+-----------";
		return s;
	}
	
	public void preencheVaga(int id_vaga, int id_carro, int id) {//PRECISA TER EMPRESA ASSOCIADA 
		Vaga vaga = retornaVaga(id_vaga);
		Veiculo veiculo=null;
		if((vaga.getOcupacao()==false && getAssociado()==1) || id==0){ //id=0 para quando a vaga precisa ser carregada
			veiculo = empresa.retornaVeiculo(id_carro);
			vaga.setId_Veiculo(veiculo.getIdCadastro());
			atualizaVaga(vaga,id_vaga);
			veiculo.setId_vaga(vaga.getNumero());
			veiculo.setId_garagem(getIdGaragem());
		}else {System.out.println("VAGA JÁ ESTÁ OCUPADA ( ALTERAR MÉTODO PARA PREENCHER VAGAS QUE FORAM SALVAS COM OCUPAÇÃO = TRUE");}
		//return veiculo; / RETORNA VEICULO PARA ATUALIZAR
	}
	public Vaga getVagas(int index){//TRABALHAR COM EXCESSÃO PARA PROGRAMA NÃO RETORNAR UMA VAGA NULL 
		return vagas.get(index);
	}
	public void salvaDadosVagas()throws IOException {
		OutputStream os = new FileOutputStream("dados/vagas/vagas_"+getIdGaragem()+"xx.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for (int i=0; i<vagas.size();i++) {
			Vaga vaga = vagas.get(i);
			bw.write(vaga.getNumero()+";"+vaga.getId_Veiculo()+";"+vaga.getOcupacao()+";\n");
		}
		bw.close();
		osw.close();
		os.close();
		System.out.println("SalvaDadosVagas V");
	}
	public Boolean carregaDadosVagas(int id_garagem) throws IOException{//CARREGA DADOS DA VAGA, MAS NÃO ASSOCIA VAGA X VEICULO
		ArrayList<Vaga> vaga = new ArrayList<Vaga>();
		if(TratamentoExcecoes.verificaArquivo("dados/vagas", "vagas_"+(id_garagem))){
			return true;
		}
		InputStream is = new FileInputStream("dados/vagas/vagas_"+id_garagem);
		InputStreamReader isr = new InputStreamReader (is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		int i=0;
		while(s!=null) {
			String[] v= s.split(";");
			Vaga slot = new Vaga(Integer.parseInt(v[0]), Integer.parseInt(v[1]),Boolean.parseBoolean(v[2]));
			vagas.set(i++, slot);
			s = br.readLine();
		}
		System.out.println("CarregaDadosVagas V");
		br.close();
		isr.close();
		is.close();
		return false;
	}
	public int numeroDeVagas() {
		return vagas.size();
	}
	public Vaga retornaVaga(int idVaga) {
		for(int i=0; i<vagas.size();i++) {
			Vaga aux_vaga = vagas.get(i);
			if(aux_vaga.getNumero()==idVaga){return aux_vaga;}
		}
		return null;
	}
	public void atualizaVaga(Vaga v, int idVaga) {
		Vaga aux_vaga = retornaVaga(idVaga);
		for(int i=0; i<vagas.size();i++) {
			Vaga comparacao = vagas.get(i);
			if (aux_vaga.getNumero()==comparacao.getNumero()){vagas.set(i,aux_vaga);}
		}
	}
	public String DadosVaga(int aux_id, int aux_ind) {//ou AUX_ID = -1 ou AUX_IND = -1 
		Vaga vag = null;
		if(aux_id==-1) {vag = vagas.get(aux_ind);}
		if(aux_ind==-1) {vag = retornaVaga(aux_id);}
		String s = vag.toString();
		return s;
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

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		setAssociado(1);
	}

	public int getAssociado() {
		return associado;
	}
	public void setAssociado(int associado) {
		this.associado = associado;
	}

	public int getIdGaragem() {
		return idGaragem;
	}
	public void setIdGaragem(int idGaragem) {
		this.idGaragem = idGaragem;
	}
}
