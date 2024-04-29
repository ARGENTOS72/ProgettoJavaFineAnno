package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class HomePage {
	private Prodotto[][] prodotti;
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
		this.prodottoWidth = screenWidth/5;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		prodotti = new Prodotto[2][5];
		
		texture = new Texture2D("textures/SearchIcon.png");
		
		Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", 0);
		
		for (int i = 0; i < 5; i++) {
    		prodotti[0][i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 20, 15, 10, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
		}
		
		GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
				(prodottoConsigliatoY+prodottoConsigliatoHeight+screenHeight/30), 10, screenHeight/30);
		
//		int g=0;
//        for (int i = 0; i < 2; i++) {
//        	for(int j = 0; j < 5; j++) {
//        		prodotti[g].setLocation((i*prodottoWidth), (prodottoConsigliatoY+prodottoConsigliatoHeight)+(j*prodotti[g].getHeight()));
//        		g++;
//        	}
//        }
		
		
	}
    
    
    
    public void draw() {
    	 rShapes.DrawRectangleRec(new Rectangle(0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight), Color.GOLD);
    	 for (int i = 0; i < 5; i++) {
         	prodotti[0][i].draw();
		}
    }
    
    public void registraEventiHome(Controller c) {
    	/*for (int i = 0; i < 5; i++) {
        	prodotti[i].setName("prodotto"+(i+1));
        	prodotti[i].addListener(c);
        }*/
    }

    public int getHomePageHeight() {
		return prodottoConsigliatoHeight+(prodotti[0][0].getHeight()*2);
    	
    }
}
