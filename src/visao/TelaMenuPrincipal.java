package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Cidade;
import servicos.Validacao;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class TelaMenuPrincipal extends JFrame {
	private JPanel contentPane;

	public TelaMenuPrincipal(Cidade eleicao) {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (TelaCadastroCidade.umCadastro == false) {
					JOptionPane.showMessageDialog(null, "Você deve realizar ao menos um cadastro!", "Erro ao sair",
							JOptionPane.ERROR_MESSAGE);
				} else if (Validacao.validaCadastrarOutraCidade()) {
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
		setTitle("Menu principal");
		setBounds(100, 100, 632, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCadastrar.setBounds(20, 185, 50, 50);
		contentPane.add(lblCadastrar);
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setToolTipText("Cadastrar um candidato.");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCadastrar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/imagem/cadastrar.png")));
		btnCadastrar.setBounds(20, 70, 128, 128);
		contentPane.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro(eleicao);
				telaCadastro.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblListar = new JLabel("Listar");
		lblListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListar.setBounds(168, 185, 50, 50);
		contentPane.add(lblListar);
		JButton btnListar = new JButton("Listar");
		btnListar.setToolTipText("Listar todos os condidatos por ordem de legenda.");
		btnListar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/imagem/listar.png")));
		btnListar.setBounds(168, 70, 128, 128);
		contentPane.add(btnListar);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validacao.minimoCadastro(eleicao)) {
					TelaPesquisar.pesquisa = false;
					TelaListar telaListar = new TelaListar(eleicao);
					telaListar.setVisible(true);
					dispose();
				}
			}
		});
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConsultar.setBounds(316, 185, 50, 50);
		contentPane.add(lblConsultar);
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setToolTipText("Consultar um candidato pelo número do partido.");
		btnConsultar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/imagem/consultar.png")));
		btnConsultar.setBounds(316, 70, 128, 128);
		contentPane.add(btnConsultar);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validacao.minimoCadastro(eleicao)) {
					TelaConsultar telaConsultar = new TelaConsultar(eleicao);
					telaConsultar.setVisible(true);
					dispose();
				}
			}
		});
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisar.setBounds(464, 185, 50, 50);
		contentPane.add(lblPesquisar);
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setToolTipText("Pesquisar candidatos por nome.");
		btnPesquisar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/imagem/pesquisar.png")));
		btnPesquisar.setBounds(464, 70, 128, 128);
		contentPane.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validacao.minimoCadastro(eleicao)) {
					TelaPesquisar telaPesquisar = new TelaPesquisar(eleicao);
					telaPesquisar.setVisible(true);
					dispose();
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(215, 0, 300, 50);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblSair = new JLabel("Sair");
		lblSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSair.setBounds(492, 330, 50, 50);
		contentPane.add(lblSair);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		JButton btnSair = new JButton("Sair");
		btnSair.setToolTipText("Sair.");
		btnSair.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/imagem/sair.png")));
		btnSair.setBounds(492, 246, 100, 100);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	}
}
