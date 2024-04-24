package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import controller.Controller;

public class GraphicComponent {// has an hitbox, isHovered
	public static final int PRESSED = 0, DOWN = 1, RELEASED = 2, UP = 3;
	
	private Rectangle bounds;
	private Color color, hoveredColor, clickedColor, focussedColor, currentColor;
	private String name;
	
	//Constructor -------------------------------
	public GraphicComponent(Rectangle bounds, String name, Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		this.bounds = bounds;
		this.name = name;
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.focussedColor = focussedColor;
		this.currentColor = color;
	}
	
	public GraphicComponent(int x, int y, int width, int height, String name, Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		this(new Rectangle(x, y, width, height), name, color, hoveredColor, clickedColor, focussedColor);
	}

	public GraphicComponent(int x, int y, int width, int height, Color color) {
		this.bounds = new Rectangle(x, y, width, height);
		this.color = color;
		this.currentColor = color;
	}
	
	//draw ------------------------------------------
	public void draw() {
		rShapes.DrawRectangleRec(bounds, currentColor);
	}
	
	//getters & setters -------------------------
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
	
	public Color[] getColors() {
		return new Color[] {color, hoveredColor, clickedColor, focussedColor};
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

	public Color getFocussedColor() {
		return focussedColor;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}

	public String getName() {
		return name;
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
	
	public void setColors(Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.focussedColor = focussedColor;
		this.currentColor = color;
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

	public void setFocussedColor(Color focussedColor) {
		this.focussedColor = focussedColor;
	}
	
	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//change state methods ---------------------------
	//default operations to do intra-class when component is on hover
	public void onHover() {
		if(hoveredColor != null) currentColor = hoveredColor;
	}
	
	//default operations to do intra-class when component is out of hover
	public void outOfHover() {
		currentColor = color;
	}
	
	//default operations to do intra-class when component is on focus
	public void onFocus() {
		if(focussedColor != null) currentColor = focussedColor;
	}
	
	//default operations to do intra-class when component is out of focus
	public void outOfFocus() {
		currentColor = color;
	}
	
	//default operations to do intra-class when component is clicked either pressed, down, released or up
	public void onClick(int modality) {
		if(clickedColor != null && modality == GraphicComponent.DOWN) currentColor = clickedColor;
	}
	
	//method to check wheter mouse is over the component or not
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, bounds);
	}
	
	//default operations to do when this GraphicComponent need to add a listener
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	//default operations to do when this GraphicComponent need to remove a listener
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
	}
	
	//toString --------------------------------------------
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tcolors [normal: " + color + ", hovered: " + hoveredColor + ", clicked: " + clickedColor +
				"focussed: " + focussedColor +
				"]\n}";
	}
}
