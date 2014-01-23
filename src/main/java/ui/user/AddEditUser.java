package ui.user;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;

import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import util.TextProperties;
import delegate.user.UserCommand;

public class AddEditUser extends JPanel {
	private JTextField txtTxtname;
	private JTextField txtTxtemail;
	private JTextField txtTxtusername;
	private JPasswordField pwdTxtpass;
	private JPasswordField pwdTxtpassagain;
	private JList lstTela;
	private JButton btnBtncancel;

	/**
	 * Create the panel.
	 */
	public AddEditUser(Observer observer) {
		MessageObservable observable = new MessageObservable();
		observable.addObserver(observer);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JToolBar toolBar = new JToolBar();
		add(toolBar);

		JButton btnBtnsave = new JButton(TextProperties.getInstance()
				.getProperty("app.btn.save"));
		btnBtnsave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				UserCommand command = new UserCommand();
				try {
					command.save(txtTxtname.getText(), txtTxtemail.getText(),
							txtTxtusername.getText(), pwdTxtpass.getText(),
							pwdTxtpassagain.getText(),
							lstTela.getSelectedValuesList());

					// TODO Implementar tratamento de erro e fechar tela.

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnBtnsave);

		btnBtncancel = new JButton(TextProperties.getInstance().getProperty(
				"app.btn.cancel"));
		toolBar.add(btnBtncancel);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel lblLnlname = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.name"));
		panel.add(lblLnlname);

		txtTxtname = new JTextField();
		panel.add(txtTxtname);
		txtTxtname.setColumns(10);

		JLabel lblLblemail = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.email"));
		panel.add(lblLblemail);

		txtTxtemail = new JTextField();
		panel.add(txtTxtemail);
		txtTxtemail.setColumns(10);

		JLabel lblLblusername = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.username"));
		panel.add(lblLblusername);

		txtTxtusername = new JTextField();
		panel.add(txtTxtusername);
		txtTxtusername.setColumns(10);

		JLabel lblLblpassword = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.pass"));
		panel.add(lblLblpassword);

		pwdTxtpass = new JPasswordField();
		panel.add(pwdTxtpass);

		JLabel lblLblpassagain = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.passagain"));
		panel.add(lblLblpassagain);

		pwdTxtpassagain = new JPasswordField();
		panel.add(pwdTxtpassagain);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 10));

		lstTela = new JList();
		panel_2.add(lstTela);
		lstTela.setModel(new AbstractListModel() {
			String[] values = new String[] {
					TextProperties.getInstance().getProperty("app.menu.user"),
					TextProperties.getInstance()
							.getProperty("app.menu.produto") };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

	}

	public AddEditUser(Observer observer, String user) {
		this(observer);
		if (user != null) {
			UserCommand command = new UserCommand();
			String[] ret = command.getUser(user);
			txtTxtname.setText(ret[2]);
			txtTxtemail.setText(ret[1]);
			txtTxtusername.setText(ret[0]);

			String[] ret2 = command.getAccess(user);
			if (ret2 != null) {
				ListModel<String> list = lstTela.getModel();
				int[] indices = new int[ret2.length];
				int h = 0;
				for (int i = 0; i < list.getSize(); i++) {
					for (int j = 0; j < ret2.length; j++) {
						if (ret2[j].equalsIgnoreCase(list.getElementAt(i))) {
							indices[h++] = i;
						}
					}
				}
				lstTela.setSelectedIndices(indices);
			}
		}
	}

	public JButton getBtnBtncancel() {
		return btnBtncancel;
	}

	public void setBtnBtncancel(JButton btnBtncancel) {
		this.btnBtncancel = btnBtncancel;
	}

}
