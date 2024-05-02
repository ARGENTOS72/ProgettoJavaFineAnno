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
	private TextButton loadingText;
	
	//Constructor -----------------------------------------------
	public LoadingView(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        
        this.loadingTexture = new Texture2D("textures/loading.png");
        
        this.loadingAnimation = new TextureAnimation(screenWidth/2-(loadingAnimationDim/2),
        		screenHeight/2-(loadingAnimationDim/2), loadingAnimationDim, loadingAnimationDim, 
        		loadingTexture, 300f, false);
        loadingAnimation.setName("loadingAnimation");
        
        this.loadingText = new TextButton(100, loadingAnimation.getY()+loadingAnimation.getHeight()+30, false,
        	0f, null, null, null, 0, 30, "Loading ...", Color.ORANGE, null, null);
        loadingText.setName("loadingText");
        GraphicComponentAligner.centerX(loadingText, 0, screenWidth);
	}
	
	public void draw() {
		loadingAnimation.draw();
		loadingText.draw();
	}
	
	public void registraEventi(Controller c) {
		loadingAnimation.addUpdater(c);
		loadingText.addListener(c);
	}
	
	public void cancellaEventi(Controller c) {
		loadingAnimation.removeUpdater(c);
		loadingText.removeListener(c);
	}
}
