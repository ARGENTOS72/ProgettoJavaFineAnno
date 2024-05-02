package view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
    private TextureButton prodottoConsigliato;
    
    private Texture2D texture;
	
    public HomePage(int screenWidth, int screenHeight, int prodottoConsigliatoY, int prodottoConsigliatoHeight) {
		super();
		this.prodottoConsigliatoY = prodottoConsigliatoY;
		this.prodottoConsigliatoHeight = prodottoConsigliatoHeight;
		this.prodottoWidth = screenWidth/3;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		prodotti = new Prodotto[2][5];
		
		texture = new Texture2D("textures/SearchIcon.png");

		prodottoConsigliato = new TextureButton(texture, 0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight, true, 0, 0, Color.LIGHTGRAY, Color.GRAY, Color.BLANK);
		
		Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", 0);
		
		for (int i = 0; i < 2; i++)
			for(int j = 0; j < 5; j++)
				prodotti[i][j] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 20, 15, 10, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
		
		GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
				(prodottoConsigliatoY+prodottoConsigliatoHeight+screenHeight/20), 10, screenHeight/20);
		
		for(int i = 0; i < 5; i++) {
			prodotti[1][i].setY(prodotti[0][i].getY());
			GraphicComponentAligner.alignX(new GraphicComponent[] {prodotti[0][i], prodotti[1][i]},
				GraphicComponentAligner.CENTER, GraphicComponentAligner.DISTRIBUTE, 0, screenWidth,-1);
		}
	}
    
    public void draw() {
    	prodottoConsigliato.draw();
    	for (int i = 0; i < 2; i++) {
     		for(int j = 0; j < 5; j++) {
 	        	prodotti[i][j].draw();
     		}
         }
    }
    
    public void registraEventiHome(Controller c) {
    	prodottoConsigliato.setName("ProdottoConsigliato");
    	prodottoConsigliato.addListener(c);
    	
    	for (int i = 0; i < 2; i++) {
    		for(int j = 0; j < 5; j++) {
	        	prodotti[i][j].setName("prodotto"+(i+1));
				prodotti[i][j].addListener(c);
    		}
        }
    }

    public int getHomePageHeight() {
		return prodottoConsigliatoHeight;
    }

	public int getProdottoConsigliatoY() {
		return prodottoConsigliatoY;
	}

	public int getProdottoConsigliatoHeight() {
		return prodottoConsigliatoHeight;
	}

	public int getProdottoWidth() {
		return prodottoWidth;
	}
}
