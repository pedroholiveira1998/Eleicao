package ordenacao;

import java.util.Comparator;

import dados.Prefeito;

public class OrdenaLegenda implements Comparator<Prefeito> {

	public int compare(Prefeito prefeito1, Prefeito prefeito2) {
		if (prefeito1.getNumeroLegenda() > prefeito2.getNumeroLegenda()) {
			return 1;
		}
		if (prefeito1.getNumeroLegenda() == prefeito2.getNumeroLegenda()) {
			return 0;
		}
		if (prefeito1.getNumeroLegenda() < prefeito2.getNumeroLegenda()) {
			return -1;
		}
		return 0;
	}
}
