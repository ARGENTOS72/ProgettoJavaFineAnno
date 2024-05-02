package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import controller.Controller;

public class ListenableGraphicComponent extends GraphicComponent implements Listenable {
	private Listenable listener;//in oreder to have settable Listenable methods
	private Color hoveredColor, clickedColor, focussedColor, currentColor;
	
	//Constructor -------------------------------
	public ListenableGraphicComponent(ListenableGraphicComponent lgc) {
		super(lgc.getX(), lgc.getY(), lgc.getWidth(), lgc.getHeight(), lgc.getColor());
		
		this.hoveredColor = lgc.getHoveredColor();
		this.clickedColor = lgc.getClickedColor();
		this.focussedColor = lgc.focussedColor;
		this.currentColor = getColor();
		
		this.listener = getDefaultInterationAction();
	}
	
	public ListenableGraphicComponent(Rectangle bounds, Color color, Color hoveredColor, Color clickedColor, Color focussedColor) {
		super(bounds, color);
		this.currentColor = getColor();
		this.hoveredColor = hoveredColor;
		this.clickedColor = clickedColor;
		this.focussedColor = focussedColor;
		
		this.listener = getDefaultInterationAction();
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
	
	public Listenable getInterationActions() {
		return listener;
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
	
	public void setInterationAction(Listenable listener) {
		this.listener = listener;
	}
	
	//implements --------------------------------------------------------------
	@Override
	public void onHover() {
		listener.onHover();
	}
	
	@Override
	public void outOfHover() {
		listener.outOfHover();
	}
	
	@Override
	public void onFocus() {
		listener.onFocus();
	}
	
	@Override
	public void outOfFocus() {
		listener.outOfFocus();
	}
	
	@Override
	public void onClick(int modality) {
		listener.onClick(modality);
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return listener.isHovered(mousePos);
	}
	
	//default Listenable -------------------------------------
	public Listenable getDefaultInterationAction() {
		return new Listenable() {
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
		};
	}
	
	//add & remove listener ----------------------------------
	//default operations to do when this Listenable Object need to add a listener
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}

	//default operations to do when this Listenable Object need to remove a listener
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
