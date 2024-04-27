package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class HomePage {
	private Prodotto[] prodotti;
	private int prodottoConsigliatoY;
    private int prodottoConsigliatoHeight;
    private int prodottoWidth;
    private int screenWidth;
    private int screenHeight;
    
    private Texture2D texture;
	
    public HomePage(int screenWidth, int screenHeight, int prodottoConsigliatoY, int prodottoConsigliatoHeight) {
		super();
		this.prodottoConsigliatoY = prodottoConsigliatoY;
		this.prodottoConsigliatoHeight = prodottoConsigliatoHeight;
		this.prodottoWidth = screenWidth/4;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		prodotti = new Prodotto[8];
		
		texture = new Texture2D("textures/SearchIcon.png");
		
		Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", 0);
		
		for (int i = 0; i < 8; i++) {
    		prodotti[i] = new Prodotto(0, 0, prodottoWidth, 0f, texture, pp, 20, 15, 10, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
		}
		
		int g=0;
        for (int i = 0; i < 4; i++) {
        	for(int j = 0; j < 2; j++) {
        		prodotti[g].setLocation((i*prodottoWidth), (prodottoConsigliatoY+prodottoConsigliatoHeight)+(j*prodotti[g].getHeight()));
        		g++;
        	}
        }
	}
    
    public void draw() {
    	 rShapes.DrawRectangleRec(new Rectangle(0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight), Color.GOLD);
    	 for (int i = 0; i < 8; i++) {
         	prodotti[i].draw();
		}
    }
    
    public void registraEventiHome(Controller c) {
    	for (int i = 0; i < 8; i++) {
        	prodotti[i].setName("prodotto"+(i+1));
        	prodotti[i].addListener(c);
        }
    }

    public int getHomePageHeight() {
		return prodottoConsigliatoHeight+(prodotti[0].getHeight()*2);
    	
    }
}
