package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import controller.Controller;

public class ListenableGraphicComponent extends GraphicComponent implements Listenable {
	private Color hoveredColor, clickedColor, focussedColor, currentColor;
	
	//Constructor -------------------------------
	public ListenableGraphicComponent(Rectangle bounds, Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		super(bounds, color);
		this.currentColor = getColor();
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.focussedColor = focussedColor;
	}
	
	public ListenableGraphicComponent(int x, int y, int width, int height, Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		this(new Rectangle(x, y, width, height), color, hoveredColor, clickedColor, focussedColor);
	}

	public ListenableGraphicComponent(int x, int y, int width, int height, Color color) {
		this(new Rectangle(x, y, width, height), color, null, null, null);
	}
	
	//draw -----------------------------------------------
	@Override
	public void draw() {
		rShapes.DrawRectangleRec(getBounds(), currentColor);
	}
	
	//getters & setters ------------------------------------
	public Color getHoveredColor() {
		return hoveredColor;
	}

	public Color getClickedColor() {
		return clickedColor;
	}

	public Color getFocussedColor() {
		return focussedColor;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}
	
	public Color[] getColors() {
		return new Color[] {getColor(), hoveredColor, clickedColor, focussedColor};
	}
	
	public void setHoveredColor(Color hoveredColor) {
		this.hoveredColor = hoveredColor;
	}

	public void setClickedColor(Color clickedColor) {
		this.clickedColor = clickedColor;
	}

	public void setFocussedColor(Color focussedColor) {
		this.focussedColor = focussedColor;
	}
	
	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}
	
	public void setColors(Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		setColor(color);
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.focussedColor = focussedColor;
		this.currentColor = color;
	}
	
	//implements --------------------------------------------------------------
	@Override
	public void onHover() {
		if(getHoveredColor() != null) setCurrentColor(getHoveredColor());
	}
	
	@Override
	public void outOfHover() {
		setCurrentColor(getColor());
	}
	
	@Override
	public void onFocus() {
		if(getFocussedColor() != null) setCurrentColor(getFocussedColor());
	}
	
	@Override
	public void outOfFocus() {
		setCurrentColor(getColor());
	}
	
	@Override
	public void onClick(int modality) {
		if(getClickedColor() != null && modality == DOWN) setCurrentColor(getClickedColor());
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, getBounds());
	}
	
	@Override
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	@Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
	}
	
	//toString ---------------------------------------------
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tcolors [normal: " + getColor() + ", hovered: " + hoveredColor + ", clicked: " + clickedColor +
				"focussed: " + focussedColor +
				"]\n}";
	}
}
