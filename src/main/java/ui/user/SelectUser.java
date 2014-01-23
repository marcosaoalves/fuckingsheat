package ui.user;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JToolBar;

import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import util.TextProperties;
import delegate.user.UserCommand;

public class SelectUser extends JPanel {
	private JButton btnBtncancel;

	/**
	 * Create the panel.
	 */
	public SelectUser(Observer observer) {
		final MessageObservable observable = new MessageObservable();
		observable.addObserver(observer);

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

		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < users.length; i++) {
			listModel.addElement(users[i]);
		}

		JList lstUsers = new JList(listModel);
		lstUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
                observable.changeData(MessageObservable.EDIT_USER, ((JList)e.getSource()).getSelectedValue());
			}
		});

		panel.add(lstUsers);

	}

	public JButton getBtnBtncancel() {
		return btnBtncancel;
	}

	public void setBtnBtncancel(JButton btnBtncancel) {
		this.btnBtncancel = btnBtncancel;
	}

}
