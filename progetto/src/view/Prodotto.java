package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import controller.Controller;
import model.Product;

public class Prodotto extends GraphicComponent {
	//
	private Rectangle bounds, imgBounds;
	private Vector2 origin;
	private float roundness;
	private int textFontSize, priceFontSize;
	private Color color, hoveredColor, clickedColor, currentColor;
	
	//product
	private Texture2D img;
	private Product p;
	
	//Constructor ---------------------------
	public Prodotto(int x, int y, int width, int height, float roundness, Texture2D img, Product p, int textFontSize, int priceFontSize, Color color, Color hoveredColor, Color clickedColor) {
		this.bounds = new Rectangle(x, y, width, height);
		this.imgBounds = new Rectangle(0, 0, img.getWidth(), img.getHeight());
		this.origin = new Vector2(0, 0);
		this.roundness = roundness;
		this.img = img;
		this.p = p;
		this.textFontSize = textFontSize;
		this.priceFontSize = priceFontSize;
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.currentColor = color;
	}
	
	//draw ----------------------------------------------
	public void draw() {//TODO
		Finestra.getRaylib().shapes.DrawRectangleRounded(bounds, roundness, 5, currentColor);//background
		rTextures.DrawTexturePro(img, imgBounds, new Rectangle((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.width), origin, 0, Color.WHITE);//img
		Finestra.getRaylib().text.DrawText(
			p.getNome(),
			(int) (bounds.x+((bounds.width/2)-(Finestra.getRaylib().text.MeasureText(p.getNome(), textFontSize)/2))),
			(int) (bounds.y+bounds.width),
			textFontSize, Color.BLACK
		);//text
		Finestra.getRaylib().text.DrawText(
			String.valueOf(p.getPrezzo()+"$"),
			(int) (bounds.x+((bounds.width/2)-(Finestra.getRaylib().text.MeasureText(String.valueOf(p.getPrezzo()+"$"), priceFontSize)/2))),
			(int) (bounds.y+bounds.width+textFontSize+5),
			priceFontSize, Color.BLACK
		);//price
	}
	
	//getters & setters ----------------------------- TODO
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getLocation() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	public Vector2 getSize() {
		return new Vector2(bounds.width, bounds.height);
	}
	
	public int getX() {
		return (int) bounds.x;
	}
	
	public int getY() {
		return (int) bounds.y;
	}
	
	public int getWidth() {
		return (int) bounds.width;
	}
	
	public int getHeight() {
		return (int) bounds.height;
	}

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

	public Color getColor() {
		return color;
	}

	public Color getHoveredColor() {
		return hoveredColor;
	}

	public Color getClickedColor() {
		return clickedColor;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public Texture2D getImg() {
		return img;
	}

	public Product getP() {
		return p;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		bounds.x = x;
		bounds.y = y;
		bounds.width = width;
		bounds.height = height;
	}
	
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}
	
	public void setSize(int width, int height) {
		bounds.width = width;
		bounds.height = height;
	}
	
	public void setX(int x) {
		bounds.x = x;
	}
	
	public void setY(int y) {
		bounds.y = y;
	}
	
	public void setWidth(int width) {
		bounds.width = width;
	}
	
	public void setHeight(int height) {
		bounds.height = height;
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

	public void setColor(Color color) {
		this.color = color;
	}

	public void setHoveredColor(Color hoveredColor) {
		this.hoveredColor = hoveredColor;
	}

	public void setClickedColor(Color clickedColor) {
		this.clickedColor = clickedColor;
	}

	public void setP(Product p) {
		this.p = p;
	}
	
	//superclass overrides ---------------------------------------------------
	@Override
	public void onHover() {
		if(hoveredColor != null) currentColor = hoveredColor;
	}
	
	@Override
	public void outOfHover() {
		currentColor = color;
	}
	
	@Override
	public void onClick(int modality) {
		if(clickedColor != null && modality == GraphicComponent.DOWN) currentColor = clickedColor;
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, bounds);
	}
	
	@Override
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	@Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
	}
}
