package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

public class SearchBar extends GraphicComponent {//TODO also addActionListener
	private Rectangle bounds;//textfield + btn
	
	//text field
	private Rectangle txtFieldBounds;
	private Color borderColor;
	private Color color, hoveredColor, clikcedColor, focussedColor, currentColor;
	private Color textColor, hoveredTextColor, clikcedTextColor, focussedTextColor, currentTextColor;
	private float roundness;
	private int borderThickness;
	private char[] text;
    private byte maxChars;
    private byte nCurrenteLetters;
    private int fontSize, fontSpacing, padding;
    private int key;
    private int frameCounter;
    private boolean cancel;
    
    //send button
    private TextureButton sendBtn;
    
    //Constructor -----------------------------------------------
    public SearchBar(int x, int y, int width, int height, float roundness, int borderThickness, byte maxChars, int fontSize,
    		Texture2D textureSendBtn)  {
    	this.bounds = new Rectangle(x, y, width, height);
    	this.txtFieldBounds = bounds;
    	this.txtFieldBounds.setWidth(bounds.getWidth()-bounds.getHeight());
    	
    	this.borderColor = Color.BLACK;
    	this.color = Color.YELLOW;
    	this.hoveredColor = Color.BEIGE;
    	this.clikcedColor = null;
    	this.focussedColor = Color.RED;
    	this.currentColor = color;
    	this.textColor = Color.GRAY;
    	this.hoveredTextColor = Color.DARKGRAY;
    	this.clikcedTextColor = null;
    	this.focussedTextColor = Color.BLACK;
    	this.currentTextColor = textColor;
    	
    	this.roundness = roundness;
    	this.borderThickness = borderThickness;
    	this.maxChars = maxChars;
    	this.fontSize = fontSize;
    	this.fontSpacing = 2;
    	this.padding = (height - fontSize) / 2;
    	int sendBtnPadding = 5;
    	this.sendBtn = new TextureButton(textureSendBtn, (int)(x+txtFieldBounds.getWidth()), y, height-(sendBtnPadding*2), height-(sendBtnPadding*2), true,
    			roundness, sendBtnPadding, Color.WHITE, Color.YELLOW, Color.RED);
    	
    	this.text = new char[maxChars];
        for (int i = 0; i < maxChars; i++) this.text[i] = ' ';
        this.maxChars = maxChars;
        this.nCurrenteLetters = 0;
        this.fontSize = fontSize;
        this.key = 0;
        this.frameCounter = 0;
        this.cancel = true;
    }
    
    //draw -------------------------------------------------------
    public void draw() {
    	Finestra.getRaylib().shapes.DrawRectangleRounded(bounds, roundness, 5, currentColor);//text field background
    	Finestra.getRaylib().shapes.DrawRectangle(sendBtn.getX()-getHeight(), getY(), (int) (getHeight()*1.5), getHeight(), currentColor);
    	//...
    	
    	Finestra.getRaylib().text.DrawTextEx(rText.GetFontDefault(), String.valueOf(text),
    			new Vector2(bounds.getX()+padding, bounds.getY()+padding), fontSize, fontSpacing, currentTextColor);
    	
    	sendBtn.draw();
    	
    	//old
//        Finestra.getRaylib().shapes.DrawRectangleRounded(rec, borderRadius, 4, recColor);
//
//        Finestra.getRaylib().text.DrawTextEx(rText.GetFontDefault(), String.valueOf(text),
//                new Vector2(rec.x + leftPadding, rec.y + 10),
//                fontSize, fontSpacing, textColor);
//
//        //Finestra.getRaylib().shapes.DrawRectangleRounded(new Rectangle(rec.x + rec.width - 100, rec.y, 100, rec.height), borderRadius, 4, Color.GRAY);
//        sendButton.draw();
//        Finestra.getRaylib().textures.DrawTextureEx(icon, new Vector2(rec.x + rec.width - 50 - ((icon.width * 0.08f) / 2), rec.y + rec.height / 2 - ((icon.height * 0.08f) / 2)), 0f, 0.08f, Color.WHITE);
//        Finestra.getRaylib().shapes.DrawRectangleRoundedLines(rec, borderRadius, 4, borderThickness, Color.BLACK);
    }
    
    //class methods ------------------------------------------------------
    public void resetSearchBar() {
        if (nCurrenteLetters < maxChars) {
            if (text[nCurrenteLetters] == '_') {
                text[nCurrenteLetters] = ' ';
            }
        }

        frameCounter = 0;
    }
    
