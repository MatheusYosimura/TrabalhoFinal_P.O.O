package pacote;

public interface Organizacao {
	public abstract int adicionaVeiculo(String marca, String modelo, int ano, int id);
	public abstract Veiculo retornaVeiculo(int idVeiculo);
	public abstract int adicionaFuncionario(String nome, String endereco, int CPF, String funcao, int id);
	public abstract Funcionario retornaFuncionario(int idFuncionario);
	public abstract int adicionaContrato(int idVeiculo, int idCliente, int parcelas,int restantes, double valor,Boolean validade,int id);
	public abstract Contrato retornaContrato(int idContrato);
}
