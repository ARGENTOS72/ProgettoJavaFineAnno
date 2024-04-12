package controller;

import java.util.ArrayList;

import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

import view.Button;
import view.Finestra;
import view.GraphicComponent;
import view.Pannello;
import view.SearchBar;

public class Controller {
	private Pannello p;
	private ArrayList<GraphicComponent> components;//GraphicComponents that has added action listener
	private GraphicComponent focusedComponent;
	private Vector2 mousePos;
	
	public Controller(Pannello p) {
		this.components = new ArrayList<>();
		this.focusedComponent = null;
		this.p = p;
		p.registraEventi(this);
	}
	
	//Fundamental methods ----------------------------------------------------------------
	public void update() {//check events
		checkAllComponents();
		handleFocusedComponent();
	}
	
	public void checkAllComponents() {//check collision of the array 'components'
		mousePos = rCore.GetMousePosition();
		
		for(GraphicComponent gc : components) {
			if(gc.isHovered(mousePos)) {//is Hovered
				if(gc.getName().equals("btn")) {//get who is it
					
					if(Finestra.getRaylib().core.IsMouseButtonPressed(0)) {//is Clicked
						((Button) gc).setText(
							(((Button) gc).getText().equals("Sono un bottone")? "Mi hai sbottonato!" : "Sono un bottone")
						);
						this.focusedComponent = gc;//when clicking it gains focus
					}
					
				} else if(gc.getName().equals("search bar")) {
					
					if(Finestra.getRaylib().core.IsMouseButtonPressed(0)) {//is Clicked
						this.focusedComponent = gc;//when clicking it gains focus
					}
					
				}
			}
		}
	}
	
	public void handleFocusedComponent() {
		if(focusedComponent == null) return;
		
		if(focusedComponent.getName().equals("search bar")) {
			System.out.println("aaa");
			((SearchBar) focusedComponent).handleKeyBoardEvents();
		}
	}
	
	// --------------------------------------------------------------------------------
	public void addListenerTo(GraphicComponent gc) throws ControllerException {
		if(gc == null) 
			throw new ControllerException(
				"Cannot add listener to null "+GraphicComponent.class.getSimpleName()
			);
		if(gc.getName() == null)
			throw new ControllerException(
				"Cannot add listener to "+GraphicComponent.class.getSimpleName()+" with null name"
			);
		
		components.add(gc);
	}
	
	public void removeListenerTo(GraphicComponent gc) {
		components.remove(gc);
	}
	
	public void removeListenerTo(String gcName) {
		for(int i=0; i<components.size(); i++) {
			if(components.get(i).getName().equals(gcName)) {
				components.remove(i);
				return;
			}
		}
	}
}
