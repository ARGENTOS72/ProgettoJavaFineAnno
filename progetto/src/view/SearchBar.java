package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

public class SearchBar extends GraphicComponent {
	private Rectangle border;
	private int MAX_INPUT_CHARS;
	private char[] nome;
	int key, contaFrame, back, fontSize;
    byte nLettere;
    
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
	
	public void draw() {
		rShapes.DrawRectangleRec(border, Color.BLUE);
		Finestra.getRaylib().text.DrawText(String.valueOf(nome), (int)(border.x), (int)(border.y), fontSize, Color.BLACK);
//		Finestra.getRaylib().text.DrawText(String.valueOf(nome), contaFrame, back, MAX_INPUT_CHARS, null);
	}
	
	public void handleKeyBoardEvents() {
		key = Finestra.getRaylib().core.GetCharPressed();
        System.out.println("key: "+key);
        while (key > 0){
            if ((key >= 32) && (key <= 125) && (nLettere < MAX_INPUT_CHARS)){
                nome[nLettere] = (char)key;
                nLettere++;
            }
            if(key==32){
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
        
        if(Finestra.getRaylib().core.IsKeyPressed(Keyboard.KEY_ENTER)){
            if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0){
                nome[nLettere]=' ';
            }
            if(!String.copyValueOf(nome).replaceAll(" +", "").equals("")){
                // Premi invio e controlla se ha spazi vuoti
            }
        }else{
            if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0){
                nome[nLettere]='_';
            }else if(nLettere<MAX_INPUT_CHARS){
                nome[nLettere]=' ';
            }
            contaFrame++;
        }
	}
	
	@Override
	public boolean isHovered(Vector2 mousePos) {
		return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, border);
	}
}
