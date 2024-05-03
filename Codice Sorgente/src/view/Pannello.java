package view;

import java.util.ArrayList;
import java.util.Arrays;

import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import com.raylib.java.core.Color;
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
    private TextureButton textureBtn;
    private int screenWidth;
    private int screenHeight;
    private Camera2D camera;
    
    public Pannello() {
    	//init vars
        this.ray = Finestra.getRaylib();
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
    
        texture = new Texture2D("textures/SearchIcon.png");
        
        String[] categorieTemp = {"Cacca", "Cacca"};
        ArrayList<String> categorie = new ArrayList<String>(Arrays.asList(categorieTemp));
        Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", categorie, 0);
        
        loadingView = new LoadingView(screenWidth, screenHeight);
        header = new Header(screenWidth, screenHeight, screenWidth, screenHeight/5, screenWidth/30, screenHeight/14, 6);
        homePage = new HomePage(screenWidth, screenHeight, header.getHeight(), screenHeight/2, 10);
        productView = new ProductView(texture, pp, screenWidth, screenHeight, header.getHeight());
        productsSearched = new ProductsSearched(header.getHeight(), screenWidth, screenHeight, 11);
       
	camera = new Camera2D(new Vector2(0,0), new Vector2(0,0), 0f, 1f);
        
    }
    
    //Draw Panel
    public void draw() {
    	ray.core.BeginMode2D(camera);
	Finestra.getRaylib().core.ClearBackground(new Color(141, 255, 248, 255));
	    
    	//loadingView.draw();
    	header.draw();
    	homePage.draw();
//    	productView.draw();
    	//productsSearched.draw();
	    
        ray.core.EndMode2D();
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
//    	productView.registraEventiProdotto(c);
    	//loadingView.registraEventi(c);
    }
}
