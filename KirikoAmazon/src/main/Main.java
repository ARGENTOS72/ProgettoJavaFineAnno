package main;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.Finestra;

public class Main {

	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static int width, height;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Finestra f = new Finestra();
		
	}

}
