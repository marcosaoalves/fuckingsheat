package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.TextProperties;

public class ErrorPanel extends JPanel {
	private JButton btnBtnerror;

	/**
	 * Create the panel.
	 */
	public ErrorPanel(String error) {

		JLabel lblLblerror = new JLabel(error);
		add(lblLblerror);

		btnBtnerror = new JButton(TextProperties.getInstance().getProperty(
				"app.error.button"));
		add(btnBtnerror);

	}

	public JButton getBtnBtnerror() {
		return btnBtnerror;
	}

	public void setBtnBtnerror(JButton btnBtnerror) {
		this.btnBtnerror = btnBtnerror;
	}

}
