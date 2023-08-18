package pacote;
import java.io.*;

import javax.swing.JOptionPane;

public class TratamentoExcecoes {
	public TratamentoExcecoes(String msg) {
		JOptionPane.showMessageDialog(null, msg,"ERRO",JOptionPane.WARNING_MESSAGE);
	}
	public static Boolean verificaArquivo(String local, String arquivo) {
		File f = new File(local);
		String[] lista = f.list();
		for(int i=0; i<lista.length;i++){
			if(lista[i].equals(arquivo)) {
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "Arquivo "+arquivo+" não encontrado","ERRO",JOptionPane.WARNING_MESSAGE);
		return true;
	}
}
