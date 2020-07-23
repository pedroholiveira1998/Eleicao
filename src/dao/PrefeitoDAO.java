package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexao.Conexao;
import dados.Cidade;
import dados.Prefeito;

public class PrefeitoDAO {
	private Connection con = Conexao.getConnection();

	public void cadastrar(Prefeito prefeito) {

		String sql = "INSERT INTO javaVerao.prefeito (nome, partidoAssociado, numeroLegenda) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, prefeito.getNome());
			preparador.setString(2, prefeito.getPartidoAssociado());
			preparador.setInt(3, prefeito.getNumeroLegenda());
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
	}

	public Prefeito consultar(int partido) {
		String nome = "";
		String partidoAssociado = "";
		int numeroLegenda = 0;

		String sql = "SELECT * FROM prefeito WHERE numeroLegenda=?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, partido);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				nome = rs.getString("nome");
				partidoAssociado = rs.getString("partidoAssociado");
				numeroLegenda = Integer.parseInt(rs.getString("numeroLegenda"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
		Prefeito prefeito = new Prefeito(nome, partidoAssociado, numeroLegenda);
		return prefeito;
	}

	public void retornarLista(Cidade eleicao) {
		eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
		String sql = "SELECT * FROM prefeito";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Prefeito prefeito = new Prefeito(rs.getString(1), rs.getString(2), rs.getInt(3));
				eleicao.setPrefeitos(prefeito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void retornarPesquisa(Cidade eleicao, String pesquisa) {
		eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
		String sql = "SELECT * FROM prefeito WHERE nome LIKE ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + pesquisa + "%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Prefeito prefeito = new Prefeito(rs.getString(1), rs.getString(2), rs.getInt(3));
				eleicao.setPrefeitos(prefeito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
	}

	public boolean VerificarBanco() {
		String sql = "SELECT * FROM prefeito";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deletar() {
		String sql = "DELETE FROM prefeito";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conex�o.");
					System.out.println("Causa: " + e.getMessage());
				}
		}
	}
}
