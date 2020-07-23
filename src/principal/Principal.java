package principal;

import java.awt.EventQueue;
import java.awt.Toolkit;

import visao.TelaInicial;

public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icone.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
