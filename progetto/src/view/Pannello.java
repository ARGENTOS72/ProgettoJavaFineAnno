package view;

import java.lang.annotation.Target;
import java.util.Vector;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;

import controller.Controller;
import model.Product;

public class Pannello {
    private Raylib ray;

    private Button btn1, btn2;
    private TextButton txtBtn;
    private Texture2D texture;
    private TextureButton textureBtn;
    private SearchBar searchBar;
    private int screenWidth;
    private int screenHeight;
    private TextButton[] categorie;
    private int headerWidth;
    private int headerHeight;
    private int barPosX;
    private int barPosY;
    private int nCategorie;
    private Prodotto p;//TEMP TODO
    private Prodotto[] prodotti;
    private Camera2D camera;
    private int prodottoConsigliatoY;
    private int prodottoConsigliatoHeight;
    private int prodottoWidth;
    
    public Pannello() {
    	//init vars
        this.ray = Finestra.getRaylib();
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
        headerWidth = screenWidth;
        headerHeight = screenHeight / 6;
        barPosX = screenWidth/30;
        barPosY = screenHeight/14;
        prodottoWidth = screenWidth/4;
        int sumW = 0;
        int totPadding = 0;
        int padding = 0;
        
        prodotti = new Prodotto[8];
        
        camera = new Camera2D(new Vector2(0,0), new Vector2(0,0), 0f, 1f);
        
        //search bar
        texture = new Texture2D("textures/SearchIcon.png");
        searchBar = new SearchBar(barPosX, barPosY, screenWidth-(screenWidth/30)*2, 60, 0.5f, 3, (byte) 80, 
        		32, texture);
        
        //sample button
        txtBtn = new TextButton(10, 10, true, 0f, Color.PINK, Color.PINK,
        		Color.PINK, 0, 40, "Kirizon", Color.WHITE, Color.RED, Color.DARKGREEN);
        
        //array of the buttons' categories
        categorie = new TextButton[5];
        for (int i = 0; i < 5; i++) {
			categorie[i] = new TextButton(txtBtn);
			categorie[i].setPadding(5);
			categorie[i].setColors(Color.VIOLET, Color.DARKPURPLE, Color.PINK);
			sumW+=categorie[i].getWidth();
			
			if(i == 0) categorie[i].setLocation(0, headerHeight);
			else categorie[i].setLocation(categorie[i-1].getX()+categorie[i-1].getWidth()+10, headerHeight);
		}
        
        totPadding = screenWidth - sumW;
        padding = totPadding/ (categorie.length-1);
        
        categorie[0].setX(0);
        
        for (int i = 1; i < categorie.length; i++) {
        	int precedentX = categorie[i - 1].getX();
        	int precedentWidth = categorie[i - 1].getWidth();
        	categorie[i].setX(precedentX + precedentWidth + padding);
		}
        
        prodottoConsigliatoY = headerHeight+(categorie[0].getHeight());
        prodottoConsigliatoHeight = screenHeight/3;
        
        //TEMP TODO prodotti temp
        Product pp = new Product(911.01, "Kiriko 2", "semplice carta das sedere", 0);
        //p = new Prodotto(0, (prodottoConsigliatoY+prodottoConsigliatoHeight), prodottoWidth, 0f, texture, pp, 20, 15, 10, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
        
        for (int i = 0; i < 8; i++) {
        		prodotti[i] = new Prodotto(0, 0, prodottoWidth, 0f, texture, pp, 20, 15, 10, Color.VIOLET, Color.DARKPURPLE, Color.PINK);
        }
        
        int g=0;
        for (int i = 0; i < 4; i++) {
        	for(int j = 0; j < 2; j++) {
        		prodotti[g].setLocation(0+(i*prodottoWidth), (prodottoConsigliatoY+prodottoConsigliatoHeight)+(j*prodotti[g].getHeight()));
        		g++;
        	}
        }
    }

    public void draw() {
    	ray.core.BeginMode2D(camera);
    	//header
    	rShapes.DrawRectangleRec(new Rectangle(0, 0, headerWidth, headerHeight), Color.PINK);
    	txtBtn.draw();
    	searchBar.draw();
        
        //body
        rShapes.DrawRectangleRec(new Rectangle(0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight), Color.GOLD);
        for (TextButton b : categorie) b.draw();
        
        //TEMP TODO
        //p.draw();
        
        for (int i = 0; i < 8; i++) {
        	prodotti[i].draw();
		}
        
        ray.core.EndMode2D();
    }
    
    public void aggiornaCameraY(float y) {
    		camera.target.y -= y;
    		camera.target.y = Math.max(0, camera.target.y);
    }

    public void registraEventi(Controller c) {
        txtBtn.setName("textBtn");
        txtBtn.addListener(c);
        
        searchBar.setName("searchBar");
        searchBar.addListener(c);
        
        for (int i = 0; i < categorie.length; i++) {
        	categorie[i].setName("categoria "+(i+1));
			categorie[i].addListener(c);
        }
        
        //TODO
        //p.setName("prodotto");
        //p.addListener(c);
        for (int i = 0; i < 8; i++) {
        	prodotti[i].setName("prodotto"+(i+1));
        	prodotti[i].addListener(c);
		}
    }
}

