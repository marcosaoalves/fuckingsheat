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
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import svc.command.user.UserCommand;
import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import ui.common.JToolBar;
import util.TextProperties;

public class AddEditUser extends JPanel {
	private JTextField txtTxtname;
	private JTextField txtTxtemail;
	private JTextField txtTxtusername;
	private JPasswordField pwdTxtpass;
	private JPasswordField pwdTxtpassagain;
	private JList lstTela;

	/**
	 * Create the panel.
	 * 
	 * @wbp.parser.constructor
	 */
	public AddEditUser(Observer observer) {
		super(observer);

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

					observable.setBackPanel(null);
					observable.changeData(
							MessageObservable.MESSAGE,
							TextProperties.getInstance().getProperty(
									"user.addedit.okmessage"));

				} catch (Exception e1) {
					observable.changeData(MessageObservable.ERROR,
							e1.getMessage());

				}
			}
		});
		toolBar.add(btnBtnsave);

		JButton btnBtncancel = new JButton(TextProperties.getInstance()
				.getProperty("app.btn.cancel"));
		btnBtncancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				observable.changeData(MessageObservable.BACK_PANEL, null);
			}
		});
		toolBar.add(btnBtncancel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

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
		panel_2.setLayout(new CardLayout(10, 10));

		lstTela = new JList();
		panel_2.add(lstTela, "name_948415222739");
		lstTela.setModel(new AbstractListModel() {
			String[] values = new String[] {
					TextProperties.getInstance().getProperty("app.menu.user"),
					TextProperties.getInstance().getProperty("app.menu.product") };

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
}
