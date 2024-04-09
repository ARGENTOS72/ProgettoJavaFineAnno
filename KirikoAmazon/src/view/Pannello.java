package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pannello extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField barraDiRicerca;

	public Pannello() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		barraDiRicerca = new JTextField();
		add(barraDiRicerca);
	}
}
