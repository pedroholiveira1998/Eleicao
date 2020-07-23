package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Cidade;
import dao.PrefeitoDAO;
import servicos.Validacao;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaConsultar extends JFrame {

	private JPanel contentPane;
	private JTextField textNumeroLegenda;
	private JButton btnBuscar;
	private JButton btnVoltar;

	public TelaConsultar(Cidade eleicao) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Validacao.validaCadastrarOutraCidade()) {
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
		setTitle("Consulta por legenda");
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));

		JLabel lblNewLabel = new JLabel("Consultar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblNewLabel.setBounds(130, 11, 247, 47);
		contentPane.add(lblNewLabel);

		textNumeroLegenda = new JTextField();
		textNumeroLegenda.setToolTipText("Digite o número da legenda.");
		textNumeroLegenda.setBounds(50, 113, 416, 20);
		contentPane.add(textNumeroLegenda);
		textNumeroLegenda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrefeitoDAO prefeitoDao = new PrefeitoDAO();
				try {
					Integer numeroLegenda = Integer.parseInt(textNumeroLegenda.getText());
					eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
					eleicao.setPrefeitos(prefeitoDao.consultar(numeroLegenda));
					if (eleicao.getPrefeitos().get(0).getNumeroLegenda() == 0) {
						JOptionPane.showMessageDialog(null, "Nenhum candidado encontrado!", "Falha",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Nome: " + eleicao.getPrefeitos().get(0).getNome().toString().toUpperCase()
										+ "\nPartido: " + eleicao.getPrefeitos().get(0).getPartidoAssociado().trim()
										+ "\nLegenda: " + eleicao.getPrefeitos().get(0).getNumeroLegenda(),
								eleicao.getNomeCidade().toString(), JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro! Digite novamente o número da legenda");
				}
				eleicao.getPrefeitos().removeAll(eleicao.getPrefeitos());
			}
		});
		btnBuscar.setBounds(205, 144, 89, 23);
		contentPane.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal(eleicao);
				telaMenuPrincipal.setVisible(true);
			}
		});
		btnVoltar.setBounds(415, 227, 89, 23);
		contentPane.add(btnVoltar);
	}
}
