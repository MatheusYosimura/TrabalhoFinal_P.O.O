package pacote;
import static org.junit.Assert.*;
import org.junit.*;
//import org.junit.Test;

public class ClienteTeste {
	@Test
	public void testeCliente() {
		int ID=Cliente.cont;
		Cliente cliente = new Cliente("nome","endereco",1001, "pagamento");
		assertEquals(ID,cliente.getCadastro());
	}
}
