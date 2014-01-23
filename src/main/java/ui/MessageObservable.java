package ui;

import java.util.Observable;

public class MessageObservable extends Observable {
	public static final int EDIT_USER = 1;
	
	private int action;

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

}
