package view;

import java.io.File;
import java.util.ArrayList;

import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class ProductsSearched {
	private int screenWidth, screenHeight;
	
	private Prodotto[][] prodotti;
	private int prodottoWidth;
	
    private int headerHeight;
    
	private int padding;
	
    public ProductsSearched(int headerHeight, int screenWidth, int screenHeight) {
		super();
		this.headerHeight = headerHeight;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.prodottoWidth = screenWidth / 5;
		this.padding = this.screenHeight / 20;
		
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
			Finestra.getRaylib().text.DrawText("Non sono presenti prodotti con questa ricerca",
				(Finestra.unscaledScreenWidth/2)-(Finestra.getRaylib().text.MeasureText("Non sono presenti prodotti con questa ricerca", 40)/2),
				headerHeight + padding / 2, 40, Color.VIOLET);
		}
    }

	public void loadSearchedProdotti(ArrayList<Product> products) {
		prodotti = null;

		int nProdotti = products.size();

		Prodotto[] p1 = new Prodotto[(int) Math.ceil((float) nProdotti / 2)];
		Prodotto[] p2 = new Prodotto[(int) Math.floor((float) nProdotti / 2)];
		
		for (int i = 0; i < p1.length; i++) {
			Product prodotto = products.get(i);

			File file = new File("textures/" + prodotto.getNome() + ".png");
			Texture2D texture; 

			if (file.exists()) {
				texture = new Texture2D("textures/" + prodotto.getNome() + ".png");
			} else {
				texture = Finestra.getPlaceHolderTexture();
			}

			p1[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, prodotto, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		
			p1[i].setName("prodotto" + products.get(i).getCodiceProdotto());
		}
		
		for (int i = 0; i < p2.length; i++) {
			Product prodotto = products.get(p1.length + i);

			File file = new File("textures/" + prodotto.getNome() + ".png");
			Texture2D texture; 

			if (file.exists()) {
				texture = new Texture2D("textures/" + prodotto.getNome() + ".png");
			} else {
				texture = Finestra.getPlaceHolderTexture();
			}

			p2[i] = new Prodotto(0, 0, prodottoWidth, 0.4f, texture, prodotto, 40, 30, 50, Color.WHITE, new Color(255, 182, 224, 255), Color.PINK);
		
			p2[i].setName("prodotto" + products.get(p1.length + i).getCodiceProdotto());
		}
		
		if (p1.length != 0) {
			Prodotto temp[][] = new Prodotto[][] {p1, p2};
		
			prodotti = temp;
		
			GraphicComponentAligner.alignY(prodotti[0], GraphicComponentAligner.UP, GraphicComponentAligner.PADDING,
					(this.headerHeight + padding * 2), 10, padding);
		}

		if (nProdotti > 1) {
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
		if (prodotti == null) return 10;

		if (prodotti[0].length > 0) return (headerHeight + padding * 2) +
		((prodotti[0][0].getHeight() + padding * 2) * prodotti[0].length);
		
		return headerHeight + padding * 2;
	}
	
	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}
}
