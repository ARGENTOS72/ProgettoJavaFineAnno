package controller;

@SuppressWarnings("serial")
public class ControllerException extends RuntimeException {
	public ControllerException() {
		super();
	}
	
	public ControllerException(String s) {
		super(s);
	}
}
