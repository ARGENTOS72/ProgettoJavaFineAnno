package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import controller.Controller;

public class GraphicComponent {// has an hitbox, isHovered
	private Rectangle bounds;
	private Color color;
	private String name;
	
	//Constructor -------------------------------
	public GraphicComponent(Rectangle bounds, Color color) {
		this.bounds = bounds;
		this.color = color;
	}
	
	public GraphicComponent(int x, int y, int width, int height, Color color) {
		this.bounds = new Rectangle(x, y, width, height);
		this.color = color;
	}
	
	//draw ------------------------------------------
	public void draw() {
		rShapes.DrawRectangleRec(bounds, color);
		System.out.println("bounds: "+bounds.x+", "+bounds.y+", "+bounds.width+", "+bounds.height);
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
	
	public Color getColor() {
		return color;
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
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//toString --------------------------------------------
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tcolor: " + color +
				"\n}";
	}
}
