package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pannello extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public Pannello() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(43, 46, 967, 20);
		add(textField);
		textField.setColumns(10);
		
	}
}
