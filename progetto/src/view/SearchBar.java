package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;

public class SearchBar extends GraphicComponent {
    private Rectangle rec;
    private Color recColor;
    private int borderThickness;
    private float borderRadius;
    private int leftPadding;
    private char[] text;
    private byte maxChars;
    private byte nCurrenteLetters;
    private int fontSize;
    private int fontSpacing;
    private Color textColor;
    private int key;
    private int frameCounter;
    private boolean cancel;
    private Texture2D icon;

    public SearchBar(int xPos, int yPos, int width, int borderThickness, float borderRadius, int leftPadding, byte maxChars,
            int fontSize, int fontSpacing, Color recColor,
            Color textColor) {
        this.rec = new Rectangle(xPos, yPos, width, fontSize + 20);
        this.recColor = recColor;
        this.borderThickness = borderThickness;
        this.borderRadius = borderRadius;
        this.leftPadding = leftPadding;
        this.text = new char[maxChars];
        for (int i = 0; i < maxChars; i++) {
            this.text[i] = ' ';
        }
        this.maxChars = maxChars;
        this.nCurrenteLetters = 0;
        this.fontSize = fontSize;
        this.fontSpacing = fontSpacing;
        this.textColor = textColor;
        this.key = 0;
        this.frameCounter = 0;
        this.cancel = true;
        this.icon = new Texture2D("textures/SearchIcon.png");
    }

    public void draw() {
        Finestra.getRaylib().shapes.DrawRectangleRounded(rec, borderRadius, 4, recColor);

        Finestra.getRaylib().text.DrawTextEx(rText.GetFontDefault(), String.valueOf(text),
                new Vector2(rec.x + leftPadding, rec.y + 10),
                fontSize, fontSpacing, textColor);

        Finestra.getRaylib().shapes.DrawRectangleRounded(new Rectangle(rec.x + rec.width - 100, rec.y, 100, rec.height), borderRadius, 4, Color.GRAY);
        Finestra.getRaylib().textures.DrawTextureEx(icon, new Vector2(rec.x + rec.width - 50, rec.y + rec.height / 2), 0f, 0.08f, Color.WHITE);
        Finestra.getRaylib().shapes.DrawRectangleRoundedLines(rec, borderRadius, 4, borderThickness, Color.BLACK);
    }

    public void handleKeyBoardEvents() {
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

    public void resetSearchBar() {
        if (nCurrenteLetters < maxChars) {
            if (text[nCurrenteLetters] == '_') {
                text[nCurrenteLetters] = ' ';
            }
        }

        frameCounter = 0;
    }

    @Override
    public boolean isHovered(Vector2 mousePos) {
        return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, rec);
    }
}

/*
package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

public class SearchBar extends GraphicComponent {
	private int MAX_INPUT_CHARS;
	private Color backgroundColor, foregroundColor;
	private Rectangle border;
	private int fontSize;
	
	private char[] nome;
	int key, contaFrame, back;
    byte nLettere;
    
    
    //Constructors -----------------------------------------
    public SearchBar(int max_input_chars, Color backgroundColor, Rectangle bounds, Color foregroundColor, int fontSize) {
    	this.MAX_INPUT_CHARS = max_input_chars;
    	this.backgroundColor = backgroundColor;
    	this.foregroundColor = foregroundColor;
    	this.border = bounds;
    	this.fontSize = fontSize;
    	
    	key = 0;
    	contaFrame = 0;
    	back = 0;
    	nLettere = 0;
    	nome = new char[MAX_INPUT_CHARS];
        for (int i = 0; i < MAX_INPUT_CHARS; i++) nome[i] = ' ';
    }
    
    public SearchBar(int max_input_chars, Color backgroundColor, int x, int y, int width, int height, Color foregroundColor, int fontSize) {
    	this(max_input_chars, backgroundColor, new Rectangle(x, y, width, height), foregroundColor, fontSize);
    }
    
	public SearchBar(int max_input_chars, int x, int y, int fontSize) {
        this.MAX_INPUT_CHARS = max_input_chars;
        this.key = 0;
        this.contaFrame = 0;
        this.back = 0;
        this.nLettere = 0;
        this.fontSize = fontSize;
        
        nome = new char[MAX_INPUT_CHARS];
        for (int i = 0; i < MAX_INPUT_CHARS; i++) nome[i] = ' ';
        
        border = new Rectangle(x, y, Finestra.getRaylib().text.MeasureText(String.valueOf(nome), fontSize), fontSize);
	}
	
	//draw -----------------------------------------
	public void draw() {
		rShapes.DrawRectangleRec(border, backgroundColor);
		Finestra.getRaylib().text.DrawText(getDisplayedText(), (int)(border.x), (int)(border.y), fontSize, foregroundColor);
//		Finestra.getRaylib().text.DrawText(String.valueOf(nome), contaFrame, back, MAX_INPUT_CHARS, null);
	}
	
	//
	private String getDisplayedText() {
		int maxCharW = Finestra.getRaylib().text.MeasureText("oo", fontSize)-Finestra.getRaylib().text.MeasureText("o", fontSize);
		int maxCharDisplayed = (int)(border.width / maxCharW);
		
		System.out.print(border.width +" / ");
		System.out.print(maxCharW +" = ");
		System.out.println(maxCharDisplayed);
		
		int i=1;
		for(;i<nome.length && nome[i] != ' ' && nome[i] != '_';i++);
		
		return String.valueOf(nome).substring(Math.max(0, i-maxCharDisplayed), maxCharDisplayed);
	}
	
	//-----------------------------------------
	public void handleKeyBoardEvents() {
		key = Finestra.getRaylib().core.GetCharPressed();
        
        while (key > 0) {
            if ((key >= 32) && (key <= 125) && (nLettere < MAX_INPUT_CHARS)){
                nome[nLettere] = (char)key;
                nLettere++;
            }
            if(key==32) {
                contaFrame=0;
            }

            key = Finestra.getRaylib().core.GetCharPressed();
        }

        if (rCore.IsKeyDown(Keyboard.KEY_BACKSPACE) && ((contaFrame)%5) == 0) {
            nLettere--;
            if (nLettere < 0){ nLettere = 0; }
            nome[nLettere]=' ';
            if(nLettere< MAX_INPUT_CHARS-1){ nome[nLettere+1]=' '; }
            contaFrame = 0;
        }
        
        if(Finestra.getRaylib().core.IsKeyPressed(Keyboard.KEY_ENTER)) {
            if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0){
                nome[nLettere]=' ';
            }
            if(!String.copyValueOf(nome).replaceAll(" +", "").equals("")){
                // Premi invio e controlla se ha spazi vuoti
            }
        }else{
            if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0) {
                nome[nLettere]='_';
            }else if(nLettere<MAX_INPUT_CHARS){
                nome[nLettere]=' ';
            }
            contaFrame++;
        }
	}
	
	//superclass overrides
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, border);
	}
}
*/
