package view;

import com.raylib.java.raymath.Vector2;

public interface Listenable {
	public static final int PRESSED = 0, DOWN = 1, RELEASED = 2, UP = 3;

	//default operations to do intra-class when component is on hover
	public void onHover();
	
	//default operations to do intra-class when component is out of hover
	public void outOfHover();
	
	//default operations to do intra-class when component is on focus
	public void onFocus();
	
	//default operations to do intra-class when component is out of focus
	public void outOfFocus();
	
	//default operations to do intra-class when component is clicked either pressed, down, released or up
	public void onClick(int modality);
	
	//method to check wheter mouse is over the component or not
	public boolean isHovered(Vector2 mousePos);
}
