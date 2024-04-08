package view;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Finestra extends JFrame {

	private static final long serialVersionUID = 1L;
	private Pannello contentPane;

	public Finestra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new Pannello();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
