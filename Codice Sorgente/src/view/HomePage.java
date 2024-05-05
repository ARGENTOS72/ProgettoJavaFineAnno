package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class HomePage {
	private int screenWidth, screenHeight;
	
	private TextureButton prodottoConsigliato;
	private int prodottoConsigliatoY, prodottoConsigliatoHeight;
	
	private Prodotto[][] prodotti;
	private int nProdotti;
    private int prodottoWidth;
    
    private int padding;
    private Texture2D texture;
    
    public HomePage(int screenWidth, int screenHeight, int prodottoConsigliatoY, int prodottoConsigliatoHeight, int nProdotti) {
		super();
		this.prodottoConsigliatoY = prodottoConsigliatoY;
		this.prodottoConsigliatoHeight = prodottoConsigliatoHeight;
		this.prodottoWidth = screenWidth / 5;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		padding = this.screenHeight / 20;
		this.nProdotti = nProdotti;

		prodotti = new Prodotto[2][5];
		
		texture = new Texture2D("textures/PlaceHolder.jpg");
		
		prodottoConsigliato = new TextureButton(texture, 0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight, true, 0, 0, Color.LIGHTGRAY, Color.GRAY, Color.BLANK);
		prodottoConsigliato.setName("ProdottoConsigliato");

		String[] tempCat = {"a", "s"};
		Product product = new Product(10, "hak", "hak", new ArrayList<String>(Arrays.asList(tempCat)), 10);

		//Matrix creation
		Prodotto[] p1 = new Prodotto[(int) Math.ceil((float) nProdotti / 2)];
		Prodotto[] p2 = new Prodotto[(int) Math.floor((float) nProdotti / 2)];
		
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, product, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		}
		
		for (int i = 0; i < p2.length; i++) {
			p2[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, product, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		}
		
		Prodotto temp[][] = new Prodotto[][] {p1, p2};
		
		prodotti = temp;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < prodotti[i].length; j++) {
				prodotti[i][j].setName("prodotto" + (i * 2 + j));
			}
		}
		
		GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
				(prodottoConsigliatoY + prodottoConsigliatoHeight + padding * 2), 10, padding);
		
		for (int i = 0; i < prodotti[1].length; i++) {
			prodotti[1][i].setY(prodotti[0][i].getY());
			
			GraphicComponentAligner.alignX(new GraphicComponent[] {prodotti[0][i], prodotti[1][i]},
				GraphicComponentAligner.CENTER, GraphicComponentAligner.DISTRIBUTE, 0, screenWidth,-1);
		}
		
		if (nProdotti % 2 != 0) prodotti[0][prodotti[0].length - 1].setX(prodotti[0][0].getX());
	}
    
    public void draw() {
    	prodottoConsigliato.draw();

    	for (int i = 0; i < 2; i++) {
     		for (int j = 0; j < prodotti[i].length; j++) {
 	        	prodotti[i][j].draw();
     		}
        }
    }

	public void loadHomeProducts(ArrayList<Product> products) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < prodotti[i].length; j++) {
				Product product = products.get(i * 2 + j);

				prodotti[i][j].setP(product);

				File file = new File(product.getNome() + ".png");
				
				if (file.exists()) {
					Texture2D texture = new Texture2D(product.getNome() + ".png");

					prodotti[i][j].setImg(texture);
				} else {
					prodotti[i][j].setImg(new Texture2D());
				}
			}
		}
	}
    
    public void registraEventi(Controller c) {
    	prodottoConsigliato.addListener(c);
    	
    	for (int i = 0; i < 2; i++) 
    		for (int j = 0; j < prodotti[i].length; j++) 
				prodotti[i][j].addListener(c);
    }

	public void rimuoviEventi(Controller c) {
		prodottoConsigliato.removeListener(c);

		for (int i = 0; i < 2; i++)
    		for (int j = 0; j < prodotti[i].length; j++) 
				prodotti[i][j].removeListener(c);
	}

    public int getHeight() {
		return (prodottoConsigliatoY + prodottoConsigliatoHeight + padding * 2) +
				((prodotti[0][0].getHeight() + padding * 2) * prodotti[0].length);
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

	public int getNProdotti() {
		return nProdotti;
	}
}
