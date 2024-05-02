package view;

import java.util.ArrayList;
import java.util.Arrays;

import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class ProductsSearched {
	private Prodotto[][] prodotti;
	private int screenWidth;
    private int screenHeight;
    private Texture2D texture;
    private int nProdotti;
    private int headerHeight;
	private int padding;
    private int prodottoWidth;
    private int footY;
    private Foot foot;
	
    public ProductsSearched(int headerHeight, int screenWidth, int screenHeight, int nProdotti) {
		super();
		this.headerHeight = headerHeight;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.nProdotti = nProdotti;
		this.prodottoWidth = screenWidth/5;
		this.texture = new Texture2D("textures/SearchIcon.png");
		this.padding = (screenHeight/20);
		String[] categorieTemp = {"Test"};
		Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", new ArrayList<String>(Arrays.asList(categorieTemp)), 0);
		//Matrix creation
		Prodotto[] p1 = new Prodotto[(int) Math.ceil((float)(nProdotti)/2)];
		Prodotto[] p2 = new Prodotto[(int) Math.floor((float)(nProdotti)/2)];
		
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 40, 30, 50, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
		}
		
		for (int i = 0; i < p2.length; i++) {
			p2[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 40, 30, 50, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
		}
		
		Prodotto temp[][] = new Prodotto[][] {p1, p2};
		
		prodotti = temp;
		
		GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
				(headerHeight+(padding*2)), 10, padding);
		
		for(int i = 0; i < prodotti[1].length; i++) {
				prodotti[1][i].setY(prodotti[0][i].getY());
				GraphicComponentAligner.alignX(new GraphicComponent[] {prodotti[0][i], prodotti[1][i]},
					GraphicComponentAligner.CENTER, GraphicComponentAligner.DISTRIBUTE, 0, screenWidth,-1);
		}
		
		if(nProdotti %2 != 0) prodotti[0][prodotti[0].length-1].setX(prodotti[0][0].getX());
		
		footY += (headerHeight+(padding*2)) + ((prodotti[0][0].getHeight()+(padding*2))*prodotti[0].length);
		foot = new Foot(footY, screenWidth, screenHeight);
	}
    
    public void draw() {
    	for (int i = 0; i < 2; i++) {
     		for(int j = 0; j < prodotti[i].length; j++) {
     			if(prodotti[i][j]!=null) {
     				prodotti[i][j].draw();
     			}
     		}
         }
    	foot.draw();
    }
    
    public void registraEventiProductsSearched(Controller c) {
    	for (int i = 0; i < 2; i++) {
    		for(int j = 0; j < prodotti[i].length; j++) {
    			if(prodotti[i][j]!=null) {
    				prodotti[i][j].setName("prodotto"+(i+1));
    				prodotti[i][j].addListener(c);
     			}
    		}
        }
    	foot.registraEventiFoot(c);
    }
    
}
