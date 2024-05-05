package view;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class Pannello {
    private Raylib ray;

    private LoadingView loadingView;
    private Header header;
    private HomePage homePage;
    private ProductView productView;
    private ProductsSearched productsSearched;
    
    private Texture2D texture;
    private int screenWidth;
    private int screenHeight;
    private Camera2D camera;

    private CurrentView currentView;
    
    public Pannello() {
    	//init vars
        this.ray = Finestra.getRaylib();
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
    
        texture = new Texture2D("textures/SearchIcon.png");
        
        loadingView = new LoadingView(screenWidth, screenHeight);
        header = new Header(screenWidth, screenHeight, screenWidth, screenHeight/5, screenWidth/30, screenHeight/14, 6);
        homePage = new HomePage(screenWidth, screenHeight, header.getHeight(), screenHeight/2, 10);
        productView = new ProductView(texture, null, screenWidth, screenHeight, header.getHeight());
        productsSearched = new ProductsSearched(header.getHeight(), screenWidth, screenHeight);
       
	    camera = new Camera2D(new Vector2(0, 0), new Vector2(0, 0), 0f, 1f);
        
        currentView = CurrentView.HomePage;
    }
    
    //Draw Panel
    public void draw() {
    	ray.core.BeginMode2D(camera);

    	header.draw();

        switch (currentView) {
            case CurrentView.Loading: {
                loadingView.draw();
                
                break;
            }

            case CurrentView.HomePage: {
                homePage.draw();
                
                break;
            }

            case CurrentView.ProductView: {
                productView.draw();
                
                break;
            }

            case CurrentView.ProductsSearched: {
                productsSearched.draw();

                break;
            }
        }
	    
        ray.core.EndMode2D();
    }

    public void showProduct(Product prodotto, Controller c) {
        productView.setProdotto(prodotto);

        currentView = CurrentView.ProductView;

        productView.registraEventiProdotto(c);
        homePage.rimuoviEventiHome(c);
        productsSearched.rimuoviEventiProductsSearched(c);

        camera.target.y = 0;
    }

    public void showHomePage(Controller c) {
        currentView = CurrentView.HomePage;

        productView.rimuoviEventiProdotto(c);
        homePage.registraEventiHome(c);
        productsSearched.rimuoviEventiProductsSearched(c);
    
        camera.target.y = 0;
    }

    public void showProductsSearched(ArrayList<Product> prodotti, Controller c) {
        productsSearched.loadSearchedProdotti(prodotti);

        currentView = CurrentView.ProductsSearched;

        productView.rimuoviEventiProdotto(c);
        homePage.rimuoviEventiHome(c);
        productsSearched.registraEventiProductsSearched(c);

        camera.target.y = 0;
    }

    public String getQuery() {
        return header.getQuery();
    }
    
    //Update Scroll
    public void aggiornaCameraY(float y) {
		camera.target.y -= y;
		camera.target.y = Math.max(0, camera.target.y);
    }
    
    public Camera2D getCamera() {
    	return camera;
    }

    public void loadHomeProducts(ArrayList<Product> products) {
        homePage.loadHomeProducts(products);
    }

    public void registraEventi(Controller c) {
       	header.registraEventiHeader(c);
        homePage.registraEventiHome(c);
        //productView.registraEventiProdotto(c);
    	//loadingView.registraEventi(c);
    }

    public int getHomePageNProdotti() {
        return homePage.getNProdotti();
    }
}
