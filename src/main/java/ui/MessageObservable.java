package ui;

import java.util.Observable;

import ui.common.JPanel;

public class MessageObservable extends Observable {
	public static final int ERROR = 0;
	public static final int EDIT_USER = 1;
	public static final int LOGIN = 2;
	public static final int BACK_PANEL = 3;
	public static final int MESSAGE = 4;

	private int action;
	private JPanel backPanel;

	public MessageObservable() {
		super();
	}

	public void changeData(int action, Object data) {
		setChanged();
		this.action = action;
		notifyObservers(data);
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public JPanel getBackPanel() {
		return backPanel;
	}

	public void setBackPanel(JPanel backPanel) {
		this.backPanel = backPanel;
	}

}
