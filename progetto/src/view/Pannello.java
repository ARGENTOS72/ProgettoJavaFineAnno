package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
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
    
    public Pannello() {
        this.ray = Finestra.getRaylib();
        
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
		}
    }

    public void draw() {
    	rShapes.DrawRectangleRec(new Rectangle(0, 0, headerWidth, headerHeight), Color.PINK);
    	searchBar.draw();
        txtBtn.draw();
        
        for (int i = 0; i < categorie.length; i++) {
			categorie[i].draw();
        }
        rShapes.DrawRectangleRec(new Rectangle(0, headerHeight+(categorie[0].getHeight()), screenWidth, screenHeight/4), Color.GOLD);
    }

    public void registraEventi(Controller c) {
        txtBtn.setName("textBtn");
        c.addListenerTo(txtBtn);
        
        searchBar.setName("search bar");
        c.addListenerTo(searchBar);
        searchBar.setListener(c);
        
        /*for (int i = 0; i < categorie.length; i++) {
			categorie[i];
        }*/
    }
}
