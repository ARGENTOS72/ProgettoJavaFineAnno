package controller;

import java.util.ArrayList;

import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Mouse.MouseButton;
import com.raylib.java.raymath.Vector2;

import model.Db;
import view.Button;
import view.LastView;
import view.Finestra;
import view.GraphicAnimation;
import view.GraphicComponent;
import view.ListenableGraphicComponent;
import view.Pannello;
import view.Prodotto;
import view.SearchBar;

public class Controller {
	private Finestra f;
	private Pannello p;
	private Db db;
	private ArrayList<ListenableGraphicComponent> components; // GraphicComponents that has added listener
	private ArrayList<GraphicAnimation> animations; // GraphicAnimation that has added updater
	private ListenableGraphicComponent hoveredComponent, lastHoveredComponent, focusedComponent, lastFocusedComponent;
	private LastView lastView;
	private String lastQuery;
	private Vector2 mousePos;
	private float deltaTime;
	private int scrollMultiplier;
	
	public Controller(Finestra f, Db db) {
		this.components = new ArrayList<>();
		this.animations = new ArrayList<>();
		this.lastHoveredComponent = null;
		this.hoveredComponent = null;
		this.focusedComponent = null;
		this.lastFocusedComponent = null;
		this.lastQuery = null;
		this.f = f;
		this.p = f.getPannello();
		this.db = db;
		scrollMultiplier = 50;
		lastView = LastView.HomePage;
		
		p.registraEventi(this);
	}
	
	// Fundamental methods ----------------------------------------------------------------
	// update controler
	public void update() {
		//update mouse position
		mousePos = rCore.GetMousePosition();
		mousePos.x += (float) (mousePos.x*(double)(Finestra.unscaledScreenHeight) / (double)(rCore.GetScreenHeight()));
		mousePos.y += (float) (mousePos.y*(double)(Finestra.unscaledScreenWidth) / (double)(rCore.GetScreenWidth()));
		
		f.setMousePos((int) mousePos.x, (int) mousePos.y);
		
		mousePos = Finestra.getRaylib().core.GetScreenToWorld2D(mousePos, p.getCamera());
		//get deltatime
		deltaTime = rCore.GetFrameTime();

		//update scroll
		p.aggiornaCameraY(scrollMultiplier * rCore.GetMouseWheelMove());
		
		handleComponentsActions(); // do smth when a component is hovered or clicked
		handleOutOfHover(); // do smth when a component is not anymore hovered
		handleOutOfFocus(); // do smth when a component is not anymore on focus

		// update hovered & focused components
		lastHoveredComponent = hoveredComponent;
		hoveredComponent = null;

		if ((lastFocusedComponent == null && focusedComponent != null) ||
			(lastFocusedComponent != null && !lastFocusedComponent.equals(focusedComponent)))
			lastFocusedComponent = focusedComponent;
		
		handleFocusedComponent(); // do smth when a component is on focus
		
		updateAnimations(); // update all animations
	}
	
