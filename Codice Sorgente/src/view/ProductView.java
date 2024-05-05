package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class ProductView {
	private Texture2D texture;
    private TextButton txtBtn;
    private TextureButton backBtn;
	private TextWrap productDescription;
    private Product prodotto;
    private int screenWidth, screenHeight;
    private int textX, imgY, imgX;
	
    public ProductView(int screenWidth, int screenHeight, int headerHeight) {
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		imgY = headerHeight + (screenHeight / 15);
		imgX = screenWidth / 30;

		txtBtn = new TextButton(-1, imgY+(-1), true, 0f, Color.PINK, Color.VIOLET,
        		Color.PINK, 10, 40, "Acquista ora", Color.WHITE, Color.RED, Color.DARKGREEN);
		txtBtn.setName("productview.acquista");

		productDescription = new TextWrap(null, 40, -1, imgY + txtBtn.getHeight(), screenWidth / 3, 200, Color.BLACK);
		
		int backBtnWidth = 75;
		int backBtnHeight = 75;
		backBtn = new TextureButton(new Texture2D("textures/BackArrow.png"), this.screenWidth - backBtnWidth - 40, this.screenHeight - backBtnHeight - 40, backBtnWidth, backBtnHeight, true, 20, 10, Color.SKYBLUE, Color.BLUE, Color.VIOLET);
		backBtn.setName("productview.back");
	}
    
    public void draw() {
    	Finestra.getRaylib().textures.DrawTexture(texture, imgX, imgY, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(prodotto.getNome(), textX, imgY , 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(String.format("%.2f$", prodotto.getPrezzo()), textX, imgY + txtBtn.getHeight() * 5, 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Codice prodotto: " + prodotto.getCodiceProdotto()), textX, imgY + txtBtn.getHeight() * 6, 30, Color.DARKGRAY);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Quantita' disponibile: " + prodotto.getQuantita()), textX, imgY + txtBtn.getHeight() * 8, 40, Color.DARKGRAY);

    	txtBtn.draw();
		productDescription.draw();
		backBtn.draw();
	}
    
    public void registraEventi(Controller c) {
    	txtBtn.addListener(c);
    	backBtn.addListener(c);
    }

	public void rimuoviEventi(Controller c) {
    	txtBtn.removeListener(c);
    	backBtn.removeListener(c);
	}
    
	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
		productDescription.setText(prodotto.getDescrizione());
		
		//get its texture
		this.texture = new Texture2D("textures/"+prodotto.getNome()+".png");
		
		//load default img if it does not exist
		if(texture == null) this.texture = Finestra.getPlaceHolderTexture();
		
		//adjust graphics
		textX = imgX * 6 + texture.width;
		txtBtn.setLocation(textX, imgY+texture.height);
		productDescription.setLocation(textX, imgY + txtBtn.getHeight());
	}
	
	public int getHeight() {
		return screenHeight;
	}
}
