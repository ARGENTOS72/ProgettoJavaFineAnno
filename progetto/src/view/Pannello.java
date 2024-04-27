package view;

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

    private Header header;
    private HomePage homePage;
    
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
    
        header = new Header(screenWidth, screenHeight, screenWidth, screenHeight/6, screenWidth/30, screenHeight/14, 5);
        homePage = new HomePage(screenWidth, screenHeight, header.getHeaderHeight(), screenHeight/3);
        
        camera = new Camera2D(new Vector2(0,0), new Vector2(0,0), 0f, 1f);
        
    }
    
    //Draw Panel
    public void draw() {
    	ray.core.BeginMode2D(camera);
   
    	header.draw();
    	homePage.draw();
        
        ray.core.EndMode2D();
    }
    
    //Update Scroll
    public void aggiornaCameraY(float y) {
		camera.target.y -= y;
		camera.target.y = Math.max(0, camera.target.y);
		camera.target.y = Math.min(screenHeight, camera.target.y);
    }

    public void registraEventi(Controller c) {
    	header.registraEventiHeader(c);
        homePage.registraEventiHome(c);
    }
}
