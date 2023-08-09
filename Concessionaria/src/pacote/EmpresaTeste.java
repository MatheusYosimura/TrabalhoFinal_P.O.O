package pacote;
import static org.junit.Assert.*;
import org.junit.Test;

public class EmpresaTeste {
	Empresa empresa = new Empresa("nome","endereco","razaosocial", 1234);
	public void testeEmpresa() {
		assertEquals("nome",empresa.getNome());	
	}
	public void testeVeiculo() {
		int ID = empresa.adicionaVeiculo("marca", "modelo", 2002);
		Veiculo veiculo = empresa.retornaVeiculo(ID);
		assertEquals(ID,veiculo.getIdCadastro());
	}
	public void testeFuncionario() {
		int ID = empresa.adicionaFuncionario("nome", "endereco", 1001, "funcao");
		Funcionario funcionario = empresa.retornaFuncionario(ID);
		assertEquals(ID,funcionario.getIdCadastro());
	}
	public void testeContrato(){
		int ID = empresa.adicionaContrato(123, 123, 10, 100.00);
		Contrato contrato = empresa.retornaContrato(ID);
		assertEquals(ID,contrato.getIdContrato());
	}
}
