package view;

import java.net.CookieHandler;

import com.raylib.java.core.Color;

import controller.Controller;

public class Foot {
	private int footY;
	private TextButton txtBtn;
	private int screenWidth;
    private int screenHeight;
    private int footHeight;
	private int padding;
	
	public Foot(int footY, int screenWidth, int screenHeight) {
		super();
		this.footY = footY;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.footHeight = screenHeight/2;
		this.padding = screenWidth/20;
		
		txtBtn = new TextButton(0, footY, true, 0f, Color.VIOLET, new Color(87, 10, 142, 255),
        		Color.PINK, 10, 40, "Torna in testa", Color.WHITE, Color.WHITE, new Color(87, 10, 142, 255));
		txtBtn.setLocation(screenWidth-(txtBtn.getWidth()+padding), footY+footHeight-(padding));
		System.out.println("Viola A "+Color.PINK.getA()+" R "+Color.PINK.getR()+"G"+Color.PINK.getG()+"B"+Color.PINK.getB());
	}

	public void draw() {
		Finestra.getRaylib().shapes.DrawRectangle(0, footY, screenWidth, footHeight, Color.PINK);
		Finestra.getRaylib().text.DrawText("Kirizon", padding, footY+padding , 50, Color.WHITE);
		Finestra.getRaylib().shapes.DrawRectangle(padding, footY+padding+(padding/2), screenWidth-(padding*2), screenHeight/200, Color.VIOLET);
		Finestra.getRaylib().text.DrawText("Gabriele Bardin", padding, footY+(padding*2), 30, Color.WHITE);
		Finestra.getRaylib().text.DrawText("Tomas Matteo Maceira Maurino", padding, footY+(padding*3), 30, Color.WHITE);
		Finestra.getRaylib().text.DrawText("Giacomo Pasqualini", padding,  footY+(padding*4), 30, Color.WHITE);
		txtBtn.draw();
	}
	
	public void registraEventiFoot(Controller c) {
		txtBtn.setName("testa");
		txtBtn.addListener(c);
	}
}
