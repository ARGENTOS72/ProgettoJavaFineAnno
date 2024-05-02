package view;

import com.raylib.java.core.Color;

import controller.Controller;

public class TextAnimation extends GraphicAnimation {
	//animation based on time
	private float elapsedTimeInASecond;
	private int state;
	private String originalText;
	
	//label
	private boolean backgroundVisible;
	private String text;
	private int fontSize, padding;
	private Color textColor;
	
	//Constructor -----------------------
	public TextAnimation(TextAnimation textAnimation) {
		super(textAnimation.getX(), textAnimation.getY(), textAnimation.getWidth(), textAnimation.getHeight(), textAnimation.getColor());
		
		this.text = textAnimation.getText();
		this.fontSize = textAnimation.getFontSize();
		this.padding = textAnimation.getPadding();
		this.textColor = textAnimation.getTextColor();
		this.backgroundVisible = textAnimation.isBackgroundVisible();
		this.elapsedTimeInASecond = 0f;
		this.state = 0;
		this.originalText = text;
		
		setAnimation(getDefaultAnimation());
	}
	
	public TextAnimation(int x, int y, Color color, boolean backgroundVisible, String text, int fontSize, int padding, Color textColor) {
		super(x, y, Finestra.getRaylib().text.MeasureText(text, fontSize)+(padding*2), fontSize+(padding*2), color);
		
		this.text = text;
		this.fontSize = fontSize;
		this.padding = padding;
		this.textColor = textColor;
		this.backgroundVisible = backgroundVisible;
		this.elapsedTimeInASecond = 0;
		this.state = 0;
		this.originalText = text;
		
		setAnimation(getDefaultAnimation());
	}
	
	//draw ----------------------------------------
	@Override
	public void draw() {
		if(backgroundVisible) super.draw();
		Finestra.getRaylib().text.DrawText(text, getX() + padding, getY() + padding, fontSize, textColor);
	}
	
	//getters & setters ------------------------------
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
	
	public Color[] getColors() {
		return new Color[] {getColor(), textColor};
	}
	
	public boolean isBackgroundVisible() {
		return backgroundVisible;
	}
	
	public float getElapsedTimeInASecond() {
		return elapsedTimeInASecond;
	}
	
	public int getState() {
		return state;
	}
	
	public String getOriginalText() {
		return originalText;
	}

	public void setJustText(String text) {
		this.text = text;
		autoSetWidth();
	}
	
	public void setText(String text) {
		this.text = text;
		this.originalText = text;
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
	}
	
	public void setColors(Color color, Color textColor) {
		setColor(color);
		this.textColor = textColor;
	}
	
	public void setBackgroundVisible(boolean backgroundVisible) {
		this.backgroundVisible = backgroundVisible;
	}
	
	public void setElapsedTimeInASecond(float elapsedTimeInASecond) {
		this.elapsedTimeInASecond = elapsedTimeInASecond;
	}
	
	public void setState(int state) {
		this.state = state;
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
	
	//default animation ---------------------------------
	@Override
	public Animable getDefaultAnimation() {
		return new Animable() {
			@Override
			public void update(float deltaTime) {
				elapsedTimeInASecond+= deltaTime;
				
				//next
				if(elapsedTimeInASecond < 0.3f && state != 1) {//loading .
					setJustText(originalText+" .");
					
				} else if(elapsedTimeInASecond < 0.6f && state != 2) {//loading ..
					setJustText(originalText+" ..");
					
				} else if(state != 3) {//loading ...
					setJustText(originalText+" ...");
				}
					
				//reset if >1sec
				if(elapsedTimeInASecond > 1f) elapsedTimeInASecond = 0f;
			}
		};
	}
	
	//add & remove updater ----------------------------------
	//default operations to do when this Animable Object need to add a updater
	public void addUpdater(Controller c) {
		c.addUpdaterTo(this);
	}

	//default operations to do when this Animable Object need to remove a updater
	public void removeUpdater(Controller c) {
		c.removeUpdaterTo(this);
	}
	
}
