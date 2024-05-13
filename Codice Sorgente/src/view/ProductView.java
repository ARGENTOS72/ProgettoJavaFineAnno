package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

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

		txtBtn = new TextButton(screenWidth / 2 + 100, imgY + 530, true, 0f, Color.PINK, Color.VIOLET,
        		Color.PINK, 10, 40, "Acquista ora", Color.WHITE, Color.RED, Color.DARKGREEN);
		txtBtn.setName("productview.acquista");

		productDescription = new TextWrap(null, 40, screenWidth / 2 + 100, imgY + txtBtn.getHeight(), screenWidth / 3, 200, Color.BLACK);
		textX = screenWidth / 2 + 100;
		
		int backBtnWidth = 75;
		int backBtnHeight = 75;
		backBtn = new TextureButton(new Texture2D("textures/BackArrow.png"), this.screenWidth - backBtnWidth - 40, this.screenHeight - backBtnHeight - 40, backBtnWidth, backBtnHeight, true, 20, 10, Color.SKYBLUE, Color.BLUE, Color.VIOLET);
		backBtn.setName("productview.back");
	}
    
    public void draw() {
    	rTextures.DrawTexturePro(texture, new Rectangle(0, 0, texture.width, texture.height), new Rectangle(imgX * 2, imgY * 2, screenWidth / 2, screenHeight / 2), new Vector2(imgX, imgY), 0f, Color.WHITE);
    	Finestra.getRaylib().text.DrawText(prodotto.getNome(), textX, imgY , 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(String.format("%.2f$", prodotto.getPrezzo()), textX, imgY + txtBtn.getHeight() * 5, 50, Color.BLACK);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Codice prodotto: " + prodotto.getCodiceProdotto()), textX, imgY + txtBtn.getHeight() * 6, 30, Color.DARKGRAY);
    	Finestra.getRaylib().text.DrawText(String.valueOf("Quantita' disponibile: " + prodotto.getQuantita()), textX, imgY + txtBtn.getHeight() * 8, 40, Color.DARKGRAY);

    	txtBtn.draw();
		productDescription.draw();
		backBtn.draw();
	}
    
    public void registraEventi(Controller c) {
		if (prodotto.getQuantita() != 0) {
			txtBtn.addListener(c);
		}
    	backBtn.addListener(c);
    }

	public void rimuoviEventi(Controller c) {
    	txtBtn.removeListener(c);
    	backBtn.removeListener(c);
	}
    
	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
		productDescription.setText(prodotto.getDescrizione());

		// get its texture
		this.texture = new Texture2D("textures/" + prodotto.getNome() + ".png");
		
		// load default img if it does not exist
		if (texture.id<= 0) this.texture = Finestra.getPlaceHolderTexture();

		if (prodotto.getQuantita() != 0) {
			txtBtn.setColors(Color.PINK, Color.VIOLET, Color.PINK, null);
			txtBtn.setTextColors(Color.WHITE, Color.RED, Color.DARKGREEN);
		}
	}

	public int codiceProdotto() {
		return prodotto.getCodiceProdotto();
	}

	public int quantitaProdotto() {
		return prodotto.getQuantita();
	}

	public void disabilitaBottoneAcquista(Controller c) {
		txtBtn.setColors(Color.PINK, null, null, null);
		txtBtn.setTextColors(Color.WHITE, null, null);
		txtBtn.removeListener(c);
	}
	
	public int getHeight() {
		return screenHeight;
	}
}
