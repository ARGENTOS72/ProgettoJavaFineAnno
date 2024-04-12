package view;

import com.raylib.java.raymath.Vector2;

public class GraphicComponent {//has an hitbox, isHovered
	private String name;
	
	public GraphicComponent() {
		this.name = null;
	}
	
	public GraphicComponent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isHovered(Vector2 mousePos) {
		return false;
	}
}
