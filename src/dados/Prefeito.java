package dados;

public class Prefeito {
	// declaracoes
	private String nome;
	private String partidoAssociado;
	private Integer numeroLegenda;

	// metodo construtor
	public Prefeito(String nome, String partidoAssociado, int numeroLegenda) {
		setNome(nome);
		setPartidoAssociado(partidoAssociado);
		setNumeroLegenda(numeroLegenda);
	}

	// metodos acessores
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPartidoAssociado() {
		return partidoAssociado;
	}

	public void setPartidoAssociado(String partidoAssociado) {
		this.partidoAssociado = partidoAssociado;
	}

	public Integer getNumeroLegenda() {
		return numeroLegenda;
	}

	public void setNumeroLegenda(Integer numeroLegenda) {
		this.numeroLegenda = numeroLegenda;
	}

}
