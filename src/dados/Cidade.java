package dados;

import java.util.ArrayList;

public class Cidade {
	private StringBuilder nome;
	private ArrayList<Prefeito> prefeitos;

	public Cidade(StringBuilder nome) {
		setNomeCidade(nome);
		prefeitos = new ArrayList<Prefeito>();
	}

	public StringBuilder getNomeCidade() {
		return nome;
	}

	public void setNomeCidade(StringBuilder nome) {
		this.nome = nome;
	}

	public ArrayList<Prefeito> getPrefeitos() {
		return prefeitos;
	}

	public void setPrefeitos(Prefeito prefeito) {
		prefeitos.add(prefeito);
	}

	public int quantidadePrefeitos() {
		return prefeitos.size();
	}

}
