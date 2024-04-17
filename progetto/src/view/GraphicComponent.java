package view;

import com.raylib.java.raymath.Vector2;

public class GraphicComponent {// has an hitbox, isHovered
	public static final int PRESSED = 0, DOWN = 1, RELEASED = 2, UP = 3;
	private String name;
	
	//Constructor -------------------------------
	public GraphicComponent() {
		this.name = null;
	}

	public GraphicComponent(String name) {
		this.name = name;
	}
	
	//getters & setters -------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//must-have method ---------------------------
	//default operations to do intra-class when component is on hover
	public void onHover() {}
	//default operations to do intra-class when component is out of hover
	public void outOfHover() {}
	//default operations to do intra-class when component is on focus
	public void onFocus() {}
	//default operations to do intra-class when component is out of focus
	public void outOfFocus() {}
	//default operations to do intra-class when component is clicked either pressed, down, released or up
	public void onClick(int modality) {}
	
	//method to check wheter mouse is over the component or not
	public boolean isHovered(Vector2 mousePos) {
		return false;
	}
}
