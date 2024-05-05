package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import controller.Controller;
import model.Product;

public class Prodotto extends ListenableGraphicComponent {
	//
	private Rectangle imgBounds;
	private Vector2 origin;
	private float roundness;
	private int textFontSize, priceFontSize, padding;
	
	//product
	private Texture2D img;
	private Product p;
	
	//Constructor ---------------------------
	public Prodotto(int x, int y, int width, float roundness, Texture2D img, Product p, int textFontSize, int priceFontSize, int padding, Color color, Color hoveredColor, Color clickedColor) {
		super(x, y, width + (padding * 2), width + textFontSize + priceFontSize + (padding * 4), color, hoveredColor, clickedColor, null);
		
		this.padding = padding;
		this.imgBounds = new Rectangle(0, 0, img.getWidth(), img.getHeight());
		this.origin = new Vector2(0, 0);
		this.roundness = roundness;
		this.img = img;
		this.p = p;
		this.textFontSize = textFontSize;
		this.priceFontSize = priceFontSize;
	}
	
	//draw ----------------------------------------------
	@Override
	public void draw() {
		Finestra.getRaylib().shapes.DrawRectangleRounded(getBounds(), roundness, 5, getCurrentColor()); // background
		
		rTextures.DrawTexturePro(img, imgBounds, new Rectangle(getX() + padding, getY() + padding, getWidth() - (padding * 2), getWidth() - (padding * 2)), origin, 0f, Color.WHITE); // img
		
		Finestra.getRaylib().text.DrawText(
			p.getNome(),
			(int) (getX() + ((getWidth() / 2)-(Finestra.getRaylib().text.MeasureText(p.getNome(), textFontSize) / 2))),
			getY() + getWidth(),
			textFontSize, Color.BLACK
		); // text
		
		Finestra.getRaylib().text.DrawText(
			String.format("%.2f$", p.getPrezzo()),
			(int) (getX() + ((getWidth() / 2) - (Finestra.getRaylib().text.MeasureText(String.format("%.2f$", p.getPrezzo()), priceFontSize) / 2))),
			(int) (getY() + getWidth() + textFontSize + padding),
			priceFontSize, Color.BLACK
		); // price
	}
	
	//getters & setters -----------------------------
	public Rectangle getImgBounds() {
		return imgBounds;
	}

	public float getRoundness() {
		return roundness;
	}

	public int getTextFontSize() {
		return textFontSize;
	}

	public int getPriceFontSize() {
		return priceFontSize;
	}

	public Texture2D getImg() {
		return img;
	}

	public Product getP() {
		return p;
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness;
	}

	public void setTextFontSize(int textFontSize) {
		this.textFontSize = textFontSize;
	}

	public void setPriceFontSize(int priceFontSize) {
		this.priceFontSize = priceFontSize;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public void setImg(Texture2D img) {
		this.img = img;
	}
	
	//superclass overrides ---------------------------------------------------
	@Override
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	@Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
	}
}
