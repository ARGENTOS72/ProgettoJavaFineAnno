package controller;

import java.util.ArrayList;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Mouse.MouseButton;
import com.raylib.java.raymath.Vector2;

import view.Button;
import view.Finestra;
import view.GraphicComponent;
import view.Pannello;
import view.Prodotto;
import view.SearchBar;
import view.TextButton;

public class Controller {
	private Pannello p;
	private ArrayList<GraphicComponent> components;//GraphicComponents that has added action listener
	private GraphicComponent hoveredComponent, lastHoveredComponent, focusedComponent, lastFocusedComponent;
	private Vector2 mousePos;

	public Controller(Pannello p) {
		this.components = new ArrayList<>();
		this.lastHoveredComponent = null;
		this.hoveredComponent = null;
		this.focusedComponent = null;
		this.lastFocusedComponent = null;
		this.p = p;
		p.registraEventi(this);
	}
	
	//Fundamental methods ----------------------------------------------------------------
	//update controler
	public void update() {
		mousePos = rCore.GetMousePosition();//update mouse position
		
		handleComponentsActions();//do smth when a component is hovered or clicked
		handleOutOfHover();//do smth when a component is not anymore hovered
		handleOutOfFocus();//do smth when a component is not anymore on focus

		//update hovered & focused components
		lastHoveredComponent = hoveredComponent;
		hoveredComponent = null;
		if((lastFocusedComponent == null && focusedComponent != null) ||
			(lastFocusedComponent != null && !lastFocusedComponent.equals(focusedComponent)))
			lastFocusedComponent = focusedComponent;
		
		handleFocusedComponent();//do smth when a component is on focus
		
	}
	
	//onHover, onFocus
	private void handleComponentsActions() {
		for (GraphicComponent gc : components) {
			if (gc.isHovered(mousePos)) {//is Hovered
				hoveredComponent = gc;
				gc.onHover();//do default operations when hovered
				
				//get who is it
				if (gc instanceof Button || gc instanceof SearchBar || gc instanceof Prodotto) {
					if (Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {
						this.focusedComponent = gc;// when clicking it gains focus
					}
					if(rCore.IsMouseButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
						gc.onClick(GraphicComponent.DOWN);
						
					}

				}
			}
		}
		
		//lose focus when clicking out of any GraphicComponent
		if(hoveredComponent == null && Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT))
			focusedComponent = null;
	}
	
	//out of hover
	private void handleOutOfHover() {
		if(lastHoveredComponent == null || lastHoveredComponent.equals(hoveredComponent)) return;
		
		//use default method outOfHover
		lastHoveredComponent.outOfHover();
		
		//who is it
		// ...
	}
	
	//out of focus
	private void handleOutOfFocus() {
		if(lastFocusedComponent == null || lastFocusedComponent.equals(focusedComponent)) return;
		
		//use default method outOfFocus
		lastFocusedComponent.outOfFocus();
				
		//who is it
		//...
	}
	
	//onFocus
	private void handleFocusedComponent() {
		if(lastFocusedComponent == null) return;
		
		//use default method onFocus
		lastFocusedComponent.onFocus();
		
		//who is it
		// ...
	}
	
	//external utilities --------------------------------------------------------------------------------
	public void addListenerTo(GraphicComponent gc) throws ControllerException {
		if (gc == null)
			throw new ControllerException(
					"Cannot add listener to null " + GraphicComponent.class.getSimpleName());
		if (gc.getName() == null)
			throw new ControllerException(
					"Cannot add listener to " + GraphicComponent.class.getSimpleName() + " with null name");

		components.add(gc);
	}

	public void removeListenerTo(GraphicComponent gc) {
		components.remove(gc);
	}

	public void removeListenerTo(String gcName) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getName().equals(gcName)) {
				components.remove(i);
				break;
			}
		}
	}
}
