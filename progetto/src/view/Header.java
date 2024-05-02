package view;

import java.util.Iterator;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

public class Header {
	private Raylib ray;
	private TextButton txtBtn;
    private Texture2D texture;
    private SearchBar searchBar;
    private TextButton[] categorie;
    private int headerWidth;
    private int headerHeight;
    private int nCategorie;
	
    public Header(int screenWidth, int screenHeight, int headerWidth, int headerHeight, int barPosX, int barPosY, int nCategorie) {
    	this.ray = Finestra.getRaylib();
        this.headerWidth = headerWidth;
        this.headerHeight = headerHeight;
        this.nCategorie = nCategorie;
        
      //search bar
        texture = new Texture2D("textures/SearchIcon.png");
        searchBar = new SearchBar(barPosX, barPosY, screenWidth-(screenWidth/30)*2, 60, 0.5f, 3, (byte) 80, 
        		32, texture);
        
        //Home button
        txtBtn = new TextButton(10, 10, true, 0f, Color.PINK, Color.PINK,
        		Color.PINK, 0, 40, "Kirizon", Color.WHITE, Color.VIOLET, new Color(87, 10, 142, 255));
        
        //array of the buttons' categories
        categorie = new TextButton[nCategorie];
        for (int i = 0; i < nCategorie; i++) {
			categorie[i] = new TextButton(txtBtn);
			categorie[i].setPadding(5);
		}
        
        for (int i = 0; i < nCategorie; i++) {
        	categorie[i].setLocation(0, headerHeight-categorie[0].getHeight());
		}
        
        //center-grow alignment of categories btn 
        GraphicComponentAligner.alignX(categorie, GraphicComponentAligner.CENTER,
        		GraphicComponentAligner.DISTRIBUTE, 0, screenWidth, -1);
	
    }
    
    public void draw() {
    	rShapes.DrawRectangleRec(new Rectangle(0, 0, headerWidth, headerHeight), Color.PINK);
    	txtBtn.draw();
    	searchBar.draw();
        for (TextButton b : categorie) b.draw();
    }
 
    public void registraEventiHeader(Controller c) {
    	txtBtn.setName("textBtn");
        txtBtn.addListener(c);
        
        searchBar.setName("searchBar");
        searchBar.addListener(c);
        
        for (int i = 0; i < categorie.length; i++) {
        	categorie[i].setName("categoria "+(i+1));
			categorie[i].addListener(c);
        }
    }
    
	public int getWidth() {
		return headerWidth;
	}

	public int getHeight() {
		return headerHeight;
	}

	public int getnCategorie() {
		return nCategorie;
	}
    
}
