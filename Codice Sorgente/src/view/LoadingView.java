package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

public class LoadingView {
	//screen
	private int screenWidth, screenHeight;
	
	//animation
	private Texture2D loadingTexture;
	private TextureAnimation loadingAnimation;
	private int loadingAnimationDim = rCore.GetScreenWidth()/6;
	
	//loading text
	private TextAnimation loadingText;
	
	//Constructor -----------------------------------------------
	public LoadingView(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        
        this.loadingTexture = new Texture2D("textures/loading.png");
        
        this.loadingAnimation = new TextureAnimation(this.screenWidth / 2 - loadingAnimationDim / 2,
        		this.screenHeight / 2 - loadingAnimationDim / 2, loadingAnimationDim, loadingAnimationDim, 
        		loadingTexture, 300f, false);
        loadingAnimation.setName("loadingAnimation");
        
        this.loadingText = new TextAnimation(100, loadingAnimation.getY() + loadingAnimation.getHeight() + 30,
        		Color.WHITE, false, "Loading", 30, 10, Color.BLACK);
        
        loadingText.setName("loadingText");
        GraphicComponentAligner.centerX(loadingText, 0, screenWidth);
	}
	
	public void draw() {
		loadingAnimation.draw();
		loadingText.draw();
	}
	
	public void registraEventi(Controller c) {
		loadingAnimation.addUpdater(c);
		loadingText.addUpdater(c);
	}
	
	public void cancellaEventi(Controller c) {
		loadingAnimation.removeUpdater(c);
		loadingText.removeUpdater(c);
	}
}
