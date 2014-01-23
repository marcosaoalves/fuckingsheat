package ui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ui.user.AddEditUser;
import ui.user.SelectUser;
import util.TextProperties;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
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
		initialize();
	}

	public void error(String message){
		ErrorPanel panel = new ErrorPanel(message);
		panel.getBtnBtnerror().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.getContentPane().remove(0);
				frame.getContentPane().revalidate();
			}
		});
		frame.getContentPane().add(panel);
		frame.getContentPane().revalidate();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(
				UIManager.getColor("Button.darkShadow"));
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		frame.setBackground(UIManager.getColor("Button.darkShadow"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnUsuario = new JMenu(TextProperties.getInstance().getProperty("app.menu.user"));
		menuBar.add(mnUsuario);

		JMenuItem mntmAdicionar = new JMenuItem(TextProperties.getInstance().getProperty("app.menu.user.add"));
		mntmAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AddEditUser panel = new AddEditUser();
				panel.getBtnBtncancel().addMouseListener(new CancelMouseAdapter(frame));
				frame.getContentPane().remove(0);
				frame.getContentPane().add(panel);
				frame.getContentPane().revalidate();
			}
		});
		mnUsuario.add(mntmAdicionar);

		JMenuItem mntmConsultar = new JMenuItem(TextProperties.getInstance().getProperty("app.menu.user.select"));
		mntmConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SelectUser panel = new SelectUser();
				panel.getBtnBtncancel().addMouseListener(new CancelMouseAdapter(frame));
				frame.getContentPane().remove(0);
				frame.getContentPane().add(panel);
				frame.getContentPane().revalidate();
			}
		});
		mnUsuario.add(mntmConsultar);
	}
}

class CancelMouseAdapter extends MouseAdapter {
	private JFrame frame;

	public CancelMouseAdapter(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		frame.getContentPane().remove(0);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		frame.getContentPane().revalidate();
	}
}