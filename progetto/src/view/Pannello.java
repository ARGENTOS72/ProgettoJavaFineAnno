//CODICE DI PASQUA
/*
package view;

import java.util.Iterator;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.core.ray.Ray;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

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
    private int sumW;
    private int totPadding;
    private int padding;
    private int prodottoConsigliatoY;
    private int prodottoConsigliatoHeight;
    private int prodottoHeight;
    private Camera2D camera;
    private int scrollSpeed;
    
    public Pannello() {
        this.ray = Finestra.getRaylib();
        sumW=0; totPadding=0; padding=0; scrollSpeed=10;
        
        camera = new Camera2D(new Vector2(0,0), new Vector2(0,0), 0, 0);
        
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
        headerWidth = screenWidth;
        headerHeight = screenHeight / 6;
        barPosX = screenWidth/30;
        barPosY = screenHeight/14;
       
        categorie = new TextButton[5];
      
        searchBar = new SearchBar(barPosX, barPosY, screenWidth-(screenWidth/30*2), 4, 0.06f, 10, (byte) 80, 32, 2, Color.WHITE, Color.BLACK);
        txtBtn = new TextButton(barPosX, barPosY - searchBar.getHeight(), true, 0, Color.PINK, Color.PINK, Color.PINK, 0, 40, "Kirizon", Color.WHITE, Color.RED, Color.DARKGREEN);
        
        for (int i = 0; i < 5; i++) {
			categorie[i] = new TextButton(i*(screenWidth/5) ,headerHeight , true, 0, Color.VIOLET, Color.PINK, Color.PINK, 10, 30, "Kirizon", Color.WHITE, Color.RED, Color.DARKGREEN);
			sumW += categorie[i].getWidth();
        }
        
        totPadding = screenWidth-sumW;
        padding = totPadding/categorie.length;
        
        for (int i = 0; i < 5; i++) {
			categorie[i].setWidth(padding);
        }
        
        prodottoConsigliatoY = headerHeight+(categorie[0].getHeight());
        prodottoConsigliatoHeight = screenHeight/4;
        prodottoHeight = screenHeight/6;
        
        
    }

    public void draw() {
    	//camera.target.y=scrollSpeed*rCore.GetMouseWheelMove();
		ray.core.BeginMode2D(camera);
    	
		rShapes.DrawRectangleRec(new Rectangle(0, 0, headerWidth, headerHeight), Color.PINK);
    	searchBar.draw();
        txtBtn.draw();
        
        for (int i = 0; i < categorie.length; i++) {
			categorie[i].draw();
        }
        rShapes.DrawRectangleRec(new Rectangle(0, prodottoConsigliatoY, screenWidth, prodottoConsigliatoHeight), Color.GOLD);
        for (int i = 0; i < 10; i++) {
        	rShapes.DrawRectangleRec(new Rectangle(0, (prodottoConsigliatoY+prodottoConsigliatoHeight)+(i*prodottoHeight) , screenWidth, prodottoHeight), Color.MAROON);
		}
        
        //ray.core.EndMode2D();
    }

    public void registraEventi(Controller c) {
        txtBtn.setName("textBtn");
        c.addListenerTo(txtBtn);
        
        searchBar.setName("search bar");
        c.addListenerTo(searchBar);
        searchBar.setListener(c);
        
        for (int i = 0; i < categorie.length; i++) {
			categorie[i].setName(categorie[i].getText());
			c.addListenerTo(categorie[i]);
        }
    }
} 
*/
package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
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
    
    public Pannello() {
    	//init vars
        this.ray = Finestra.getRaylib();
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
        headerWidth = screenWidth;
        headerHeight = screenHeight / 6;
        barPosX = screenWidth/30;
        barPosY = screenHeight/14;
        
        //search bar
        texture = new Texture2D("textures/SearchIcon.png");
        searchBar = new SearchBar(barPosX, barPosY, screenWidth-(screenWidth/30*2), 60, 0.5f, 3, (byte) 80, 
        		32, texture);
        
        //sample button
        txtBtn = new TextButton(10, 10, true, 0f, Color.PINK, Color.PINK,
        		Color.PINK, 0, 40, "Kirizon", Color.WHITE, Color.RED, Color.DARKGREEN);
        
        //array of the buttons' categories
        categorie = new TextButton[5];
        for (int i = 0; i < 5; i++) {
			categorie[i] = new TextButton(txtBtn);
			categorie[i].setPadding(5);

			if(i == 0) categorie[i].setLocation(0, 200);
			else categorie[i].setLocation(categorie[i-1].getX()+categorie[i-1].getWidth()+10, 200);
		}
        
        //TEMP TODO
        Product pp = new Product(911.01, "Kiriko 2", "semplice carta da sedere", 0, 10101);
        p = new Prodotto(300, 300, 200, 300, 0.2f, texture, pp, 20, 15, Color.WHITE, Color.YELLOW, Color.GOLD);
    }

    public void draw() {
    	//header
    	rShapes.DrawRectangleRec(new Rectangle(0, 0, headerWidth, headerHeight), Color.PINK);
    	txtBtn.draw();
    	searchBar.draw();
        
        //body
        rShapes.DrawRectangleRec(new Rectangle(0, headerHeight+(categorie[0].getHeight()), screenWidth, screenHeight/4), Color.GOLD);
        for (TextButton b : categorie) b.draw();
        
        //TEMP TODO
        p.draw();
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
        p.setName("prodotto");
        p.addListener(c);
    }
}
