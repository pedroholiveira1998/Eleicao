package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Cidade;
import dados.Prefeito;
import dao.PrefeitoDAO;
import servicos.Validacao;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textNomeCandidato;
	private JLabel lblNmeroDaLegenda;
	private JTextField textNomePartido;
	private JTextField textNumeroLegenda;

	private JButton btnVoltar;

	public TelaCadastro(Cidade eleicao) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (TelaCadastroCidade.umCadastro == false) {
					JOptionPane.showMessageDialog(null, "Você deve realizar ao menos um cadastro!", "Erro ao sair",
							JOptionPane.ERROR_MESSAGE);
				} else if (Validacao.validaCadastrarOutraCidade()) {
					// eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
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
		setTitle("Cadastro de candidatos a prefeito");
		setBounds(100, 100, 420, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 10, 83, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNomeDoPartido = new JLabel("Nome do partido:");
		lblNomeDoPartido.setBounds(10, 45, 116, 29);
		contentPane.add(lblNomeDoPartido);

		lblNmeroDaLegenda = new JLabel("N\u00FAmero da legenda:");
		lblNmeroDaLegenda.setBounds(10, 80, 126, 29);
		contentPane.add(lblNmeroDaLegenda);

		textNomeCandidato = new JTextField();
		textNomeCandidato.setToolTipText("O nome deve possuir mais de 3 caracteres.\n");
		textNomeCandidato.setBounds(139, 15, 244, 20);
		contentPane.add(textNomeCandidato);
		textNomeCandidato.setColumns(10);

		textNomePartido = new JTextField();
		textNomePartido.setToolTipText(
				"O nome do partido não pode ser repetido, não deve ter caracteres especiais e não deve ter espaços");
		textNomePartido.setColumns(10);
		textNomePartido.setBounds(139, 50, 244, 20);
		contentPane.add(textNomePartido);

		textNumeroLegenda = new JTextField();
		textNumeroLegenda.setToolTipText("O numero da legenda não pode ser repetido.");
		textNumeroLegenda.setColumns(10);
		textNumeroLegenda.setBounds(139, 85, 244, 20);
		contentPane.add(textNumeroLegenda);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(267, 132, 116, 23);
		contentPane.add(btnCadastrar);

		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PrefeitoDAO prefeitoDao = new PrefeitoDAO();
				String nome = textNomeCandidato.getText().trim();
				String partidoAssociado = textNomePartido.getText().trim();
				String numeroLegenda = textNumeroLegenda.getText().trim();
				prefeitoDao.retornarLista(eleicao);

				if (Validacao.validaNomePrefeito(nome) && Validacao.validaPartidoAssociado(partidoAssociado, eleicao)
						&& Validacao.validaNumeroLegenda(numeroLegenda, eleicao)) {

					Prefeito prefeito = new Prefeito(nome, partidoAssociado, Integer.parseInt(numeroLegenda));
					prefeitoDao.cadastrar(prefeito);
					JOptionPane.showMessageDialog(null, "Cadastrado feito com sucesso!");
					TelaCadastroCidade.umCadastro = true;
					textNomeCandidato.setText("");
					textNomePartido.setText("");
					textNumeroLegenda.setText("");
				}
			}
		});

		getRootPane().setDefaultButton(btnCadastrar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
				dispose();
				TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal(eleicao);
				telaMenuPrincipal.setVisible(true);
			}
		});
		btnVoltar.setBounds(10, 177, 89, 23);
		contentPane.add(btnVoltar);
	}

}
