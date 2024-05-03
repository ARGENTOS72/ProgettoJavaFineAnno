package view;

import java.util.ArrayList;
import java.util.Arrays;

import com.raylib.java.core.Color;
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
    private int padding;
    private int footY;
    private Texture2D texture;
	private Foot foot;
    
    public HomePage(int screenWidth, int screenHeight, int prodottoConsigliatoY, int prodottoConsigliatoHeight, int nProdotti) {
		super();
		this.prodottoConsigliatoY = prodottoConsigliatoY;
		this.prodottoConsigliatoHeight = prodottoConsigliatoHeight;
		this.prodottoWidth = screenWidth/5;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		padding = screenHeight/20;
		this.texture = new Texture2D("textures/SearchIcon.png");
		prodotti = new Prodotto[2][5];
		
		texture = new Texture2D("textures/PlaceHolder.jpg");

		prodottoConsigliato = new TextureButton(texture, 0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight, true, 0, 0, Color.LIGHTGRAY, Color.GRAY, Color.BLANK);
		
		String[] categorieTemp = {"cacca", "culo"};
		ArrayList<String> categorie = new ArrayList<String>(Arrays.asList(categorieTemp));
		Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", categorie, 1);
		
		//Matrix creation
				Prodotto[] p1 = new Prodotto[(int) Math.ceil((float)(nProdotti)/2)];
				Prodotto[] p2 = new Prodotto[(int) Math.floor((float)(nProdotti)/2)];
				
				for (int i = 0; i < p1.length; i++) {
					p1[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
				}
				
				for (int i = 0; i < p2.length; i++) {
					p2[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, pp, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
				}
				
				Prodotto temp[][] = new Prodotto[][] {p1, p2};
				
				prodotti = temp;
				
				GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
						(prodottoConsigliatoY+prodottoConsigliatoHeight+(padding*2)), 10, padding);
				
				for(int i = 0; i < prodotti[1].length; i++) {
						prodotti[1][i].setY(prodotti[0][i].getY());
						GraphicComponentAligner.alignX(new GraphicComponent[] {prodotti[0][i], prodotti[1][i]},
							GraphicComponentAligner.CENTER, GraphicComponentAligner.DISTRIBUTE, 0, screenWidth,-1);
				}
				
				if(nProdotti %2 != 0) prodotti[0][prodotti[0].length-1].setX(prodotti[0][0].getX());
		
		footY += (prodottoConsigliatoY+prodottoConsigliatoHeight+(padding*2)) + ((prodotti[0][0].getHeight()+(padding*2))*prodotti[0].length);
		foot = new Foot(footY, screenWidth, screenHeight);
	}
    
    public void draw() {
    	prodottoConsigliato.draw();
    	for (int i = 0; i < 2; i++) {
     		for(int j = 0; j < prodotti[i].length; j++) {
 	        	prodotti[i][j].draw();
     		}
         }
    	foot.draw();
    }

	public void loadHomeProducts(ArrayList<String> products) {
		throw new UnsupportedOperationException("Unimplemented method 'run'");
	}
    
    public void registraEventiHome(Controller c) {
    	prodottoConsigliato.setName("ProdottoConsigliato");
    	prodottoConsigliato.addListener(c);
    	
    	for (int i = 0; i < 2; i++) {
    		for(int j = 0; j < prodotti[i].length; j++) {
	        	prodotti[i][j].setName("prodotto"+(i+1));
				prodotti[i][j].addListener(c);
    		}
        }
    	foot.registraEventiFoot(c);
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
