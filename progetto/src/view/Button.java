package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;

public class Button {
	//border
	private Vector2 border;
	private Color backgroundColor;
	private boolean borderVisible;
	private int padding;
	
	//label
	private String text;
	private Color foregroundColor;
	private int fontSize;
	
	//Constructor -----------------------------------------
	public Button(Raylib ray, Color backgroundColor, boolean borderVisible, int padding, String text, Color foregroundColor, int fontSize) {
		this.backgroundColor = backgroundColor;
		this.borderVisible = borderVisible;
		this.padding = padding;
		this.text = text;
		this.foregroundColor = foregroundColor;
		this.fontSize = fontSize;
		
		border = new Vector2(ray.text.MeasureText(text, fontSize)+(padding*2), 0);
	}
	
	public Button(Raylib ray, String text, int fontSize, int padding) {
		this(ray, Color.WHITE, true, padding, text, Color.BLACK, fontSize);
	}
	
	public Button(Raylib ray, String text, int fontSize) {
		this(ray, Color.WHITE, true, 0, text, Color.BLACK, fontSize);
	}
	
	public Button(Raylib ray, int padding) {//TODO: getRaylibInstance() per favore giacomo pasqua arriva da me!! ( T_T )
		this(ray, Color.WHITE, true, padding, "", Color.BLACK, 12);
	}
	
	//Draw -----------------------------------------------------------------
	public void draw(Raylib ray, int x, int y) {
		if(borderVisible) ray.shapes.DrawRectangle(x, y, (int)(border.x), (int)(border.y), backgroundColor);
		ray.text.DrawText(text, x+padding, y+padding, fontSize, foregroundColor);
	}
	
	// ----------------------------------------------------------------
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isBorderVisible() {
		return borderVisible;
	}

	public void setBorderVisible(boolean borderVisible) {
		this.borderVisible = borderVisible;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
