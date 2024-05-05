package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.RenderTexture;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

public class Finestra {
	private static Raylib ray;
	
	//scren resolution used in Pannello
	public static int unscaledScreenWidth, unscaledScreenHeight;
	
	//unscaled resolution screen vars
	public Rectangle unscaledScreenBounds;
	private RenderTexture unscaledScreen;
    private Vector2 origin;

    //current screen resolution
    private Rectangle screenBounds;
    
    //static default texture
    private static Texture2D placeHolderTexture;
    
    private Pannello pannello;
    
    //pointer modality
  	public static final int NORMAL = 0, ONHOVER = 1;
  	//pointer
    private Vector2 mousePos;
    private Texture2D mouseTexture, normalMouseTexture, onhoverMouseTexture;
    private Rectangle mouseTextureBounds;
    private Rectangle mouseBounds;

    //Constructor ----------------------------------------
    public Finestra() {
        ray = new Raylib(0, 0, "Kiriko Amazon");
        ray.core.ToggleFullscreen();
        ray.core.SetTargetFPS(60);
        ray.core.HideCursor();
        
        screenBounds = new Rectangle(0, 0, rCore.GetScreenWidth(), rCore.GetScreenHeight());
        
        unscaledScreenWidth = 1920;
        unscaledScreenHeight = 1080;
        unscaledScreen = ray.textures.LoadRenderTexture(unscaledScreenWidth, unscaledScreenHeight);
        unscaledScreenBounds = new Rectangle(0, 0, 1920, -1080);
        
        origin = new Vector2(0, 0);
        
        //mouse init
        mousePos = new Vector2(0, 0);
        
        normalMouseTexture = new Texture2D("textures/DonutPointer.png");
        onhoverMouseTexture = new Texture2D("textures/DonutPointerHand.png");
        mouseTexture = normalMouseTexture;
        
        mouseTextureBounds = new Rectangle(0, 0, mouseTexture.width, mouseTexture.height);
        mouseBounds = new Rectangle(mousePos.x, mousePos.y, 45, 45);
        
        
        placeHolderTexture = new Texture2D("textures/PlaceHolder.jpg");

        pannello = new Pannello();
    }

    public void draw() {
    	//draw to unscled screen
    	ray.core.BeginTextureMode(unscaledScreen);
    	
    	ray.core.ClearBackground(Color.WHITE);
        
    	pannello.draw();
    	
    	rTextures.DrawTexturePro(mouseTexture, mouseTextureBounds, mouseBounds, origin, 0f, Color.WHITE);
    	
    	ray.core.EndTextureMode();
    	
    	//adapt unscaledscreen to the current screen
        ray.core.BeginDrawing();

        rTextures.DrawTexturePro(unscaledScreen.getTexture(), unscaledScreenBounds, screenBounds,
        	origin, 0f, Color.WHITE);

        ray.core.EndDrawing();
    }

    //gettres & setters -------------------------------
    public static Raylib getRaylib() {
        return ray;
    }

    public static Texture2D getPlaceHolderTexture() {
		return placeHolderTexture;
	}
    
    public Pannello getPannello() {
        return pannello;
    }
    
    public void setMousePos(int mouseX, int mouseY) {
    	mousePos.x = mouseX;
    	mousePos.y = mouseY;
    	
    	mouseBounds.x = mouseX;
    	mouseBounds.y = mouseY;
    }
    
    public void setPointerMode(int modality) {
    	if(modality == NORMAL) mouseTexture = normalMouseTexture;
    	else if(modality == ONHOVER) mouseTexture = onhoverMouseTexture;
    	
    	mouseTextureBounds.width = onhoverMouseTexture.width;
		mouseTextureBounds.height = onhoverMouseTexture.height;
    }
}
