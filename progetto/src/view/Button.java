package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

public class Button extends GraphicComponent {
	//border
	private Rectangle border;
	private Color backgroundColor;
	private boolean borderVisible;
	private int padding;
	
	//label
	private String text;
	private Color foregroundColor;
	private int fontSize;
	
	//Constructors -----------------------------------------
	public Button(Color backgroundColor, boolean borderVisible, int padding, int x, int y, String text, Color foregroundColor, int fontSize) {
		super();
		this.backgroundColor = backgroundColor;
		this.borderVisible = borderVisible;
		this.padding = padding;
		this.text = text;
		this.foregroundColor = foregroundColor;
		this.fontSize = fontSize;
		
		border = new Rectangle(x, y, Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2));
	}
	
	public Button(Color backgroundColor, boolean borderVisible, int padding, Vector2 pos, String text, Color foregroundColor, int fontSize) {
		this(backgroundColor, borderVisible, padding, (int)(pos.x), (int)(pos.y), text, foregroundColor, fontSize);
	}
	
	public Button(String text, int fontSize, int padding, int x, int y) {
		this(Color.WHITE, true, padding, new Vector2(x, y), text, Color.BLACK, fontSize);
	}
	
	public Button(String text, int fontSize, int x, int y) {
		this(Color.WHITE, true, 0, x, y, text, Color.BLACK, fontSize);
	}
	
	public Button(int padding, int x, int y) {
		this(Color.WHITE, true, padding, x, y, "", Color.BLACK, 12);
	}
	
	//Draw -----------------------------------------------------------------
	public void draw() {
		if(borderVisible) rShapes.DrawRectangleRec(border, backgroundColor);
		Finestra.getRaylib().text.DrawText(text, (int)(border.x)+padding, (int)(border.y)+padding, fontSize, foregroundColor);
	}
	
	//getters + setters + superclass overrides ------------------------------------------
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
		//recalc border
		border.width = (border.width-(this.padding*2))+(padding*2);
		border.height = fontSize+padding+padding;
		
		this.padding = padding;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		
		//recalc rectangle
		border.width = Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2);
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
		
		//recalc rectangle
		border.width = Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2);
		border.height = fontSize+padding+padding;
	}
	
	public Rectangle getBorder() {
		return border;
	}
	
	public void setBorder(Rectangle border) {
		this.border = border;
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, border);
	}
}