    //getters & setters ----------------------------------------------------
    public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getLocation() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	public Vector2 getSize() {
		return new Vector2(bounds.width, bounds.height);
	}
	
	public int getX() {
		return (int) bounds.x;
	}
	
	public int getY() {
		return (int) bounds.y;
	}
	
	public int getWidth() {
		return (int) bounds.width;
	}
	
	public int getHeight() {
		return (int) bounds.height;
	}
	
	public Rectangle getTextFieldBounds() {
		return txtFieldBounds;
	}
	
	public Vector2 getTextFieldLocation() {
		return new Vector2(txtFieldBounds.x, txtFieldBounds.y);
	}
	
	public Vector2 getTextFieldSize() {
		return new Vector2(txtFieldBounds.width, txtFieldBounds.height);
	}
	
	public int getTextFieldX() {
		return (int) txtFieldBounds.x;
	}
	
	public int getTextFieldY() {
		return (int) txtFieldBounds.y;
	}
	
	public int getTextFieldWidth() {
		return (int) txtFieldBounds.width;
	}
	
	public int getTextFieldHeight() {
		return (int) txtFieldBounds.height;
	}

	public float getRoundness() {
		return roundness;
	}

	public int getBorderThickness() {
		return borderThickness;
	}

	public String getText() {
		return String.valueOf(text);
	}

	public int getMaxChars() {
		return maxChars;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getFontSpacing() {
		return fontSpacing;
	}

	public int getPadding() {
		return padding;
	}

	public TextureButton getSendBtn() {
		return sendBtn;
	}
	
	public Color[] getAllColors() {
		return new Color[] {
			borderColor,
			color, hoveredColor, clikcedColor, focussedColor,
			textColor, hoveredTextColor, clikcedTextColor, focussedTextColor,
			sendBtn.getColor(), sendBtn.getHoveredColor(), sendBtn.getClickedColor()
		};
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
		
		txtFieldBounds.x = bounds.x;
		txtFieldBounds.y = bounds.y;
		txtFieldBounds.width = bounds.width-bounds.height;
		txtFieldBounds.height = bounds.height;
		
		sendBtn.setBounds((int)(bounds.x+txtFieldBounds.width), (int)(bounds.y), (int)(bounds.height), (int)(bounds.height));
		
		padding = (int) ((bounds.height - fontSize) / 2);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		bounds.x = x;
		bounds.y = y;
		bounds.width = width;
		bounds.height = height;
		
		txtFieldBounds.x = x;
		txtFieldBounds.y = y;
		txtFieldBounds.width = width - height;
		txtFieldBounds.height = height;
		
		sendBtn.setBounds((int)(x+txtFieldBounds.width), y, height, height);
		
		padding = (height - fontSize) / 2;
	}
	
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
		
		txtFieldBounds.x = x;
		txtFieldBounds.y = y;
		
		sendBtn.setLocation((int) (x+txtFieldBounds.getWidth()), y);
	}
	
	public void setSize(int width, int height) {
		bounds.width = width;
		bounds.height = height;
		
		txtFieldBounds.width = width - height;
		txtFieldBounds.height = height;
		
		sendBtn.setBounds((int)(bounds.x+txtFieldBounds.width), (int) bounds.y, height, height);
		
		padding = (height - fontSize) / 2;
	}
	
	public void setX(int x) {
		bounds.x = x;
		
		txtFieldBounds.x = x;
		
		sendBtn.setX((int) (x+txtFieldBounds.getWidth()));
	}
	
	public void setY(int y) {
		bounds.y = y;
		
		txtFieldBounds.y = y;
		
		sendBtn.setY(y);
	}
	
	public void setWidth(int width) {
		bounds.width = width;
		
		txtFieldBounds.width = width - bounds.height;
		txtFieldBounds.height = bounds.height;
		
		sendBtn.setX((int)(bounds.x+txtFieldBounds.width));
	}
	
	public void setHeight(int height) {
		bounds.height = height;
		
		txtFieldBounds.height = height;
		
		sendBtn.setHeight(height);
		
		padding = (height - fontSize) / 2;
	}
	
