package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

public class SearchBar extends ListenableGraphicComponent {
	//text field
	private Rectangle txtFieldBounds;
	private Color borderColor;
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
    	super(x, y, width, height, Color.WHITE, new Color(238,238,238, 255), null, Color.WHITE);
    	
    	this.txtFieldBounds = getBounds();
    	this.txtFieldBounds.setWidth(getWidth()-getHeight());
    	
    	this.borderColor = Color.BLACK;
    	this.textColor = Color.GRAY;
    	this.hoveredTextColor = Color.DARKGRAY;
    	this.clikcedTextColor = null;
    	this.focussedTextColor = new Color(87, 10, 142, 255);
    	this.currentTextColor = textColor;
    	
    	this.roundness = roundness;
    	this.borderThickness = borderThickness;
    	this.maxChars = maxChars;
    	this.fontSize = fontSize;
    	this.fontSpacing = 2;
    	this.padding = (height - fontSize) / 2;
    	int sendBtnPadding = 5;
    	this.sendBtn = new TextureButton(textureSendBtn, (int)(x+txtFieldBounds.getWidth()), y, height-(sendBtnPadding*2), height-(sendBtnPadding*2), true,
    			roundness, sendBtnPadding, Color.WHITE, new Color(141, 255, 248, 255), Color.VIOLET);
    	
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
    @Override
    public void draw() {
    	Finestra.getRaylib().shapes.DrawRectangleRounded(getBounds(), roundness, 5, getCurrentColor());//text field background
    	Finestra.getRaylib().shapes.DrawRectangle(sendBtn.getX()-getHeight(), getY(), (int) (getHeight()*1.5), getHeight(), getCurrentColor());
    	//...
    	
    	Finestra.getRaylib().text.DrawTextEx(rText.GetFontDefault(), String.valueOf(text),
    			new Vector2(getX()+padding, getY()+padding), fontSize, fontSpacing, currentTextColor);
    	
    	sendBtn.draw();
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
	
	@Override
	public Color[] getColors() {
		return new Color[] {
			borderColor,
			getColor(), getHoveredColor(), getClickedColor(), getFocussedColor(),
			textColor, hoveredTextColor, clikcedTextColor, focussedTextColor,
			sendBtn.getColor(), sendBtn.getHoveredColor(), sendBtn.getClickedColor()
		};
	}
	
	@Override
	public void setBounds(Rectangle bounds) {
		setBounds(bounds);
		
		txtFieldBounds.x = bounds.x;
		txtFieldBounds.y = bounds.y;
		txtFieldBounds.width = bounds.width-bounds.height;
		txtFieldBounds.height = bounds.height;
		
		sendBtn.setBounds((int)(bounds.x+txtFieldBounds.width), (int)(bounds.y), (int)(bounds.height), (int)(bounds.height));
		
		padding = (int) ((bounds.height - fontSize) / 2);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		
		txtFieldBounds.x = x;
		txtFieldBounds.y = y;
		txtFieldBounds.width = width - height;
		txtFieldBounds.height = height;
		
		sendBtn.setBounds((int)(x+txtFieldBounds.width), y, height, height);
		
		padding = (height - fontSize) / 2;
	}
	
	@Override
	public void setLocation(int x, int y) {
		setLocation(x, y);
		
		txtFieldBounds.x = x;
		txtFieldBounds.y = y;
		
		sendBtn.setLocation((int) (x+txtFieldBounds.getWidth()), y);
	}
	
	@Override
	public void setSize(int width, int height) {
		setSize(width, height);
		
		txtFieldBounds.width = width - height;
		txtFieldBounds.height = height;
		
		sendBtn.setBounds((int)(getX()+txtFieldBounds.width), getY(), height, height);
		
		padding = (height - fontSize) / 2;
	}
	
	@Override
	public void setX(int x) {
		setX(x);
		
		txtFieldBounds.x = x;
		
		sendBtn.setX((int) (x+txtFieldBounds.getWidth()));
	}
	
	@Override
	public void setY(int y) {
		setY(y);
		
		txtFieldBounds.y = y;
		
		sendBtn.setY(y);
	}
	
	@Override
	public void setWidth(int width) {
		setWidth(width);
		
		txtFieldBounds.width = width - getHeight();
		txtFieldBounds.height = getHeight();
		
		sendBtn.setX((int)(getX()+txtFieldBounds.width));
	}
	
	@Override
	public void setHeight(int height) {
		setHeight(height);
		
		txtFieldBounds.height = height;
		
		sendBtn.setHeight(height);
		
		padding = (height - fontSize) / 2;
	}
	
	public void setTextFieldPercWidth(float perc) {//0 < percentage < 1
		if(perc == 0 || perc >= 1) return;
		
		txtFieldBounds.width = getWidth() * perc;
		sendBtn.setX((int) (getX()+txtFieldBounds.width));
		sendBtn.setWidth((int) (getWidth() - txtFieldBounds.width));
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
		this.padding = (int) ((getHeight() - fontSize) / 2);
	}

	public void setFontSpacing(int fontSpacing) {
		this.fontSpacing = fontSpacing;
	}
		
	public void setAllColors(Color[] arr12Cols) {
		if(arr12Cols.length != 12) return;
		
		borderColor = arr12Cols[0];
		setColors(arr12Cols[1], arr12Cols[2], arr12Cols[3], arr12Cols[4]);
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
    	super.onHover();
    	if(hoveredTextColor != null) currentTextColor = hoveredTextColor;
	}
    
    @Override
	public void outOfHover() {
		super.outOfHover();
		currentTextColor = textColor;
	}
    
    @Override
    public void onFocus() {
    	super.onFocus();
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
    	super.outOfFocus();
		currentTextColor = textColor;
	}
    
    @Override
	public void onClick(int modality) {
    	if(modality == DOWN) {
			if(getClickedColor() != null) setCurrentColor(getClickedColor());
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
