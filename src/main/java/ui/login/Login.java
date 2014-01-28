package ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import svc.command.user.UserCommand;
import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import util.FileProperties;
import util.TextProperties;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtUsername;
	private JTextField txtPass;

	/**
	 * Create the panel.
	 */
	public Login(Observer observer) {
		super(observer);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setPreferredSize(new Dimension(10, 200));
		add(panel_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(10, 10));
		panel_7.setBackground(new Color(244, 164, 96));
		add(panel_7);
		
		JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
		panel.setBackground(Color.WHITE);
		add(panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3, gbc);
		
		JLabel lblLogo = new JLabel((Icon)null);
		try {
			BufferedImage myPicture = ImageIO.read(FileProperties.getInstance().getResourceFile("img/logo.jpg"));
			lblLogo.setIcon(new ImageIcon(myPicture));
		} catch (IOException e1) {
			e1.printStackTrace();
			observable.changeData(MessageObservable.ERROR, e1.getMessage());
		}
		panel_3.add(lblLogo);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4);
		BoxLayout layout = new BoxLayout(panel_4, BoxLayout.Y_AXIS);
		panel_4.setLayout(layout);
		
		JLabel lblUsername = new JLabel(TextProperties.getInstance().getProperty("login.user"));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER){
					UserCommand command = new UserCommand();
					if(command.validateUser(txtUsername.getText(), txtPass.getText())){
						observable.changeData(MessageObservable.LOGIN, txtUsername.getText());
					}else{
						observable.changeData(MessageObservable.ERROR, TextProperties.getInstance().getProperty("login.error.loginerror"));
					}
				}
			}
		});
		txtUsername.setPreferredSize(new Dimension(100, 20));
		panel_4.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel(TextProperties.getInstance().getProperty("login.pass"));
		panel_4.add(lblPassword);
		
		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER){
					UserCommand command = new UserCommand();
					if(command.validateUser(txtUsername.getText(), txtPass.getText())){
						observable.changeData(MessageObservable.LOGIN, txtUsername.getText());
					}else{
						observable.changeData(MessageObservable.ERROR, TextProperties.getInstance().getProperty("login.error.loginerror"));
					}
				}
			}
		});
		panel_4.add(txtPass);
		txtPass.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);
		
		JButton btnLogin = new JButton(TextProperties.getInstance().getProperty("login.btnlogin"));
		btnLogin.setPreferredSize(new Dimension(80, 25));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				UserCommand command = new UserCommand();
				if(command.validateUser(txtUsername.getText(), txtPass.getText())){
					observable.changeData(MessageObservable.LOGIN, txtUsername.getText());
				}else{
					observable.changeData(MessageObservable.ERROR, TextProperties.getInstance().getProperty("login.error.loginerror"));
				}
			}
		});
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_5.add(btnLogin);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(244, 164, 96));
		panel_6.setPreferredSize(new Dimension(10, 10));
		add(panel_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setPreferredSize(new Dimension(10, 200));
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
	}
}
