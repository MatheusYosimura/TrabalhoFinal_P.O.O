package pacote;
import java.util.ArrayList;
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
		if(id_garagem!=0) {
			cont=id_garagem;
			setIdGaragem(id_garagem);
		}else {
			setIdGaragem(cont++);
		}
	} 
	
	public static ArrayList<Garagem> carregaDadosGaragem()throws IOException {
		File f = new File("dados/garagem");
		String[] lista = f.list();
		ArrayList<Garagem> garagem = new ArrayList(); 
		for(int i=0; i<lista.length;i++) {
			Garagem gar;
			InputStream is = new FileInputStream("dados/garagem/"+lista[i]);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String[] g = (br.readLine()).split(";");
			gar  = new Garagem(g[0], g[1], Integer.parseInt(g[2]), Integer.parseInt(g[3]));
			gar.carregaDadosVagas(Integer.parseInt(g[3]));
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
	
	public void preencheVaga(int id_vaga, int id_carro, int id) {//PRECISA TER UMA EMPRESA ASSOCIADA PARA CHAMAR ESSE MÉTODO
		Vaga vaga = retornaVaga(id_vaga);
		if((vaga.getOcupacao()==false && getAssociado()==1) || id==0){ 
			Veiculo veiculo = empresa.retornaVeiculo(id_carro);
			vaga.setId_Veiculo(veiculo.getIdCadastro());
			atualizaVaga(vaga,id_vaga);
			veiculo.setId_vaga(vaga.getNumero());
			veiculo.setId_garagem(getIdGaragem());
			empresa.atualizaDadosVeiculo(veiculo, id_carro);
		}else {System.out.println("VAGA JÁ ESTÁ OCUPADA ( ALTERAR MÉTODO PARA PREENCHER VAGAS QUE FORAM SALVAS COM OCUPAÇÃO = TRUE");}
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
	public void carregaDadosVagas(int id_garagem) throws IOException{//CARREGA DADOS DA VAGA, MAS NÃO ASSOCIA VAGA X VEICULO
		ArrayList<Vaga> vaga = new ArrayList();
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
		//return vaga;
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
