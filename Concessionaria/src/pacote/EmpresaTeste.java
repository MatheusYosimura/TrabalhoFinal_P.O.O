package pacote;
import static org.junit.Assert.*;
import java.util.*;
import java.io.IOException;
import org.junit.Test;

public class EmpresaTeste {
	Empresa empresaTeste = null;
	ArrayList<Garagem> garagem = new ArrayList<Garagem>();
	@Test
	public void testeTotal() throws IOException {
		empresaTeste = new Empresa("nome","endereco","razao",123,0);
		garagem.add(new Garagem("garagem_teste","endereco_teste",10,0));
		Garagem garagemTeste= garagem.get(0);
		empresaTeste.associaGaragem(garagemTeste, empresaTeste);
		empresaTeste.adicionaVeiculo("veiculo_teste", "modelo_teste", 2001, -1);
		Veiculo veiculoTeste = empresaTeste.retornaVeiculo(1);
		empresaTeste.adicionaFuncionario("nome_teste", "enredeco_teste", 123123, "função_teste", -1);
		Funcionario funcionarioTeste = empresaTeste.retornaFuncionario(1);
		empresaTeste.adicionaCliente("nome_teste", "endereco_teste", 321321, "método_teste", -1);
		Cliente clienteTeste = empresaTeste.retornaCliente(1);
		empresaTeste.adicionaContrato(1, 1, 1, 1, 10, true, 0);
		Contrato contratoTeste = empresaTeste.retornaContrato(1);
		assertEquals(123, empresaTeste.getCNPJ());
		assertEquals(1,garagemTeste.getIdGaragem());
		assertEquals(1,veiculoTeste.getIdCadastro());		
		assertEquals(1,funcionarioTeste.getIdCadastro());
		assertEquals(1,clienteTeste.getCadastro());
		assertEquals(1,contratoTeste.getIdContrato());
		empresaTeste.pagaParcelaContrato(1, 1, 10);
		empresaTeste.encerraContrato(1);
		empresaTeste.removeVeiculo(1);
		empresaTeste.removeFuncionario(1);
		empresaTeste.removeCliente(1);
		assertEquals(null,empresaTeste.retornaVeiculo(1));
		assertEquals(null,empresaTeste.retornaFuncionario(1));
		assertEquals(null,empresaTeste.retornaCliente(1));
		assertEquals(false,(empresaTeste.retornaContrato(1)).getValidade());
	}
}
