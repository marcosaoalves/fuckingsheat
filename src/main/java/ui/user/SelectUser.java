package ui.user;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import svc.command.user.UserCommand;
import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import ui.common.JToolBar;
import util.TextProperties;

public class SelectUser extends JPanel {
	private JToolBar toolBar;
	/**
	 * Create the panel.
	 */
	public SelectUser(Observer observer) {
		super(observer);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		toolBar = new JToolBar();
		add(toolBar);

		JButton btnBtncancel = new JButton(TextProperties.getInstance().getProperty(
				"app.btn.cancel"));
		btnBtncancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				observable.changeData(MessageObservable.BACK_PANEL, null);
			}
		});
		toolBar.add(btnBtncancel);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
				observable.changeData(MessageObservable.EDIT_USER,
						((JList) e.getSource()).getSelectedValue());
			}
		});

		panel.add(lstUsers);

	}
}
