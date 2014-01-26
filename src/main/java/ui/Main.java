package ui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import svc.command.user.UserCommand;
import ui.common.JPanel;
import ui.login.Login;
import ui.user.AddEditUser;
import ui.user.SelectUser;
import util.TextProperties;
import java.awt.Toolkit;

public class Main implements Observer {
	private String username;
	private JFrame frmMobilePartnersBrasil;
	private Main observer;
	private JPanel backPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMobilePartnersBrasil.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		observer = this;
		initLoginPanel();
	}

	private void error(String message) {
		changePanel(new ErrorPanel(observer, message));
	}
	
	private void message(String message) {
		changePanel(new MessagePanel(observer, message));
	}
	
	private void changePanel(JPanel panel){
		frmMobilePartnersBrasil.getContentPane().remove(0);
		frmMobilePartnersBrasil.getContentPane().add(panel);
		frmMobilePartnersBrasil.getContentPane().revalidate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {
		this.backPanel=null;
		JPanel panel = new JPanel();
		frmMobilePartnersBrasil.getContentPane().add(panel);
		frmMobilePartnersBrasil.setJMenuBar(buildMenus());
		frmMobilePartnersBrasil.revalidate();
	}

	private void initLoginPanel() {
		frmMobilePartnersBrasil = new JFrame();
		frmMobilePartnersBrasil.setTitle("Mobility Partners Brazil");
		frmMobilePartnersBrasil.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/logo.jpg")));
		frmMobilePartnersBrasil.getContentPane().setBackground(
				UIManager.getColor("Button.darkShadow"));
		frmMobilePartnersBrasil.getContentPane().setLayout(new CardLayout(0, 0));
		frmMobilePartnersBrasil.setExtendedState(frmMobilePartnersBrasil.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		Login panel = new Login(observer);
		frmMobilePartnersBrasil.getContentPane().add(panel);
		frmMobilePartnersBrasil.setBackground(UIManager.getColor("Button.darkShadow"));
		frmMobilePartnersBrasil.setBounds(100, 100, 450, 300);
		frmMobilePartnersBrasil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initAddEditUserPanel(String user) {
		changePanel(new AddEditUser(observer, user));
	}

	private void initSelectUserPanel() {
		changePanel(new SelectUser(observer));
	}

	public void update(Observable arg0, Object arg1) {
		switch (((MessageObservable) arg0).getAction()) {
		case MessageObservable.ERROR:
			error((String) arg1);
			break;
		case MessageObservable.MESSAGE:
			message((String) arg1);
			break;
		case MessageObservable.LOGIN:
			this.username = ((String) arg1);
			frmMobilePartnersBrasil.getContentPane().remove(0);
			init();
			((MessageObservable) arg0).setBackPanel(null);
			break;
		case MessageObservable.EDIT_USER:
			initAddEditUserPanel((String) arg1);
			break;
		case MessageObservable.BACK_PANEL:
			if(backPanel != null){
				changePanel(backPanel);
			}else{
				changePanel(new JPanel());
			}
			((MessageObservable) arg0).setBackPanel(null);
			break;
		}
		
		this.backPanel = ((MessageObservable) arg0).getBackPanel();
	}
	
	private JMenuBar buildMenus(){
		JMenuBar menuBar = new JMenuBar();
		
		String[] access = (new UserCommand()).getAccess(username);
		List<String> listAccess = Arrays.asList(access);
		
		if (listAccess.contains(TextProperties.getInstance().getProperty("app.menu.user"))){
			JMenu mnUsuario = new JMenu(TextProperties.getInstance().getProperty(
					"app.menu.user"));
			menuBar.add(mnUsuario);
			JMenu mnProduct = new JMenu(TextProperties.getInstance().getProperty(
					"app.menu.product"));
			menuBar.add(mnProduct);
		
			JMenuItem mntmAdicionar = new JMenuItem(TextProperties.getInstance()
					.getProperty("app.menu.user.add"));
			mntmAdicionar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					initAddEditUserPanel(null);
				}
			});
			mnUsuario.add(mntmAdicionar);

			JMenuItem mntmConsultar = new JMenuItem(TextProperties.getInstance()
					.getProperty("app.menu.user.select"));
			mntmConsultar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					initSelectUserPanel();
				}
			});
			mnUsuario.add(mntmConsultar);

			JMenuItem mnAddProduct = new JMenuItem(TextProperties.getInstance()
					.getProperty("app.menu.product.add"));
			mnAddProduct.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					initAddEditUserPanel(null);
				}
			});
			mnProduct.add(mnAddProduct);

			JMenuItem mnConsProduct = new JMenuItem(TextProperties.getInstance()
					.getProperty("app.menu.product.select"));
			mnConsProduct.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					initAddEditUserPanel(null);
				}
			});
			mnProduct.add(mnConsProduct);

		}
		
		return menuBar;
	}
}