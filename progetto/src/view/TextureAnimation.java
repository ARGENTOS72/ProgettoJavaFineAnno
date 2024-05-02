package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import controller.Controller;

public class TextureAnimation extends GraphicAnimation {
	private Texture2D texture;
	private Rectangle textureBounds;
	private Vector2 origin;
	private int padding;
	private float rotation, roundsPerSecond;
	private boolean backgroundVisible;
	
	//Constructor --------------------------------------------
	public TextureAnimation(TextureAnimation textureAnimation) {
		super(textureAnimation);
		
		setSize(getWidth()+(textureAnimation.getPadding()*2), getHeight()+(textureAnimation.getPadding()*2));
		
		this.backgroundVisible = textureAnimation.isBackgroundVisible();
		this.roundsPerSecond = textureAnimation.getRoundsPerSecond();
		this.rotation = 0;
		this.padding = textureAnimation.getPadding();
		this.texture = textureAnimation.getTexture();
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(textureAnimation.getOrigin().x, textureAnimation.getOrigin().y);
	}
	
	public TextureAnimation(Rectangle bounds, Color color, Texture2D texture, int padding, Vector2 origin, float roundsPerSecond, boolean backgroundVisible) {
		super((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(),
				(int) bounds.getHeight(), color);
		
		setSize(getWidth()+(padding*2), getHeight()+(padding*2));
		
		this.backgroundVisible = backgroundVisible;
		this.roundsPerSecond = roundsPerSecond;
		this.rotation = 0;
		this.padding = padding;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = origin;
		
		setAnimation(getDefaultAnimation());
	}
	
	public TextureAnimation(int x, int y, int width, int height, Texture2D texture, float roundsPerSecond, boolean backgroundVisible) {
		super(x+(width/2), y+(height/2), width, height, Color.WHITE);
		
		this.backgroundVisible = backgroundVisible;
		this.roundsPerSecond = roundsPerSecond;
		this.rotation = 0f;
		this.padding = 10;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		
		origin = new Vector2(width/2 , height/2);//origin has to be the center of bounds
		
		setAnimation(getDefaultAnimation());
	}
	
	//draw -----------------------------------
	@Override
	public void draw() {
		if(backgroundVisible) Finestra.getRaylib().shapes.DrawRectangle(getX()-(getWidth()/2),
				getY()-(getHeight()/2), getWidth(), getHeight(), getColor());
		
		rTextures.DrawTexturePro(texture, textureBounds, getBounds(), origin, rotation, getColor());
	}
	
	//getters & setters -----------------------------
	@Override
	public int getX() {
		return super.getX()-(super.getWidth()/2);
	}
	
	@Override
	public int getY() {
		return super.getY()-(super.getHeight()/2);
	}
	
	public Texture2D getTexture() {
		return texture;
	}

	public Rectangle getTextureBounds() {
		return textureBounds;
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public int getPadding() {
		return padding;
	}

	public float getRotation() {
		return rotation;
	}
	
	public float getRoundsPerSecond() {
		return roundsPerSecond;
	}
	
	public boolean isBackgroundVisible() {
		return backgroundVisible;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void setRoundsPerSecond(float roundPerSecond) {
		this.roundsPerSecond = roundPerSecond;
	}
	
	public void setBackgroundVisible(boolean backgroundsVisible) {
		this.backgroundVisible = backgroundsVisible;
	}
	
	//default animation ------------------------
	@Override
	public Animable getDefaultAnimation() {
		return new Animable() {
			@Override
			public void update(float deltaTime) {
				rotation+= (float)(roundsPerSecond*deltaTime);
			}
		};
	}
	
	//add & remove updater ---------------------------------
	@Override
	public void addUpdater(Controller c) {
		c.addUpdaterTo(this);
	}
	
	@Override
	public void removeUpdater(Controller c) {
		c.removeUpdaterTo(this);
	}
		
}
