package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

public class TextButton extends Button {
	// label
	private String text;
	private int fontSize, padding;
	private Color textColor, hoveredTextColor, clickedTextColor, currentTextColor;
	
	// Constructors -----------------------------------------
	public TextButton(TextButton txtBtn) {
		super(txtBtn);
		this.text = txtBtn.getText();
		this.fontSize = txtBtn.getFontSize();
		this.padding = txtBtn.padding;
		this.textColor = txtBtn.getTextColor();
		this.hoveredTextColor = txtBtn.getHoveredTextColor();
		this.clickedTextColor = txtBtn.getClickedTextColor();
		this.currentTextColor = txtBtn.getCurrentColor();
	}
	
	public TextButton(Button btn, int padding, int fontSize, String text, Color textColor, Color hoverdTextColor, Color clickedTextColor) {
		super(btn);
		setSize(Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2));
		this.padding = padding;
		this.fontSize = fontSize;
		this.text = text;
		this.textColor = textColor;
		this.hoveredTextColor = hoverdTextColor;
		this.clickedTextColor = clickedTextColor;
		this.currentTextColor = textColor;
	}
	
	public TextButton(int x, int y, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor, int padding, int fontSize, String text, Color textColor, Color hoverdTextColor, Color clickedTextColor) {
		super(new Rectangle(x, y, Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2)), visible, roundness, color, hoveredColor, clickedColor);
		this.padding = padding;
		this.fontSize = fontSize;
		this.text = text;
		this.textColor = textColor;
		this.hoveredTextColor = hoverdTextColor;
		this.clickedTextColor = clickedTextColor;
		this.currentTextColor = textColor;
	}
	
	public TextButton(int x, int y, boolean visible, float roundness, Color color, int padding, int fontSize, String text, Color textColor) {
		this(x, y, visible, roundness, textColor, null, null, padding, fontSize, text, textColor, null, null);
	}
	
	public TextButton(int x, int y, Color color, int fontSize, String text, Color textColor) {
		this(x, y, true, 0, textColor, null, null, 0, fontSize, text, textColor, null, null);
	}
	
	// Draw -----------------------------------------------------------------
	public void draw() {
		super.draw();
		Finestra.getRaylib().text.DrawText(text, getX() + padding, getY() + padding, fontSize, currentTextColor);
	}
	
	//getters & setters ----------------------------------------------------------------
	public String getText() {
		return text;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getPadding() {
		return padding;
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
	
	public Color[] getTextColors() {
		return new Color[] {textColor, hoveredTextColor, clickedTextColor};
	}
	public void setText(String text) {
		this.text = text;
		autoSetWidth();
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		autoSetHeight();
		autoSetWidth();
	}

	public void setPadding(int padding) {
		this.padding = padding;
		autoSetHeight();
		autoSetWidth();
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
		this.currentTextColor = textColor;
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
	
	public void setTextColors(Color textColor, Color hoveredTextColor, Color clickedTextColor) {
		this.textColor = textColor;
		this.hoveredTextColor = hoveredTextColor;
		this.clickedTextColor = clickedTextColor;
		this.currentTextColor = textColor;
	}
	
	//private methods -------------------------------------------------
	//adjust width of button acording to new text width
	private void autoSetWidth() {
		setWidth(Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2)); 
	}
	
	//adjust height of button acording to new text height
	private void autoSetHeight() {
		setHeight(fontSize+(padding*2));
	}
	
	//superclass overrides ---------------------------------------------------
	@Override
	public void onHover() {
		super.onHover();
		if(hoveredTextColor != null) currentTextColor = hoveredTextColor;
	}
	
	@Override
	public void outOfHover() {
		super.outOfHover();
		currentTextColor = textColor;
	}
	
	@Override
	public void onClick(int modality) {
		if(modality == GraphicComponent.DOWN) {
			if(getClickedColor() != null) setCurrentColor(getClickedColor());
			if(clickedTextColor != null) currentTextColor = clickedTextColor;
		}
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return super.isHovered(mousePos);
	}
	
	//toString ---------------------------------------------------
	@Override
	public String toString() {
		return "TextButton {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tvisible: " + isVisible() +
				"\n\troundness: " + getRoundness() +
				"\n\tcolors [normal: " + getColor() + ", hovered: " + getHoveredColor() + ", clicked: " + getClickedColor() +
				"\n\ttext: " + text +
				"\n\tfont size: " + fontSize +
				"\n\ttext colors [normal: " + textColor + ", hovered: " + hoveredTextColor + ", clicked: " + clickedTextColor +
				"]\n}";
	}
}
