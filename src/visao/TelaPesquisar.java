package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Cidade;
import dao.PrefeitoDAO;
import servicos.Validacao;
import javax.swing.JLabel;

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
public class TelaPesquisar extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscar;
	private JButton btnBuscar;
	private JButton btnVoltar;
	public static boolean pesquisa;

	public TelaPesquisar(Cidade eleicao) {
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
		setTitle("Pesquisar");
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));

		JLabel lblTitulo = new JLabel("Pesquisar");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblTitulo.setBounds(130, 11, 247, 73);
		contentPane.add(lblTitulo);

		textBuscar = new JTextField();
		textBuscar.setToolTipText("Escreva as letras que deseja buscar");
		textBuscar.setBounds(50, 113, 416, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrefeitoDAO prefeitoDao = new PrefeitoDAO();
				prefeitoDao.retornarPesquisa(eleicao, textBuscar.getText());
				pesquisa = true;
				TelaListar telaListar = new TelaListar(eleicao);
				telaListar.listar(eleicao, 2);
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
