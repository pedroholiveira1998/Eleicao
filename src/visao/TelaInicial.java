package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import servicos.Validacao;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaInicial extends JFrame {

	private JPanel contentPane;

	public TelaInicial() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Validacao.validaSair()) {
					System.exit(0);
				}
			}
		});

		setTitle("Elei\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel("Elei\u00E7\u00E3o para prefeito");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bodoni MT Black", Font.PLAIN, 35));
		lblTitulo.setBounds(24, 11, 400, 68);
		contentPane.add(lblTitulo);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaCadastroCidade janelaCadastroCidade = new TelaCadastroCidade();
				janelaCadastroCidade.setVisible(true);
			}
		});
		btnIniciar.setBounds(125, 106, 164, 75);
		contentPane.add(btnIniciar);
		getRootPane().setDefaultButton(btnIniciar);

		JButton buttonSair = new JButton("Sair");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Validacao.validaSair()) {
					System.exit(0);
				}
			}
		});
		buttonSair.setBounds(136, 191, 141, 59);
		contentPane.add(buttonSair);
	}
}
