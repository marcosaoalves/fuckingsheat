package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.common.JButton;
import ui.common.JPanel;
import util.FileProperties;
import util.TextProperties;

public class MessagePanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public MessagePanel(Observer observer, String error) {
		super(observer);

		setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(panel, gbc);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		javax.swing.JPanel panel_3 = new javax.swing.JPanel();
		panel.add(panel_3);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(FileProperties.getInstance().getResourceFile("img/message.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel lblImage = new JLabel(new ImageIcon(myPicture));
		panel_3.add(lblImage);
		lblImage.setPreferredSize(new Dimension(100,100));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		javax.swing.JPanel panel_2 = new javax.swing.JPanel();
		panel.add(panel_2);
		
		JLabel lblLblerror = new JLabel(error);
		panel_2.add(lblLblerror);
		lblLblerror.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(panel_1, BorderLayout.SOUTH);

		JButton btnBtnerror = new JButton(TextProperties.getInstance().getProperty(
				"app.message.button"));
		panel_1.add(btnBtnerror);
		
		btnBtnerror.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				observable.changeData(MessageObservable.BACK_PANEL, null);
			}
		});

	}
}
