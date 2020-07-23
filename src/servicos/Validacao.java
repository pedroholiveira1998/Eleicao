package servicos;

import javax.swing.JOptionPane;
import dados.Cidade;
import dao.PrefeitoDAO;
import visao.TelaCadastroCidade;

public class Validacao {

	static final int MINIMO = 10;

	public static boolean minimoCadastro(Cidade eleicao) {
		if (!TelaCadastroCidade.umCadastro) {
			JOptionPane.showMessageDialog(null, "Nenhum candidato cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	public static void validaSair(boolean umCadastro) {
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair do programa?", "Sair",
				JOptionPane.YES_NO_OPTION);
		if (umCadastro == false) {
			JOptionPane.showMessageDialog(null, "Você deve realizar ao menos um cadastro!", "Erro ao sair",
					JOptionPane.ERROR_MESSAGE);
		} else if (resposta == 0) {
			PrefeitoDAO prefeitoDao = new PrefeitoDAO();
			prefeitoDao.deletar();
			System.exit(0);
		}
	}

	public static boolean validaNomePrefeito(String nome) {

		if (nome.length() <= 3) {
			JOptionPane.showMessageDialog(null, "Nome inválido! Digite novamente o nome do prefeito");
			return false;
		}

		return true;
	}

	public static boolean validaNumeroLegenda(String numeroLegendaString, Cidade eleicao) {
		try {
			Integer numeroLegenda = Integer.parseInt(numeroLegendaString);
			if (numeroLegenda < MINIMO || numeroLegenda > 99) {
				JOptionPane.showMessageDialog(null, "Valor Inválido!");
				return false;
			} else {
				for (int i = 0; i < eleicao.quantidadePrefeitos(); i++) {
					if (numeroLegenda == eleicao.getPrefeitos().get(i).getNumeroLegenda()) {
						JOptionPane.showMessageDialog(null, "Número de legenda já cadastrado!");
						return false;
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro! Digite novamente o número da legenda");
			return false;
		}
		return true;
	}

	public static boolean validaPartidoAssociado(String partidoAssociado, Cidade eleicao) {

		if (validaPontuacaoEspaçamento(partidoAssociado)) {
			JOptionPane.showMessageDialog(null, "A sigla do partido não pode ter espaços nem caracteres especiais",
					"Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (partidoAssociado.length() < 2 || partidoAssociado.length() > 10) {
			JOptionPane.showMessageDialog(null, "A sigla do partido precisa possuir mais de 2 letras e menos de 10:",
					"Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			for (int i = 0; i < eleicao.quantidadePrefeitos(); i++) {
				if (partidoAssociado.equalsIgnoreCase(eleicao.getPrefeitos().get(i).getPartidoAssociado())) {
					JOptionPane.showMessageDialog(null, "Partido já cadastrado!", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}
		return true;
	}

	public static boolean validaPontuacaoEspaçamento(String partidoAssociado) {
		for(int i=0;i<partidoAssociado.length();i++) {
			if(partidoAssociado.charAt(i)==' ' || partidoAssociado.charAt(i)=='!' || partidoAssociado.charAt(i)=='@' || partidoAssociado.charAt(i)=='#' || partidoAssociado.charAt(i)=='$' || partidoAssociado.charAt(i)=='%' || partidoAssociado.charAt(i)=='¨' || partidoAssociado.charAt(i)=='&' || partidoAssociado.charAt(i)=='.' || partidoAssociado.charAt(i)==',' || partidoAssociado.charAt(i)=='?' || partidoAssociado.charAt(i)=='*' || partidoAssociado.charAt(i)=='(' || partidoAssociado.charAt(i)==')' || partidoAssociado.charAt(i)=='_' || partidoAssociado.charAt(i)=='-' || partidoAssociado.charAt(i)=='+' || partidoAssociado.charAt(i)=='/' || partidoAssociado.charAt(i)=='\\' || partidoAssociado.charAt(i)=='=' || partidoAssociado.charAt(i)=='|' || partidoAssociado.charAt(i)==':' || partidoAssociado.charAt(i)==';' || partidoAssociado.charAt(i)=='¬' )
				return true;
		}
		return false;
	}
	
	public static boolean validaNomeCidade(StringBuilder nome) {

		if (nome == null || nome.length() <= 2) {
			JOptionPane.showMessageDialog(null, "Nome inválido! Digite novamente o nome da cidade");
			return false;
		}

		return true;
	}

	public static boolean validaSair() {
		int resposta;
		do {
			resposta = JOptionPane.showConfirmDialog(null, "Deseja sair do programa?", "Sair",
					JOptionPane.YES_NO_OPTION);
		} while (resposta == -1);
		if (resposta == 0) {
			PrefeitoDAO prefeitoDao = new PrefeitoDAO();
			prefeitoDao.deletar();
		}
		return ((resposta == 0) ? true : false);
	}

	public static boolean validaCadastrarOutraCidade() {
		int resposta;
		do {
			resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outra cidade?", "Sair",
					JOptionPane.YES_NO_OPTION);
		} while (resposta == -1);
		if (resposta == 0) {
			PrefeitoDAO prefeitoDao = new PrefeitoDAO();
			prefeitoDao.deletar();
		}
		return ((resposta == 0) ? true : false);
	}
}
