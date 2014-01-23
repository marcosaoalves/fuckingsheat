package ui.user;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import delegate.user.UserCommand;

import util.TextProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	public AddEditUser() {
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
							pwdTxtpassagain.getText(), lstTela.getSelectedValuesList());

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
		txtTxtname.setText("txtName");
		panel.add(txtTxtname);
		txtTxtname.setColumns(10);

		JLabel lblLblemail = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.email"));
		panel.add(lblLblemail);

		txtTxtemail = new JTextField();
		txtTxtemail.setText("txtEmail");
		panel.add(txtTxtemail);
		txtTxtemail.setColumns(10);

		JLabel lblLblusername = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.username"));
		panel.add(lblLblusername);

		txtTxtusername = new JTextField();
		txtTxtusername.setText("txtUsername");
		panel.add(txtTxtusername);
		txtTxtusername.setColumns(10);

		JLabel lblLblpassword = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.pass"));
		panel.add(lblLblpassword);

		pwdTxtpass = new JPasswordField();
		pwdTxtpass.setText("txtPass");
		panel.add(pwdTxtpass);

		JLabel lblLblpassagain = new JLabel(TextProperties.getInstance()
				.getProperty("user.addedit.passagain"));
		panel.add(lblLblpassagain);

		pwdTxtpassagain = new JPasswordField();
		pwdTxtpassagain.setText("txtPassAgain");
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

	public JButton getBtnBtncancel() {
		return btnBtncancel;
	}

	public void setBtnBtncancel(JButton btnBtncancel) {
		this.btnBtncancel = btnBtncancel;
	}

}
