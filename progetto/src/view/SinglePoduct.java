package view;

import com.raylib.java.core.Color;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import controller.Controller;
import model.Product;

public class SinglePoduct {
	private Texture2D texture;
    private TextButton txtBtn;
    private Product prodotto;
    
    private int textX;
    private int imgY;
    private int imgX;

	
    public SinglePoduct(Texture2D texture, Product prodotto, int screenWidth, int screenHeight, int headerHeight) {
		super();
		this.texture = texture;
		this.prodotto = prodotto;
		imgY = headerHeight+(screenHeight/15);
		imgX = screenWidth/30;
		
		textX = (imgX*6)+(texture.width);
		txtBtn = new TextButton(textX, imgY+texture.height, true, 0f, Color.PINK, Color.VIOLET,
        		Color.PINK, 10, 40, "Acquista ora", Color.WHITE, Color.RED, Color.DARKGREEN);
	}
    
    public void draw() {
    	Finestra.getRaylib().textures.DrawTexture(texture, imgX, imgY, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(prodotto.getNome(), textX, imgY , 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(prodotto.getDescrizione(), textX, imgY+txtBtn.getHeight(), 40, Color.DARKGRAY);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Prezzo: "+prodotto.getPrezzo()), textX, imgY+(txtBtn.getHeight()*4), 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Codice prodotto: "+prodotto.getCodiceProdotto()), textX, imgY+(txtBtn.getHeight()*5), 30, Color.DARKGRAY);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Quantita' disponibile: "+prodotto.getQuantita()), textX, imgY+(txtBtn.getHeight()*8), 40, Color.DARKGRAY);

    	txtBtn.draw();
    }
    
    public void registraEventiProdotto(Controller c) {
    	txtBtn.setName("Acquista");
    	txtBtn.addListener(c);
    }
    
}
