package ui.common;
import java.util.Observer;

import ui.MessageObservable;


public class JPanel extends javax.swing.JPanel {
	protected final MessageObservable observable = new MessageObservable();
	protected final JPanel thisPanel = this;

	/**
	 * Create the panel.
	 */
	public JPanel() {
		observable.setBackPanel(thisPanel);
	}
	
	public JPanel(Observer observer){
		this();
		if(observer!= null){
			observable.addObserver(observer);	
		}
	}
}
