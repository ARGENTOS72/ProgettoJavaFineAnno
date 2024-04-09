package model;

import java.util.concurrent.Semaphore;

public class Loader implements Runnable {
	public static final String init = "init";
	private Semaphore s;
	private Thread t;
	
	public Loader(String loadingType, Semaphore s) {
		t = new Thread(this, "Loader");
		this.s = s;
	}



	public void start() {
		t.start();
	}



	@Override
	public void run() {
		//to load
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//notify finish
		s.release();
	}
	
}
