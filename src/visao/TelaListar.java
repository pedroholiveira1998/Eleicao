package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dados.Cidade;
import dao.PrefeitoDAO;
import ordenacao.OrdenaLegenda;
import ordenacao.OrdenaNome;
import servicos.Validacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TelaListar extends JFrame {

	private JPanel contentPane;
	private JTable tableCadastros;

	public TelaListar(Cidade eleicao) {
		PrefeitoDAO prefeitoDao = new PrefeitoDAO();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Validacao.validaCadastrarOutraCidade()) {
					eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
					TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
					telaCadastroCidade.setVisible(true);
					dispose();
				} else {
					if (Validacao.validaSair()) {
						System.exit(0);
					}
				}
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Lista de candidatos");
		setBounds(100, 100, 530, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 492, 230);
		contentPane.add(scrollPane);

		tableCadastros = new JTable();
		tableCadastros.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Partido", "Legenda" }));
		scrollPane.setViewportView(tableCadastros);
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(12, 12, 125, 22);
		contentPane.add(lblCidade);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!TelaPesquisar.pesquisa) {
					TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal(eleicao);
					telaMenuPrincipal.setVisible(true);
				}
				dispose();
			}
		});
		btnVoltar.setBounds(393, 317, 89, 23);
		contentPane.add(btnVoltar);

		JLabel lblNomeCidade = new JLabel(eleicao.getNomeCidade().toString());

		lblNomeCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomeCidade.setBounds(12, 45, 170, 28);
		contentPane.add(lblNomeCidade);

		if (!TelaPesquisar.pesquisa) {
			prefeitoDao.retornarLista(eleicao);
			listar(eleicao, 1);
		}
	}

	public void listar(Cidade eleicao, int opc) {
		int aux = 0;
		switch (opc) {
		case 1:
			OrdenaLegenda ordenaLegenda = new OrdenaLegenda();
			Collections.sort(eleicao.getPrefeitos(), ordenaLegenda);
			for (aux = 0; aux < eleicao.quantidadePrefeitos(); aux++) {
				Object dados[] = { eleicao.getPrefeitos().get(aux).getNome().toUpperCase(),
						eleicao.getPrefeitos().get(aux).getPartidoAssociado().toUpperCase(),
						eleicao.getPrefeitos().get(aux).getNumeroLegenda() };
				((DefaultTableModel) tableCadastros.getModel()).addRow(dados);
			}
			break;
		case 2:
			String str = "candidato";
			boolean encontrado = false;
			OrdenaNome ordenaNome = new OrdenaNome();
			Collections.sort(eleicao.getPrefeitos(), ordenaNome);
			for (aux = 0; aux < eleicao.quantidadePrefeitos(); aux++) {
				Object dados[] = { eleicao.getPrefeitos().get(aux).getNome().toUpperCase(),
						eleicao.getPrefeitos().get(aux).getPartidoAssociado().toUpperCase(),
						eleicao.getPrefeitos().get(aux).getNumeroLegenda() };
				((DefaultTableModel) tableCadastros.getModel()).addRow(dados);
				setVisible(true);
				encontrado = true;
			}
			if (encontrado == false) {
				JOptionPane.showMessageDialog(null, "Nenhum dado encontrado!", "Falha",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (eleicao.getPrefeitos().size() > 1) {
				str = "candidatos";
			}
			JLabel lblQuantidade = new JLabel(eleicao.getPrefeitos().size() + " " + str);
			lblQuantidade.setBounds(12, 317, 89, 23);
			contentPane.add(lblQuantidade);
		}
	}
}
