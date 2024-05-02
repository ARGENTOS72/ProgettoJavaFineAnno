package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import controller.Controller;

public class GraphicAnimation extends GraphicComponent implements Animable {
	private Animable animation;//in order to have settable methods of Animable
	
	//Constructor -------------------------------
	public GraphicAnimation(GraphicAnimation ga) {
		super(ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight(), ga.getColor());
		
		this.animation = getDefaultAnimation();
	}
	
	public GraphicAnimation(Rectangle bounds, Color color) {
		super(bounds, color);
	}
	
	public GraphicAnimation(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}
	
	//getters & setters ---------------------------------------
	public Animable getAnimation() {
		return animation;
	}
	
	public void setAnimation(Animable animation) {
		this.animation = animation;
	}

	//implements --------------------------------------------
	@Override
	public void update(float deltaTime) {
		animation.update(deltaTime);
	}
	
	//default animation --------------------------------------
	public Animable getDefaultAnimation() {
		return new Animable() {
			@Override
			public void update(float deltaTime) {}
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
