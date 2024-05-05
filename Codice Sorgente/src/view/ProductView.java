package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class ProductView {
	private Texture2D texture;
    private TextButton txtBtn;
	private TextWrap productDescription;
    private Product prodotto;
	private TextureButton backBtn;
    private int screenWidth;
    private int screenHeight;
    private int textX;
    private int imgY;
    private int imgX;
	
    public ProductView(Texture2D texture, Product prodotto, int screenWidth, int screenHeight, int headerHeight) {
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.texture = texture;
		this.prodotto = prodotto;
		imgY = headerHeight + (screenHeight / 15);
		imgX = screenWidth / 30;
		
		textX = imgX * 6 + texture.width;

		txtBtn = new TextButton(textX, imgY + texture.height, true, 0f, Color.PINK, Color.VIOLET,
        		Color.PINK, 10, 40, "Acquista ora", Color.WHITE, Color.RED, Color.DARKGREEN);
		txtBtn.setName("acquista");

		productDescription = new TextWrap(null, 40, new Rectangle(textX, imgY + txtBtn.getHeight(), screenWidth / 3, 200), Color.BLACK);
		
		int backBtnWidth = 75;
		int backBtnHeight = 75;
		backBtn = new TextureButton(new Texture2D("textures/BackArrow.png"), this.screenWidth - backBtnWidth - 40, this.screenHeight - backBtnHeight - 40, backBtnWidth, backBtnHeight, true, 20, 10, Color.SKYBLUE, Color.BLUE, Color.VIOLET);
		backBtn.setName("back");
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
    
    public void registraEventiProdotto(Controller c) {
    	txtBtn.addListener(c);
    	backBtn.addListener(c);
    }

	public void rimuoviEventiProdotto(Controller c) {
    	txtBtn.removeListener(c);
    	backBtn.removeListener(c);
	}
    
	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;

		productDescription.setDescription(prodotto.getDescrizione());
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
}

