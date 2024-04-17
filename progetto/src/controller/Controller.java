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
import view.SearchBar;

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
		if(focusedComponent != null) lastFocusedComponent = focusedComponent;
		focusedComponent = null;
		
		handleFocusedComponent();//do smth when a component is on focus
	}
	
	//onHover, onFocus
	private void handleComponentsActions() {
		for (GraphicComponent gc : components) {
			if (gc.isHovered(mousePos)) {//is Hovered
				hoveredComponent = gc;
				
				//get who is it
				if (gc.getName().equals("btn")) {
					((Button) gc).setBackgroundColor(Color.PINK);
					
					if(Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {//is Clicked
						((Button) gc).setText(
								(((Button) gc).getText().equals("Sono un bottone") ? "Mi hai sbottonato!"
										: "Sono un bottone"));
						this.focusedComponent = gc;// when clicking it gains focus
					}

				} else if (gc.getName().equals("btnsex")) {
					((Button) gc).setBackgroundColor(Color.DARKGRAY);

					if (Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {

					}
				} else if (gc.getName().equals("search bar")) {
					if (Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {// is Clicked
						this.focusedComponent = gc;// when clicking it gains focus
					}

				}
			}
			// else {
            //     if (Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {
            //         if (this.focusedComponent instanceof SearchBar) {
            //             ((SearchBar) this.focusedComponent).resetSearchBar();
            //         }
                    
            //         this.focusedComponent = null;
            //     }
            // }
		}
	}
	
	//out of hover
	private void handleOutOfHover() {
		if(lastHoveredComponent == null || lastHoveredComponent.equals(hoveredComponent)) return;
		
		//who is it
		if(lastHoveredComponent.getName().equals("btn")) {
			((Button) lastHoveredComponent).setBackgroundColor(Color.BLACK);
		} else if (lastHoveredComponent.getName().equals("btnsex")) {
			((Button) lastHoveredComponent).setBackgroundColor(Color.GRAY);
		}
	}
	
	//out of focus
	private void handleOutOfFocus() {
		if(lastFocusedComponent == null || lastFocusedComponent.equals(focusedComponent)) return;
		
		//who is it
		//...
	}
	
	//onFocus
	private void handleFocusedComponent() {
		if(lastFocusedComponent == null) return;
		
		if(lastFocusedComponent.getName().equals("search bar")) {
			((SearchBar) lastFocusedComponent).handleKeyBoardEvents();
		}
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
