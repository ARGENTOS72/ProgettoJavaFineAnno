package view;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;

import controller.Controller;
import model.Product;

public class Pannello {
    private Raylib ray;

    private int screenWidth, screenHeight;
    private Camera2D camera;
    private int maxCameraHeight;
    
    private LoadingView loadingView;
    private Header header;
    private HomePage homePage;
    private ProductView productView;
    private Footer footer;
    private ProductsSearched productsSearched;

    private CurrentView currentView;
    
    public Pannello() {
    	//init vars
        this.ray = Finestra.getRaylib();
        screenWidth = Finestra.unscaledScreenWidth;
        screenHeight = Finestra.unscaledScreenHeight;
        
        //init views
        loadingView = new LoadingView(screenWidth, screenHeight);
        header = new Header(screenWidth, screenHeight, screenWidth, screenHeight / 5, screenWidth / 30, screenHeight / 14, 6);
        homePage = new HomePage(screenWidth, screenHeight, header.getHeight(), screenHeight / 2, 10);
        footer = new Footer(homePage.getHeight(), screenWidth, screenHeight);
        productView = new ProductView(screenWidth, screenHeight, header.getHeight());
        productsSearched = new ProductsSearched(header.getHeight(), screenWidth, screenHeight);
       
        currentView = CurrentView.Loading;
        
        //init camera
	    camera = new Camera2D(new Vector2(0, 0), new Vector2(0, 0), 0f, 1f);
	    maxCameraHeight = footer.getHeight() - screenHeight;
    }
    
    //Draw Panel
    public void draw() {
    	ray.core.BeginMode2D(camera);
	    ray.core.ClearBackground(new Color(141, 255, 248, 255));

        switch (currentView) {
            case Loading: {
                loadingView.draw();
                
                break;
            }

            case HomePage: {
            	header.draw();
                homePage.draw();
                footer.draw();
                
                break;
            }

            case ProductView: {
            	header.draw();
                productView.draw();
                footer.draw();
                
                break;
            }

            case ProductsSearched: {
            	header.draw();
                productsSearched.draw();
                footer.draw();

                break;
            }
        }
	    
        ray.core.EndMode2D();
    }

    public void showProduct(Product prodotto, Controller c) {
        productView.setProdotto(prodotto);

        currentView = CurrentView.ProductView;

        productView.registraEventi(c);
        homePage.rimuoviEventi(c);
        productsSearched.rimuoviEventi(c);

        resetCameraY();
    }

    public void registraHeaderFooter(Controller c) {
        header.registraEventiHeader(c);
        footer.registraEventiFoot(c);
        loadingView.cancellaEventi(c);
    }

    public void showHomePage(Controller c) {
        currentView = CurrentView.HomePage;

        productView.rimuoviEventi(c);
        homePage.registraEventi(c);
        productsSearched.rimuoviEventi(c);
        
        resetCameraY();
    }

    public void showProductsSearched(ArrayList<Product> prodotti, Controller c) {
        productsSearched.rimuoviEventi(c); // Reload
        
        productsSearched.loadSearchedProdotti(prodotti);
        
        currentView = CurrentView.ProductsSearched;

        productView.rimuoviEventi(c);
        homePage.rimuoviEventi(c);
        productsSearched.registraEventi(c);

        resetCameraY();
    }

    public void resetCameraY() {
        camera.target.y = 0;
    }

    public void loadCategorie(ArrayList<String> categorie) {
        header.loadCategorie(categorie);
    }

    public String getQuery() {
        return header.getQuery();
    }
    
    //Update Scroll
    public void aggiornaCameraY(float y) {
		camera.target.y -= y;
		camera.target.y = Math.max(0, camera.target.y);
		camera.target.y = Math.min(maxCameraHeight, camera.target.y);
    }
    
    public Camera2D getCamera() {
    	return camera;
    }

    public void loadHomeProducts(ArrayList<Product> products) {
        homePage.loadHomeProducts(products);
    }

    public void registraEventi(Controller c) {
        loadingView.registraEventi(c);
    }

    public int getHomePageNProdotti() {
        return homePage.getNProdotti();
    }

    public int getCodiceProdotto() {
        return productView.codiceProdotto();
    }

    public int getQuantitaProdotto() {
        return productView.quantitaProdotto();
    }

    public void disabilitaBottoneAcquista(Controller c) {
        productView.disabilitaBottoneAcquista(c);
    }
}