	public void setTextFieldPercWidth(float perc) {//0 < percentage < 1
		if(perc == 0 || perc >= 1) return;
		
		txtFieldBounds.width = bounds.width * perc;
		sendBtn.setX((int) (bounds.x+txtFieldBounds.width));
		sendBtn.setWidth((int) (bounds.width - txtFieldBounds.width));
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness;
	}

	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	public void setText(String text) {
		this.text = text.toCharArray();
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		this.padding = (int) ((bounds.height - fontSize) / 2);
	}

	public void setFontSpacing(int fontSpacing) {
		this.fontSpacing = fontSpacing;
	}
	
	public void setAllColors(Color[] arr12Cols) {
		if(arr12Cols.length != 12) return;
		
		borderColor = arr12Cols[0];
		color = arr12Cols[1];
		hoveredColor = arr12Cols[2];
		clikcedColor = arr12Cols[3];
		focussedColor = arr12Cols[4];
		textColor = arr12Cols[5];
		hoveredTextColor = arr12Cols[6];
		clikcedTextColor = arr12Cols[7];
		focussedTextColor = arr12Cols[8];
		sendBtn.setColor(arr12Cols[9]);
		sendBtn.setHoveredColor(arr12Cols[10]);
		sendBtn.setClickedColor(arr12Cols[11]);
	}
    
    //superclass overrides ---------------------------------------------------
    @Override
	public void setName(String name) {
		super.setName(name);
		sendBtn.setName(name+".sendBtn");
	}

	@Override
	public void onHover() {
    	if(hoveredColor != null) currentColor = hoveredColor;
    	if(hoveredTextColor != null) currentTextColor = hoveredTextColor;
	}
    
    @Override
	public void outOfHover() {
		currentColor = color;
		currentTextColor = textColor;
	}
    
    @Override
    public void onFocus() {
    	if(focussedColor != null) currentColor = focussedColor;
    	if(focussedTextColor != null) currentTextColor = focussedTextColor;
    	
        key = Finestra.getRaylib().core.GetCharPressed();

        while (key > 0) {
            if ((key >= 32) && (key <= 125) && (nCurrenteLetters < maxChars)) {
                text[nCurrenteLetters] = (char) key;
                nCurrenteLetters++;
            }
            if (key == 32) {
                frameCounter = 0;
            }

            key = Finestra.getRaylib().core.GetCharPressed();
        }

        if (rCore.IsKeyDown(Keyboard.KEY_BACKSPACE) && (((frameCounter) % 5) == 0 || cancel) ) {
            cancel = false;
            
            nCurrenteLetters--;

            if (nCurrenteLetters < 0) {
                nCurrenteLetters = 0;
            }

            text[nCurrenteLetters] = ' ';
            
            if (nCurrenteLetters < maxChars - 1) {
                text[nCurrenteLetters + 1] = ' ';
            }
            
            frameCounter = 0;
        }

        if (Finestra.getRaylib().core.IsKeyReleased(Keyboard.KEY_BACKSPACE)) {
            cancel = true;
        }

        if (Finestra.getRaylib().core.IsKeyPressed(Keyboard.KEY_ENTER)) {
            if (nCurrenteLetters < maxChars && ((frameCounter / 50) % 2) == 0) {
                text[nCurrenteLetters] = ' ';
            }
            
            if (!String.copyValueOf(text).replaceAll(" +", "").equals("")) {
                // Premi invio e controlla se ha spazi vuoti
            }
        } else {
            if (nCurrenteLetters < maxChars && ((frameCounter / 50) % 2) == 0) {
                text[nCurrenteLetters] = '_';
            } else if (nCurrenteLetters < maxChars) {
                text[nCurrenteLetters] = ' ';
            }
            
            frameCounter++;
        }
    }
    
    @Override
	public void outOfFocus() {
    	currentColor = color;
		currentTextColor = textColor;
	}
    
    @Override
	public void onClick(int modality) {
    	if(modality == GraphicComponent.DOWN) {
			if(clikcedColor != null) currentColor = clikcedColor;
			if(clikcedTextColor != null) currentTextColor = clikcedTextColor;
		}
	}
    
    @Override
    public boolean isHovered(Vector2 mousePos) {
        return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, txtFieldBounds);
    }
    
    @Override
    public void addListener(Controller c) {
    	c.addListenerTo(this);
    	c.addListenerTo(sendBtn);
    }
    
    @Override
	public void removeListener(Controller c) {
		c.removeListenerTo(this);
		c.removeListenerTo(sendBtn);
	}
}
