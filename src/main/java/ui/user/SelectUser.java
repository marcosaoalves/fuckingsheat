package ui.user;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.ListModel;

import java.awt.CardLayout;
import javax.swing.JButton;

import delegate.user.UserCommand;

import util.TextProperties;

public class SelectUser extends JPanel {
	private JButton btnBtncancel;

	/**
	 * Create the panel.
	 */
	public SelectUser() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JToolBar toolBar = new JToolBar();
		add(toolBar);

		btnBtncancel = new JButton(TextProperties.getInstance().getProperty(
				"app.btn.cancel"));
		toolBar.add(btnBtncancel);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new CardLayout(10, 10));

		UserCommand command = new UserCommand();
		String[] users = command.getAllUsers();
		
		JList lstUsers = new JList();
		panel.add(lstUsers);

	}

	public JButton getBtnBtncancel() {
		return btnBtncancel;
	}

	public void setBtnBtncancel(JButton btnBtncancel) {
		this.btnBtncancel = btnBtncancel;
	}

}
