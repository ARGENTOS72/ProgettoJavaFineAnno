package view;

import com.raylib.java.core.Color;

import controller.Controller;

public class Footer {
	private int offsetY;
	private TextButton txtBtn;
	private int screenWidth;
    private int screenHeight;
    private int footHeight;
	private int padding;
	
	public Footer(int offsetY, int screenWidth, int screenHeight) {
		this.offsetY = offsetY;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.footHeight = screenHeight/2;
		this.padding = screenWidth/20;
		
		txtBtn = new TextButton(0, offsetY, true, 0f, Color.PINK, new Color(87, 10, 142, 255),
        		Color.PINK, 10, 40, "Torna in testa", Color.WHITE, Color.WHITE, new Color(87, 10, 142, 255));
		
		txtBtn.setWidth(screenWidth);
		
		txtBtn.setName("footer.gotoHead");
	}

	public void draw() {
		Finestra.getRaylib().shapes.DrawRectangle(0, offsetY, screenWidth, footHeight, new Color(204, 87, 155, 255));
		txtBtn.draw();
		Finestra.getRaylib().text.DrawText("Kirizon", padding, offsetY+padding , 50, Color.WHITE);
		Finestra.getRaylib().shapes.DrawRectangle(padding, offsetY+padding+(padding/2), screenWidth-(padding*2), screenHeight/200, new Color(87, 10, 142, 255));
		Finestra.getRaylib().text.DrawText("Gabriele Bardin", padding, offsetY+(padding*2), 30, Color.WHITE);
		Finestra.getRaylib().text.DrawText("Tomas Matteo Maceira Maurino", padding, offsetY+(padding*3), 30, Color.WHITE);
		Finestra.getRaylib().text.DrawText("Giacomo Pasqualini", padding,  offsetY+(padding*4), 30, Color.WHITE);
	}
	
	public void registraEventiFoot(Controller c) {
		txtBtn.addListener(c);
	}

	public void rimuoviEventiFoot(Controller c) {
		txtBtn.removeListener(c);
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public int getHeight() {
		return offsetY + footHeight;
	}
	
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
		
		//adjust graphics
		txtBtn.setY(offsetY);
	}
}
