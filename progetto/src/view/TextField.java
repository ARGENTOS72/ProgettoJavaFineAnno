package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;

public class TextField extends GraphicComponent {
	//
	private Rectangle bounds;
	private boolean backgroundVisible;
	private float roundness;
	private int padding;
	private Color color, hoveredColor, clickedColor, currentColor;
	
	//
	private int fontSize;
	private Color textColor, hoveredTextColor, clickedTextColor, currentTextColor;
	private char[] text;
    private byte maxChars;
    private byte nCurrenteLetters;
    private int key;
    private int frameCounter;
    private boolean cancel;
    
    //constrcutor ---------------------------------------------------------------------
    public TextField(int x, int y, int width, boolean backgroundVisible, float roundness, int padding, Color color, Color hoveredColor, Color clickedColor, int fontSize, Color textColor, Color hoveredTextColor, Color clickedTextColor, byte maxChars) {
    	this.bounds = new Rectangle(x, y, width, fontSize+(padding*2));
    	this.backgroundVisible = backgroundVisible;
    	this.roundness = roundness;
    	this.padding = padding;
    	this.color = color;
    	this.hoveredColor = hoveredColor;
    	this.clickedColor = clickedColor;
    	this.currentColor = color;
    	this.fontSize = fontSize;
    	this.textColor = textColor;
    	this.hoveredTextColor = hoveredTextColor;
    	this.clickedTextColor = clickedTextColor;
    	this.currentTextColor = textColor;
    	this.maxChars = maxChars;
    	
    	this.text = new char[maxChars];
        for (int i = 0; i < maxChars; i++) this.text[i] = ' ';
        this.nCurrenteLetters = 0;
        this.key = 0;
        this.frameCounter = 0;
        this.cancel = true;
    }
    //TODO: add other constructor
    
    //draw -----------------------------------------
    public void draw() {
    	Finestra.getRaylib().shapes.DrawRectangleRounded(bounds, roundness, 5, currentColor);
    	Finestra.getRaylib().text.DrawText(String.valueOf(text), (int)(bounds.x+padding), (int)(bounds.y+padding), fontSize, textColor);
    }
    
    //getters & setters ---------------------------------------
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
    
	public boolean isBackgroundVisible() {
		return backgroundVisible;
	}

	public float getRoundness() {
		return roundness;
	}

	public int getPadding() {
		return padding;
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

	public int getFontSize() {
		return fontSize;
	}

	public Color getTextColor() {
		return textColor;
	}

	public Color getHoveredTextColor() {
		return hoveredTextColor;
	}

	public Color getClickedTextColor() {
		return clickedTextColor;
	}

	public Color getCurrentTextColor() {
		return currentTextColor;
	}

	public String getText() {
		return String.valueOf(text);
	}

	public byte getMaxChars() {
		return maxChars;
	}

	public byte getnCurrenteLetters() {
		return nCurrenteLetters;
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

	public void setBackgroundVisible(boolean backgroundVisible) {
		this.backgroundVisible = backgroundVisible;
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness;
	}

	public void setPadding(int padding) {
		this.padding = padding;
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

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setHoveredTextColor(Color hoveredTextColor) {
		this.hoveredTextColor = hoveredTextColor;
	}

	public void setClickedTextColor(Color clickedTextColor) {
		this.clickedTextColor = clickedTextColor;
	}

	public void setCurrentTextColor(Color currentTextColor) {
		this.currentTextColor = currentTextColor;
	}

//	public void setText(char[] text) {
//		this.text = text;
//	}
	
	//superclass overrides ---------------------------------------
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, bounds);
	}
	
	//TODO:
	
}
