package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import controller.Controller;

public class Button extends GraphicComponent {
	private boolean visible;
	private float roundness;

	// Constructors -----------------------------------------
	public Button(Button btn) {
		super(new Rectangle(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight()), null,
			btn.getColor(), btn.getHoveredColor(), btn.getClickedColor(), null);
		
		this.visible = btn.isVisible();
		this.roundness = btn.getRoundness();
	}
	
	public Button(Rectangle bounds, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor) {
		super(bounds, null, color, hoveredColor, clickedColor, null);
		
		this.visible = visible;
		this.roundness = roundness;
	}
	
	public Button(int x, int y, int width, int height, boolean visible, float roundness, Color color, Color hoveredColor, Color clickedColor) {
		this(new Rectangle(x, y, width, height), visible, roundness, color, hoveredColor, clickedColor);
	}
	
	public Button(Rectangle bounds, boolean visible, float roundness, Color color) {
		super((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height, color);
		
		this.visible = visible;
		this.roundness = roundness;
	}
	
	public Button(int x, int y, int width, int height, boolean visible, float roundness, Color color) {
		super(x, y, width, height, color);
		
		this.visible = visible;
		this.roundness = roundness;
	}
	
	public Button(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		
		this.visible = true;
		this.roundness = 0;
	}

	// Draw -----------------------------------------------------------------
	@Override
	public void draw() {
		if(visible) Finestra.getRaylib().shapes.DrawRectangleRounded(getBounds(), roundness, 5, getCurrentColor());
	}

	// getters + setters ----------------------------------------------------
	public boolean isVisible() {
		return visible;
	}

	public float getRoundness() {
		return roundness;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness;
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
	
	//toString ---------------------------------------------------
	@Override
	public String toString() {
		return "Button {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tvisible: " + visible +
				"\n\troundness: " + roundness +
				"\n\tcolors [normal: " + getColor() + ", hovered: " + getHoveredColor() +
				", clicked: " + getClickedColor() +
				"]\n}";
	}
}
