package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

import controller.Controller;

public class TextButton extends Button {
	// label
	private String text;
	private int fontSize, padding, centerX;
	private Color textColor, hoveredTextColor, clickedTextColor, currentTextColor;
	
	// Constructors -----------------------------------------
	public TextButton(TextButton txtBtn) {
		super(txtBtn);
		
		this.centerX = txtBtn.getCenterX();
		this.text = txtBtn.getText();
		this.fontSize = txtBtn.getFontSize();
		this.padding = txtBtn.padding;
		this.textColor = txtBtn.getTextColor();
		this.hoveredTextColor = txtBtn.getHoveredTextColor();
		this.clickedTextColor = txtBtn.getClickedTextColor();
		this.currentTextColor = textColor;
		
		this.setInterationAction(getDefaultInterationAction());
	}
	
	public TextButton(Button btn, int padding, int fontSize, String text, Color textColor, Color hoverdTextColor, Color clickedTextColor) {
		super(btn);
		
		setSize(Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2));
		
		this.centerX = getX() + padding;
		this.padding = padding;
		this.fontSize = fontSize;
		this.text = text;
		this.textColor = textColor;
		this.hoveredTextColor = hoverdTextColor;
		this.clickedTextColor = clickedTextColor;
		this.currentTextColor = textColor;
		
		this.setInterationAction(getDefaultInterationAction());
	}
	
	public TextButton(int x, int y, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor, int padding, int fontSize, String text, Color textColor, Color hoverdTextColor, Color clickedTextColor) {
		super(new Rectangle(x, y, Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2)), visible, roundness, color, hoveredColor, clickedColor);
		
		this.centerX = getX() + padding;
		this.padding = padding;
		this.fontSize = fontSize;
		this.text = text;
		this.textColor = textColor;
		this.hoveredTextColor = hoverdTextColor;
		this.clickedTextColor = clickedTextColor;
		this.currentTextColor = textColor;
		
		this.setInterationAction(getDefaultInterationAction());
	}
	
	public TextButton(int x, int y, boolean visible, float roundness, Color color, int padding, int fontSize, String text, Color textColor) {
		this(x, y, visible, roundness, color, null, null, padding, fontSize, text, textColor, null, null);
	}
	
	public TextButton(int x, int y, Color color, int fontSize, String text, Color textColor) {
		this(x, y, true, 0, color, null, null, 0, fontSize, text, textColor, null, null);
	}
	
	// Draw -----------------------------------------------------------------
	public void draw() {
		super.draw();
		Finestra.getRaylib().text.DrawText(text, centerX, getY() + padding, fontSize, currentTextColor);
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
	
	public int getCenterX() {
		return centerX;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setLocation(int x, int y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setX(int x) {
		super.setX(x);
		centerX= x+padding;
	}
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		this.centerX = getX() + ((width/2) - (Finestra.getRaylib().text.MeasureText(text, fontSize)/2));
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
	
	//default Listenable ------------------------------------------
	@Override
	public Listenable getDefaultInterationAction() {
		Listenable superDefault = super.getDefaultInterationAction();
		
		return new Listenable() {
			@Override
			public void onHover() {
				superDefault.onHover();
				if(hoveredTextColor != null) currentTextColor = hoveredTextColor;
			}

			@Override
			public void outOfHover() {
				superDefault.outOfHover();
				currentTextColor = textColor;
			}

			@Override
			public void onClick(int modality) {
				if(modality == DOWN) {
					if(getClickedColor() != null) setCurrentColor(getClickedColor());
					if(clickedTextColor != null) currentTextColor = clickedTextColor;
				}
			}

			@Override
			public void onFocus() { superDefault.onFocus(); }

			@Override
			public void outOfFocus() { superDefault.outOfFocus(); }
			
			@Override
			public boolean isHovered(Vector2 mousePos) { return superDefault.isHovered(mousePos); }
		};
	}
	
	//add & remove listener ---------------------------------------------------
	@Override
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	@Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
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
