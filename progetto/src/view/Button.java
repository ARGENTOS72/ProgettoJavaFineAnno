package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

import controller.Controller;

public class Button extends GraphicComponent {
	private Rectangle bounds;
	private boolean visible;
	private float roundness;
	private Color color, hoveredColor, clickedColor, currentColor;

	// Constructors -----------------------------------------
	public Button(Button btn) {
		super();
		this.bounds = new Rectangle(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());
		this.visible = btn.isVisible();
		this.roundness = btn.getRoundness();
		this.color = btn.getColor();
		this.hoveredColor = btn.getHoveredColor();
		this.clickedColor = btn.getClickedColor();
		this.currentColor = color;
	}
	
	public Button(Rectangle bounds, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor) {
		super();
		this.bounds = bounds;
		this.visible = visible;
		this.roundness = roundness;
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.currentColor = color;
	}
	
	public Button(int x, int y, int width, int height, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor) {
		super();
		this.bounds = new Rectangle(x, y, width, height);
		this.visible = visible;
		this.roundness = roundness;
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.currentColor = color;
	}
	
	public Button(Rectangle bounds, boolean visible, float roundness, Color color) {
		this(bounds, visible, roundness, color, null, null);
	}
	
	public Button(int x, int y, int width, int height, float roundness, boolean visible, Color color) {
		this(new Rectangle(x, y, width, height), visible, roundness, color, null, null);
	}
	
	public Button(int x, int y, int width, int height, Color color) {
		this(new Rectangle(x, y, width, height), true, 0, color, null, null);
	}

	// Draw -----------------------------------------------------------------
	public void draw() {
		if (visible) Finestra.getRaylib().shapes.DrawRectangleRounded(bounds, roundness, 5, currentColor);
	}

	// getters + setters ----------------------------------------------------
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

	public boolean isVisible() {
		return visible;
	}

	public float getRoundness() {
		return roundness;
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
	
	public Color[] getColors() {
		return new Color[] {color, hoveredColor, clickedColor};
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

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness;
	}

	public void setColor(Color color) {
		this.color = color;
		this.currentColor = color;
	}

	public void setHoveredColor(Color hoveredColor) {
		this.hoveredColor = hoveredColor;
	}

	public void setClickedColor(Color clickedColor) {
		this.clickedColor = clickedColor;
	}
	
	public void setCurrentColor(Color curentColor) {
		this.currentColor = curentColor;
	}
	
	public void setColors(Color color, Color hoveredColor, Color clickedColor) {
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.currentColor = color;
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
	
	//toString ---------------------------------------------------
	@Override
	public String toString() {
		return "Button {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tvisible: " + visible +
				"\n\troundness: " + roundness +
				"\n\tcolors [normal: " + color + ", hovered: " + hoveredColor + ", clicked: " + clickedColor +
				"]\n}";
	}
}
