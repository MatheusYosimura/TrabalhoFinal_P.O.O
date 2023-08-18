package pacote;
import java.io.*;
import java.util.*;

public class Empresa implements Organizacao{
	public static Boolean exist=false;
	private int CNPJ;
	private String nome, endereco, razaoSocial;
	private ArrayList<Funcionario>  funcionarios = new ArrayList<Funcionario>();
	private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
	private ArrayList<Garagem> garagem = new ArrayList<Garagem>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	Empresa(String nome, String endereco, String razaoSocial, int CNPJ, int flag) throws IOException{
		setNome(nome);
		setEndereco(endereco);
		setRazaoSocial(razaoSocial);
		setCNPJ(CNPJ);
		if(flag==1) {
			carregaDadosVeiculo();
			carregaDadosFuncionarios();
			carregaDadosClientes();
		}
		//salvaDadosEmpresa();
		exist=true;
	}
	public static Empresa carregaDadosEmpresa()throws IOException {
		if(TratamentoExcecoes.verificaArquivo("dados", "empresa.txt")){
			return null;
		}
		FileInputStream fis = new FileInputStream("dados/empresa.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		Empresa emp = null;
		String s = br.readLine();
		if (s.equals("")) {
			System.out.println("ERRO ao carregar arquivo da empresa");
			System.exit(0);
		}else{
			String[] e = s.split(";");
			String aux_nome = e[0], aux_end = e[1], aux_razao = e[2];
			int aux_CNPJ = Integer.parseInt(e[3]);
			emp = new Empresa(aux_nome,aux_end,aux_razao,aux_CNPJ,1);
		}
		br.close();
		return emp;
	}
	public void salvaDadosEmpresa() throws IOException{
		OutputStream os = new FileOutputStream("dados/empresassss.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(getNome()+";"+getEndereco()+";"+getRazaoSocial()+";"+getCNPJ()+";\n");
		bw.close();
		osw.close();
		os.close();
		salvaDadosVeiculo();
		salvaDadosFuncionarios();
		salvaDadosContratos();
		salvaDadosClientes();
	}
	public String toString() {
		String s = "+-----------\n"+
				   "| EMPRESA\n"+
				   "+-----------\n"+
				   "|Nome: "+getNome()+"\n"+
				   "|Razão Social: "+getRazaoSocial()+"\n"+
				   "|CNPJ: "+getCNPJ()+"\n"+
				   "|Endereço: "+getEndereco()+"\n"+
				   "+-----------\n";
		return s;
	}
	
	@Override
	public int adicionaVeiculo(String marca, String modelo, int ano, int id) {
		//Para criar um novo veículos, id=-1
		Veiculo veic = new Veiculo(marca, modelo, ano,id);
		veiculos.add(veic);
		return veic.getIdCadastro();
	}
	public void removeVeiculo(int idVeiculo) {
		Veiculo comparacao;
		for(int i=0; i<veiculos.size(); i ++) {
			comparacao=veiculos.get(i);
			if(comparacao.getIdCadastro()==idVeiculo) {
				System.out.println(comparacao.getIdCadastro());
				veiculos.remove(i);
			}
		}
	}
	@Override
	public Veiculo retornaVeiculo(int idVeiculo) {
		Veiculo comparacao;
		for(int i=0; i<veiculos.size(); i ++) {
			comparacao=veiculos.get(i);
			if(comparacao.getIdCadastro()==idVeiculo) {
				return comparacao;
			}
		}
		System.out.println("Veículo não encontrado");
		return null;
	}
	public void carregaDadosVeiculo() throws IOException{
		if(TratamentoExcecoes.verificaArquivo("dados", "veiculos.txt")){
			return ;
		}
		FileInputStream fis = new FileInputStream("dados/veiculos.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		if (s.equals("")) {
			System.out.println("Arquivo de veículos está vazio");
		}else{
			int aux_id,aux_ano;
			String aux_marca,aux_modelo;
			while(s!=null){
				String[] v = s.split(";");
				aux_marca=v[0];
				aux_modelo=v[1];
				aux_ano=Integer.parseInt(v[2]);
				aux_id=Integer.parseInt(v[3]);
				adicionaVeiculo(aux_marca,aux_modelo,aux_ano,aux_id);
				s=br.readLine();
			}
		}
		br.close();
		isr.close();
		fis.close();
		System.out.println("FIM CARREGA VEICULOS");
	}
	public void salvaDadosVeiculo() throws IOException {
		OutputStream os = new FileOutputStream("dados/veiculossss.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for(int i=0;i<veiculos.size();i++) {
			Veiculo v = veiculos.get(i);
			bw.write(v.getMarca()+";"+v.getModelo()+";"+v.getAno()+";"+v.getIdCadastro()+";\n");
		}
		bw.close();
		osw.close();
		os.close();
		System.out.println("FIM SALVA VEICULOS");
	}
	public int retornaNumeroVeiculos() {
		return veiculos.size();
	}
	public void atualizaDadosVeiculo(Veiculo v, int idVeiculo) {
		Veiculo comparacao;
		for(int i=0; i<veiculos.size(); i ++) {
			comparacao=veiculos.get(i);
			if(comparacao.getIdCadastro()==idVeiculo) {
				veiculos.set(i, v);
			}
		}
	}
	public String dadosVeiculo(int aux_id, int aux_ind) {//ou AUX_ID = -1 ou AUX_IND = -1 
		Veiculo veic = null;
		String s = null;
		if(aux_id==-1) {veic = veiculos.get(aux_ind);}
		if(aux_ind==-1) {veic = retornaVeiculo(aux_id);}
		if(veic.getId_garagem()==0){
			s = veic.toString("-----","-----");
		}else{
			Garagem gara = retornaGaragem(veic.getId_garagem());
			s = veic.toString(gara.getNome(),""+veic.getId_vaga());
		}
		return s;
	}
	
	@Override
	public int adicionaFuncionario(String nome, String endereco, int CPF, String funcao,int id) {
		//Para criar um novo funcionario, id=0
		Funcionario func = new Funcionario(nome, endereco, CPF, funcao,id);
		funcionarios.add(func);
		return func.getIdCadastro();
	}
	public void removeFuncionario(int idFuncionario) {
		Funcionario comparacao;
		for(int i=0; i<funcionarios.size();i++){
			comparacao = funcionarios.get(i);
			if(comparacao.getIdCadastro()==idFuncionario) {
				funcionarios.remove(i);
			}
		}
	}
	public void atualizaDadosFuncionario(Funcionario funcionario,int idFuncionario) {
		Funcionario comparacao;
		for (int i=0; i<funcionarios.size(); i++) {
			comparacao=funcionarios.get(i);
			if(comparacao.getIdCadastro()==idFuncionario) {
				funcionarios.set(i,funcionario);
			}
		}
	}
	@Override
	public Funcionario retornaFuncionario(int idFuncionario) {
		Funcionario comparacao;
		for(int i=0; i<funcionarios.size();i++){
			comparacao = funcionarios.get(i);
			if(comparacao.getIdCadastro()==idFuncionario) {
				return comparacao;
			}
		}
		return null;
	}
	public void carregaDadosFuncionarios() throws IOException{
		if(TratamentoExcecoes.verificaArquivo("dados", "funcionarios.txt")){
			return ;
		}
		FileInputStream fis = new FileInputStream("dados/funcionarios.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		if (s.equals("")) {
			System.out.println("Arquivo de veículos está vazio");
		}else{
			int aux_id,aux_cpf;
			String aux_nome,aux_funcao,aux_end;
			while(s!=null){
				String[] func = s.split(";");
				aux_nome=func[0];
				aux_end=func[1];
				aux_cpf=Integer.parseInt(func[2]);
				aux_funcao=func[3];
				aux_id=Integer.parseInt(func[4]);
				adicionaFuncionario(aux_nome,aux_end,aux_cpf,aux_funcao,aux_id);
				s=br.readLine();
			}
		}
		br.close();
		isr.close();
		fis.close();
		System.out.println("FIM CARREGA FUNCIONARIOS");
	}
	public void salvaDadosFuncionarios()throws IOException{
		OutputStream os = new FileOutputStream("dados/funcionariossss.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for(int i=0;i<funcionarios.size();i++) {
			Funcionario f = funcionarios.get(i);
			bw.write(f.getNome()+";"+f.getEndereco()+";"+f.getCPF()+";"+f.getFuncao()+";"+f.getIdCadastro()+";\n");
		}
		bw.close();
		osw.close();
		os.close();
		System.out.println("FIM SALVA FUNCIONARIOS");
	}
	public int numeroDeFuncionarios() {
		return funcionarios.size();
	}
	public String dadosFucionario(int aux_id, int aux_ind) {//ou AUX_ID = -1 ou AUX_IND = -1 
		Funcionario func = null;
		if(aux_id==-1) {func = funcionarios.get(aux_ind);}
		if(aux_ind==-1) {func = retornaFuncionario(aux_id);}
		String s = func.toString();
		return s;
	}
	
	//CONTRATO nÃO PODE TER MAIS DE 8 PARCELAS ( 8 semanas de uso do carro)
	@Override
	public void adicionaContrato(int idVeiculo, int idCliente, int parcelas,int restantes, double valor,Boolean validade,int id){
		//Para criar um novo contrato, id=0
		Contrato cont = null;
		if(id!=0) {
			cont = new Contrato(idVeiculo, idCliente, parcelas,restantes, valor, validade, id);
			contratos.add(cont);
		}else{
			Veiculo aux_veiculo = retornaVeiculo(idVeiculo);
			if(aux_veiculo.getId_vaga()!=0) {
				Garagem aux_garagem = retornaGaragem(aux_veiculo.getId_garagem());
				Vaga aux_vaga = aux_garagem.retornaVaga(aux_veiculo.getId_vaga());
				cont = new Contrato(idVeiculo, idCliente, parcelas, parcelas, valor, true, id);
				contratos.add(cont);
				aux_vaga.setId_Veiculo(0);
				aux_vaga.liberaVaga();
				System.out.println(aux_vaga.getNumero()+";"+aux_vaga.getOcupacao());
				aux_garagem.atualizaVaga(aux_vaga,aux_veiculo.getId_vaga());				
				atualizaDadosGaragem(aux_garagem,aux_veiculo.getId_garagem());
				aux_veiculo.setId_vaga(0);
				atualizaDadosVeiculo(aux_veiculo,idVeiculo);
			}
		}		
	}
	@Override
	public Contrato retornaContrato(int idContrato){
		Contrato comparacao;
		for (int i=0; i<contratos.size(); i++) {
			comparacao=contratos.get(i);
			if(comparacao.getIdContrato()==idContrato) {
				return comparacao;
			}
		}
		System.out.println("Contrato não encontrado");
		return null;
	}
	public void atualizaDadosContrato(Contrato contrato,int idContrato) {
		Contrato comparacao;
		for (int i=0; i<contratos.size(); i++) {
			comparacao=contratos.get(i);
			if(comparacao.getIdContrato()==idContrato) {
				contratos.set(i,contrato);
			}
		}
	}
	public void carregaDadosContratos()throws IOException{//SOMENTE APÓS O CARREGAMENTO DAS GARAGENS
		if(TratamentoExcecoes.verificaArquivo("dados", "contratos.txt")){
			return;
		}
		FileInputStream fis = new FileInputStream("dados/contratos.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		if (s.equals("")) {
			System.out.println("Arquivo de Contrato está vazio");
		}else{
			int aux_idCli,aux_idVei,aux_idCont,aux_parc,aux_rest;
			double aux_valor;
			Boolean validade;
			while(s!=null){
				String[] c = s.split(";");
				aux_idVei=Integer.parseInt(c[0]);
				aux_idCli=Integer.parseInt(c[1]);
				aux_parc=Integer.parseInt(c[2]);
				aux_rest=Integer.parseInt(c[3]);
				aux_valor=Double.parseDouble(c[4]);
				validade=Boolean.parseBoolean(c[5]);
				aux_idCont=Integer.parseInt(c[6]);
				adicionaContrato(aux_idVei,aux_idCli,aux_parc, aux_rest,aux_valor, validade, aux_idCont);
				if(validade==true) {
					Veiculo aux_veic = retornaVeiculo(aux_idVei);
					Garagem aux_gar = retornaGaragem(aux_veic.getId_garagem());
					Vaga aux_vaga = aux_gar.retornaVaga(aux_veic.getId_vaga());
					aux_vaga.setId_Veiculo(0);
					aux_vaga.liberaVaga();
					aux_gar.atualizaVaga(aux_vaga, aux_vaga.getNumero());
					atualizaDadosGaragem(aux_gar,aux_veic.getId_garagem());
					aux_veic.setId_vaga(0);
					atualizaDadosVeiculo(aux_veic,aux_idVei);
				}
				s=br.readLine();
			}
		}
		br.close();
		isr.close();
		fis.close();
		System.out.println("FIM CARREGA CONTRATOS");
	}
	public void salvaDadosContratos()throws IOException{
		OutputStream os = new FileOutputStream("dados/contratosssss.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for(int i=0;i<contratos.size();i++) {
			Contrato c = contratos.get(i);
			bw.write(c.getIdVeiculo()+";"+c.getIdCliente()+";"+c.quantidadeDeParcelas()+";"
			+c.parcelasRestantes()+";"+c.getValor()+";"+c.getValidade()+";"+c.getIdContrato()+";\n");
		}
		bw.close();
		osw.close();
		os.close();
		System.out.println("FIM SALVA CONTRATOS");
	}
	public void pagaParcelaContrato(int idContrato, int parcela, double valor){
		Contrato aux_contrato = retornaContrato(idContrato);
		if(valor == aux_contrato.getValor()) {
			if(aux_contrato.pagaParcela(parcela)==1) {
				new TratamentoExcecoes("Parcela já paga\nOrdem de pagamento cancelada!");
			}
			aux_contrato.setParcelasRestantes(aux_contrato.getParcelasRestantes()-1);
			atualizaDadosContrato(aux_contrato,idContrato);
		}
	}
	public void encerraContrato(int idContrato) {
		Contrato aux_contrato = retornaContrato(idContrato);
		if(aux_contrato.parcelasRestantes()==0) {
			aux_contrato.setValidade(false);
			Veiculo aux_veiculo = retornaVeiculo(aux_contrato.getIdVeiculo());
			Garagem aux_garagem = retornaGaragem(aux_veiculo.getId_garagem());
			for(int i=0,x=0; (i<aux_garagem.numeroDeVagas())&&(x==0);i++) {
				Vaga aux_vaga = aux_garagem.getVagas(i);
				if(aux_vaga.getOcupacao()==false) {
					aux_garagem.preencheVaga(aux_vaga.getNumero(), aux_veiculo.getIdCadastro(),1);
					x=1;
				}
			}
			//atualizaDadosGaragem(aux_garagem,aux_garagem.getIdGaragem());
			//atualizaDadosVeiculo(aux_veiculo,aux_veiculo.getIdCadastro());
		}else {
			System.out.println("Ainda há parcelas a serem pagas");
		}
		
	}
	public int numeroDeContratos() {
		return contratos.size();
	}
	public String dadosContrato(int aux_id, int aux_ind) {//ou AUX_ID = -1 ou AUX_IND = -1 
		Contrato contr = null;
		if(aux_id==-1) {contr = contratos.get(aux_ind);}
		if(aux_ind==-1) {contr = retornaContrato(aux_id);}
		Cliente clie = retornaCliente(contr.getIdCliente());
		Veiculo veic = retornaVeiculo(contr.getIdVeiculo());
		String s = contr.toString(0,clie.getCPF());// 0 deve ser substituido pela placa
		return s;
	}
	
	public int associaGaragem(Garagem gar, Empresa empresa) {
		int flag = 0; // Retorno da flag 0 = ERRO NA ASSOCIAÇÃO / 1 = ASSOCIAÇÃO BEM SUCEDIDA
		if (gar.getAssociado()==0){
			gar.setEmpresa(empresa);
			flag=1;
			this.garagem.add(gar);
		}
		return flag;
	}
	public Garagem retornaGaragem(int idGaragem) {
		for(int i=0; i<garagem.size();i++) {
			Garagem comparacao = garagem.get(i);
			if(comparacao.getIdGaragem()==idGaragem) {
				return comparacao;
			}
		}
		return null;
	}
	public void atualizaDadosGaragem(Garagem g, int idGaragem) {
		for(int i=0; i<garagem.size();i++) {
			Garagem comparacao = garagem.get(i);
			if(comparacao.getIdGaragem()==idGaragem) {
				garagem.set(i, g);
			}
		}
		//return garagem;//Chama por "empresa" mas quem recebe o retorno é "garagem"
	}
	public void atualizaTotalGaragem(ArrayList<Garagem> aux_gar) {
		this.garagem = aux_gar;
	}
	
	public int adicionaCliente(String nome, String endereco, int CPF, String pagamento, int id) {
		//Para criar um novo contrato, id=0
		Cliente cliente = new Cliente(nome,endereco,CPF,pagamento, id);
		clientes.add(cliente);
		return cliente.getCadastro();
	}
	public void removeCliente(int idCliente) {
		Cliente cliente;
		for (int i=0; i<clientes.size();i++) {
			cliente = clientes.get(i); 
			if(cliente.getCadastro()==idCliente) {
				clientes.remove(i);
			}
		}
	}
	public void atualizaDadosCliente(Cliente cliente, int idCliente) {
		Cliente comparacao;
		for (int i=0; i<clientes.size(); i++) {
			comparacao=clientes.get(i);
			if(comparacao.getCadastro()==idCliente) {
				clientes.set(i,cliente);
			}
		}
	}
	public Cliente retornaCliente(int idCliente) {
		Cliente cliente;
		for (int i=0; i<clientes.size();i++) {
			cliente = clientes.get(i); 
			if(cliente.getCadastro()==idCliente) {
				return cliente;
			}
		}
		return null;
	}
	public void carregaDadosClientes() throws IOException{
		if(TratamentoExcecoes.verificaArquivo("dados", "clientes.txt")){
			return ;
		}
		InputStream is = new FileInputStream("dados/clientes.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		if (s.equals("")) {
			System.out.println("Arquivo de Clientes está vazio");
		}else{
			while (s!=null) {
				String[] c = s.split(";");
				Cliente cliente = new Cliente(c[0],c[1],Integer.parseInt(c[2]),c[3],Integer.parseInt(c[4]));
				clientes.add(cliente);
				s=br.readLine();
			}
		}
		br.close();
		isr.close();
		is.close();	
		System.out.println("CarregaDadosClientes");
	}
	public int numeroDeClientes() {
		return clientes.size();
	}
	public void salvaDadosClientes()throws IOException{
		OutputStream os = new FileOutputStream("dados/clientessss.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for (int i=0; i<clientes.size();i++) {
			Cliente cliente = clientes.get(i);
			bw.write(cliente.getNome()+";"+cliente.getEndereco()+";"+cliente.getCPF()+";"+cliente.getPagamento()+";"+cliente.getCadastro()+";\n");
		}
		bw.close();
		osw.close();
		os.close();
		System.out.println("SalvaDadosClientes");
	}
	public String dadosCliente(int aux_id, int aux_ind) {//ou AUX_ID = -1 ou AUX_IND = -1 
		String s = null;
		Cliente clie = null;
		if(aux_id==-1) {clie = clientes.get(aux_ind);}
		if(aux_ind==-1) {clie = retornaCliente(aux_id);}
		s = clie.toString();
		return s;
	}	

	public int getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(int cNPJ) {
		this.CNPJ=cNPJ;
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
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
}













