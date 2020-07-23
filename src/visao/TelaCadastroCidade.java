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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastroCidade extends JFrame {
	public static boolean umCadastro = false;
	private JPanel contentPane;
	private JTextField textNomeCidade;

	public TelaCadastroCidade() {
		PrefeitoDAO prefeitoDao = new PrefeitoDAO();
		if (prefeitoDao.VerificarBanco()) {
			int resposta;
			Object[] options = { "Continuar", "Apagar" };
			do {
				resposta = JOptionPane.showOptionDialog(null,
						"Por alguma razão ainda consta registros no banco de dados de candidatos anteriormente cadastrados.\nDeseja continuar com os registros antigos ou apaga-los?",
						"Alerta", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} while (resposta == -1);
			if (resposta == 0) {
				TelaCadastroCidade.umCadastro = true;
				JOptionPane.showMessageDialog(null,
						"Infelizmente o registro do nome da cidade foi perdido, mas todos os candidatos estão disponíveis e cadastrados.");
			} else {
				prefeitoDao.deletar();
			}
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (TelaCadastroCidade.umCadastro == false) {
					JOptionPane.showMessageDialog(null, "Você deve realizar ao menos um cadastro!", "Erro ao sair",
							JOptionPane.ERROR_MESSAGE);
				} else if (Validacao.validaSair()) {
					System.exit(0);
				}
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Cadastro de cidade");
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));

		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(TelaCadastroCidade.class.getResource("/imagem/parlamento.png")));
		lblImagem.setBounds(224, 0, 256, 256);
		contentPane.add(lblImagem);

		JLabel lblTitulo = new JLabel("Nome da cidade:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(10, 11, 256, 29);
		contentPane.add(lblTitulo);

		textNomeCidade = new JTextField();
		textNomeCidade.setBounds(10, 51, 238, 20);
		contentPane.add(textNomeCidade);
		textNomeCidade.setColumns(10);
		textNomeCidade.setToolTipText("O nome da cidade deve possuir mais de 3 caracteres.\n");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder str = new StringBuilder();
				StringBuilder nome = str.append(textNomeCidade.getText().toUpperCase());
				if (Validacao.validaNomeCidade(nome)) {
					Cidade eleicao = new Cidade(nome);
					dispose();
					TelaMenuPrincipal menu = new TelaMenuPrincipal(eleicao);
					menu.setVisible(true);
				}
			}
		});
		getRootPane().setDefaultButton(btnCadastrar);
		btnCadastrar.setBounds(10, 96, 108, 23);
		contentPane.add(btnCadastrar);

	}
}
