package view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;

import controller.Controller;

public class GraphicAnimation extends GraphicComponent implements Animable {
	//Constructor -------------------------------
	public GraphicAnimation(Rectangle bounds, Color color) {
		super(bounds, color);
	}
	
	public GraphicAnimation(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	//implements --------------------------------------------
	@Override
	public void update(float deltaTime) {}

	@Override
	public void addUpdater(Controller c) {
		
		
	}

	@Override
	public void removeUpdater(Controller c) {
		// TODO Auto-generated method stub
		
	}
	
}
