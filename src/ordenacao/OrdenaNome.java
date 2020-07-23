package ordenacao;

import java.util.Comparator;

import dados.Prefeito;

public class OrdenaNome implements Comparator<Prefeito> {

	public int compare(Prefeito prefeito1, Prefeito prefeito2) {
		return prefeito1.getNome().toUpperCase().toString().compareTo(prefeito2.getNome().toUpperCase().toString());
	}
}
