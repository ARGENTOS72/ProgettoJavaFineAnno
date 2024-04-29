package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
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
	private float rotation, roundPerSecond = 40f;
	
	//Constructor --------------------------------------------
	public TextureAnimation(TextureAnimation textureAnimation) {
		super(textureAnimation.getX(), textureAnimation.getY(), textureAnimation.getWidth(),
			textureAnimation.getHeight(), textureAnimation.getColor());
		
		setSize(getWidth()+(textureAnimation.getPadding()*2), getHeight()+(textureAnimation.getPadding()*2));
		
		this.roundPerSecond = textureAnimation.getRoundPerSecond();
		this.rotation = 0;
		this.padding = textureAnimation.getPadding();
		this.texture = textureAnimation.getTexture();
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(textureAnimation.getOrigin().x, textureAnimation.getOrigin().y);
	}
	
	public TextureAnimation(Rectangle bounds, Color color, Texture2D texture, int padding, Vector2 origin) {
		super((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(),
				(int) bounds.getHeight(), color);
		
		setSize(getWidth()+(padding*2), getHeight()+(padding*2));
		
		this.rotation = 0;
		this.padding = padding;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = origin;
	}
	
	public TextureAnimation(int x, int y, int width, int height, Texture2D texture) {
		super(x, y, width, height, Color.WHITE);
		
		this.roundPerSecond = 30f;
		this.rotation = 0f;
		this.padding = 0;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);

		float scaleX = width / textureBounds.width; 
		float scaleY = height / textureBounds.height; 

		// Choose the minimum scale to fit the texture within the bounds while maintaining aspect ratio
		float scale = Math.min(scaleX, scaleY);

		// Calculate the scaled texture size
		float scaledWidth = textureBounds.width * scale;
		float scaledHeight = textureBounds.height * scale;

		// Calculate the origin to center the texture within the bounds
		float originX = x + width / 2;
		float originY = y + height / 2;
		
		setBounds((int)(originX - scaledWidth / 2), (int)(originY - scaledHeight / 2), (int)scaledWidth, (int)scaledHeight);
	    origin = new Vector2(scaledWidth/2 , scaledHeight/2);

	}
	
	//draw -----------------------------------
	@Override
	public void draw() {
		super.draw();
//		rTextures.DrawTexturePro(texture, textureBounds,
//			new Rectangle(getX()+padding, getY()+padding, getWidth()-(padding*2), getHeight()-(padding*2)),
//			origin, rotation, getColor());
		rTextures.DrawTexturePro(texture,
			textureBounds,
			getBounds(),
			origin,
			rotation, getColor());
		
		System.out.println("origin: "+origin.getX()+", "+origin.getY());
	}
	
	//getters & setters -----------------------------
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
	
	public float getRoundPerSecond() {
		return roundPerSecond;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void setRoundPerSecond(float roundPerSecond) {
		this.roundPerSecond = roundPerSecond;
	}
	
	//implements ------------------------------------
	@Override
	public void update(float deltaTime) {
		this.rotation+= (float)(roundPerSecond*deltaTime);
	}
	
	@Override
	public void addUpdater(Controller c) {
		c.addUpdaterTo(this);
	}
	
	@Override
	public void removeUpdater(Controller c) {
		c.removeUpdaterTo(this);
	}
		
}
