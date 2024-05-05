package view;

import java.util.ArrayList;

import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class ProductsSearched {
	private int screenWidth, screenHeight;
	
	private Prodotto[][] prodotti;
	private int prodottoWidth;
	
    private Texture2D texture;
    private int headerHeight;
    
	private int padding;
	
    public ProductsSearched(int headerHeight, int screenWidth, int screenHeight) {
		super();
		this.headerHeight = headerHeight;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.prodottoWidth = screenWidth / 5;
		this.texture = new Texture2D("textures/SearchIcon.png");
		this.padding = screenHeight / 20;
		
		prodotti = null;
	}
    
    public void draw() {
    	if (prodotti != null) {
			for (int i = 0; i < 2; i++) {
				if (prodotti[i] != null) {
				  	for (int j = 0; j < prodotti[i].length; j++) {
					   	if (prodotti[i][j] != null) {
							prodotti[i][j].draw();
						}
					}
			   }
		   }
		} else {
			Finestra.getRaylib().text.DrawText("Non sono presenti prodotti con questa ricerca", 10, headerHeight + padding / 2, 40, Color.BLACK);
		}
    }

	public void loadSearchedProdotti(ArrayList<Product> products) {
		prodotti = null;

		int nProdotti = products.size();

		Prodotto[] p1 = new Prodotto[(int) Math.ceil((float) nProdotti / 2)];
		Prodotto[] p2 = new Prodotto[(int) Math.floor((float) nProdotti / 2)];
		
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, products.get(i), 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		
			p1[i].setName("prodotto" + products.get(i).getCodiceProdotto());
		}
		
		for (int i = 0; i < p2.length; i++) {
			p2[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, products.get(p1.length + i), 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		
			p2[i].setName("prodotto" + products.get(p1.length + i).getCodiceProdotto());
		}
		
		Prodotto temp[][] = new Prodotto[][] {p1, p2};
		
		prodotti = temp;
		
		GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
				(this.headerHeight + padding * 2), 10, padding);
		
		if (nProdotti > 2) {
			for(int i = 0; i < prodotti[1].length; i++) {
				prodotti[1][i].setY(prodotti[0][i].getY());

				GraphicComponentAligner.alignX(new GraphicComponent[] {prodotti[0][i], prodotti[1][i]},
					GraphicComponentAligner.CENTER, GraphicComponentAligner.DISTRIBUTE, 0, screenWidth, -1);
			}
		}
		
		if (nProdotti % 2 != 0)
			GraphicComponentAligner.centerX(prodotti[0][prodotti[0].length - 1], 0, screenWidth);
	}
    
    public void registraEventi(Controller c) {
    	if (prodotti != null) {
			for (int i = 0; i < 2; i++) {
				if (prodotti[i] != null) {
					for (int j = 0; j < prodotti[i].length; j++) {
						if (prodotti[i][j] != null) {
							prodotti[i][j].addListener(c);
						}
					}
				}
			}
		}
    }
    
	public void rimuoviEventi(Controller c) {
		if (prodotti != null) {
			for (int i = 0; i < 2; i++) {
				if (prodotti[i] != null) {
					for (int j = 0; j < prodotti[i].length; j++) {
						if (prodotti[i][j] != null) {
							prodotti[i][j].removeListener(c);
						}
					}
				}
			}
		}
	}
	
	public int getHeight() {
		if (prodotti[0].length > 0) return (headerHeight + padding * 2) +
		((prodotti[0][0].getHeight() + padding * 2) * prodotti[0].length);
		
		return headerHeight + padding * 2;
	}
}
