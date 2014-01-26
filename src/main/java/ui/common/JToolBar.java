package ui.common;

import javax.swing.border.EmptyBorder;

public class JToolBar extends javax.swing.JToolBar {

	/**
	 * @param args
	 */
	public JToolBar(){
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setFloatable(false);
	}
}
