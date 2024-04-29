package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import controller.Controller;

public class TextureButton extends Button {
	private Texture2D texture;
	private Rectangle textureBounds;
	private Vector2 origin;
	private int padding;
	
	//Constructor --------------------------------------------
	public TextureButton(TextureButton textureBtn) {
		super(textureBtn);
		setSize(getWidth()+(textureBtn.getPadding()*2), getHeight()+(textureBtn.getPadding()*2));
		this.padding = textureBtn.getPadding();
		this.texture = textureBtn.getTexture();
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(textureBtn.getOrigin().x, textureBtn.getOrigin().y);
	}
	
	public TextureButton(Button btn, Texture2D texture, int padding, Vector2 origin) {
		super(btn);
		setSize(getWidth()+(padding*2), getHeight()+(padding*2));
		this.padding = padding;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = origin;
	}
	
	public TextureButton(Texture2D texture, Rectangle bounds, boolean visible, float roundness, int padding, Color color, Color hoveredColor, Color clickedColor) {
		super(bounds, visible, roundness, color, hoveredColor, clickedColor);
		setSize(getWidth()+(padding*2), getHeight()+(padding*2));
		this.padding = padding;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(0, 0);
	}
	
	public TextureButton(Texture2D texture, int x, int y, int width, int height, boolean visible, float roundness, int padding, Color color, Color hoveredColor, Color clickedColor) {
		super(x, y, width, height, visible, roundness, color, hoveredColor, clickedColor);
		setSize(getWidth()+(padding*2), getHeight()+(padding*2));
		this.padding = padding;
		this.texture = texture;
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(0, 0);
	}
	
	public TextureButton(Texture2D texture, int x, int y, int width, int height, boolean visible, float roundness, Color color) {
		this(texture, x, y, width, height, visible, roundness, 0, null, null, null);
	}
	
	//use only for sponsored product (keep aspect ratio of a wide img)
	public TextureButton(int x, int y, int width, Texture2D texture) {
		super(new Rectangle(x, y, width, texture.height*(width / texture.width)), false, 0f, Color.WHITE);
		this.textureBounds = new Rectangle(0, 0, texture.width, texture.height);
		this.origin = new Vector2(0, 0);
		this.padding = 0;
		this.texture = texture;
	}
	
	//draw ------------------------------------------------------
	public void draw() {
		super.draw();
		rTextures.DrawTexturePro(texture, textureBounds, new Rectangle(getX()+padding, getY()+padding, getWidth()-(padding*2), getHeight()-(padding*2)), origin, 0, Color.WHITE);
	}
	
	//getters & setters ------------------------------------------
	public Texture2D getTexture() {
		return texture;
	}

	public int getPadding() {
		return padding;
	}
	
	public Vector2 getOrigin() {
		return origin;
	}

	public void setTexture(Texture2D texture) {
		this.texture = texture;
		this.textureBounds.width = texture.width;
		this.textureBounds.height = texture.height;
	}

	public void setPadding(int padding) {
		setSize(getWidth()-(this.padding*2)+(padding*2), getHeight()-(this.padding*2)+(padding*2));
		this.padding = padding;
	}
	
	public void setOrigin(int x, int y) {
		this.origin = new Vector2(x, y);
	}
	
	//superclass overrides ---------------------------------------------
	@Override
	public void onHover() {
		super.onHover();
	}
	
	@Override
	public void outOfHover() {
		super.outOfHover();
	}
	
	@Override
	public void onClick(int modality) {
		if(modality == ListenableGraphicComponent.DOWN) {
			super.onClick(modality);
		}
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return super.isHovered(mousePos);
	}
	
	@Override
	public void addListener(Controller c) {
		c.addListenerTo(this);
	}
	
	@Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
	}
	
	//toString ---------------------------------------------------
	@Override
	public String toString() {
		return "Button {" +
				"\n\tbounds [" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() +
				"]\n\tvisible: " + isVisible() +
				"\n\troundness: " + getRoundness() +
				"\n\tcolors [normal: " + getColor() + ", hovered: " + getHoveredColor() + ", clicked: " + getClickedColor() +
				"]\n\ttexture: " + texture +
				"\n\tpadding: " + padding +
				"\n}";
	}
}