	// onHover, onFocus
	private void handleComponentsActions() {
		for (ListenableGraphicComponent lgc : components) {
			if (lgc.isHovered(mousePos)) { // is Hovered
				hoveredComponent = lgc;
				
				lgc.onHover(); // do default operations when hovered

				if (Finestra.getRaylib().core.IsMouseButtonReleased(MouseButton.MOUSE_BUTTON_LEFT)) {
					if (lgc.getName().equals("productview.back")) {
						switch (lastView) {
							case HomePage: {
								p.showHomePage(this);
								
								break;
							}
	
							case ProductsSearched: {
								if (lastQuery.startsWith("categoria-")) {
									p.showProductsSearched(db.prodottoCategoria(lastQuery.substring(10)), this);
								} else {
									p.showProductsSearched(db.cercaProdotti(lastQuery), this);
								}
								
								break;
							}
						}
	
						return;
					}

					if (lgc.getName().equals("header.searchBar.sendBtn")) {
						String query = p.getQuery();
						
						lastQuery = query;

						p.showProductsSearched(db.cercaProdotti(query), this);
						lastView = LastView.ProductsSearched;

						return;
					}
	
					if (lgc.getName().equals("header.logo")) {
						if (lastView == LastView.ProductsSearched) {
							p.showHomePage(this);
						
							lastView = LastView.HomePage;
	
							return;
						}
					}

					if (lgc.getName().equals("footer.gotoHead")) {
						p.resetCameraY();
					}

					if (lgc.getName().equals("productview.acquista")) {
						db.cambiaQuantita(p.getCodiceProdotto(), -1);
						
						if (p.getQuantitaProdotto() == 0) {
							p.disabilitaBottoneAcquista(this);
						}
					}

					for (int i = 0; i < db.nProdotti(); i++) {
						if (lgc.getName().equals("prodotto" + i)) {
							p.showProduct(db.getProdotti().get(i), this);
	
							return;
						}
					}

					for (String categorie : db.categorieProdotti()) {
						if (lgc.getName().equals(categorie)) {
							p.showProductsSearched(db.prodottoCategoria(categorie), this);

							lastQuery = "categoria-" + categorie;

							lastView = LastView.ProductsSearched;

							return;
						} 
					}
				}
				
				// get who is it
				if (lgc instanceof Button || lgc instanceof SearchBar || lgc instanceof Prodotto) {
					if (Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)) {
						this.focusedComponent = lgc; // when clicking it gains focus
					}

					if (rCore.IsMouseButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
						lgc.onClick(ListenableGraphicComponent.DOWN);
					}
				}
			}
		}
		
		// lose focus when clicking out of any GraphicComponent
		if (hoveredComponent == null && Finestra.getRaylib().core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT))
			focusedComponent = null;
		
		//change pointer
		if(hoveredComponent == null) f.setPointerMode(Finestra.NORMAL);
		else f.setPointerMode(Finestra.ONHOVER);
	}
	
	// out of hover
	private void handleOutOfHover() {
		if(lastHoveredComponent == null || lastHoveredComponent.equals(hoveredComponent)) return;

		// use default method outOfHover
		lastHoveredComponent.outOfHover();
		
		// ...
		// who is it
	}
	
	// out of focus
	private void handleOutOfFocus() {
		if (lastFocusedComponent == null || lastFocusedComponent.equals(focusedComponent)) return;
		
		// use default method outOfFocus
		lastFocusedComponent.outOfFocus();
				
		// who is it
		// ...
	}
	
	//onFocus
	private void handleFocusedComponent() {
		if (lastFocusedComponent == null) return;
		
		// use default method onFocus
		lastFocusedComponent.onFocus();
		
		// who is it
		// ...
	}
	
	public void updateAnimations() {
		for (GraphicAnimation ga : animations) {
			ga.update(deltaTime);
		}
	}
	
	//external utilities --------------------------------------------------------------------------------
	public void addListenerTo(ListenableGraphicComponent lgc) throws ControllerException {
		if (lgc == null)
			throw new ControllerException(
					"Cannot add listener to null " + GraphicComponent.class.getSimpleName());

		if (lgc.getName() == null)
			throw new ControllerException(
					"Cannot add listener to " + GraphicComponent.class.getSimpleName() + " with null name");

		components.add(lgc);
	}

	public void removeListenerTo(ListenableGraphicComponent lgc) {
		components.remove(lgc);
	}

	public void removeListenerTo(String lgcName) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getName().equals(lgcName)) {
				components.remove(i);

				break;
			}
		}
	}
	
	public void addUpdaterTo(GraphicAnimation ga) throws ControllerException {
		if (ga == null)
			throw new ControllerException(
					"Cannot add updater to null " + GraphicComponent.class.getSimpleName());

		if (ga.getName() == null)
			throw new ControllerException(
					"Cannot add updater to " + GraphicComponent.class.getSimpleName() + " with null name");

		animations.add(ga);
	}

	public void removeUpdaterTo(GraphicAnimation ga) {
		animations.remove(ga);
	}

	public void removeUpdaterTo(String gaName) {
		for (int i = 0; i < animations.size(); i++) {
			if (animations.get(i).getName().equals(gaName)) {
				animations.remove(i);
				
				break;
			}
		}
	}
}
