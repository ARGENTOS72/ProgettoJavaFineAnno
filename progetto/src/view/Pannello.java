package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.rText;

public class Pannello {
    private Raylib ray;
    final byte MAX_INPUT_CHARS;
    char[] nome;
    int key, contaFrame, back;
    byte nLettere;

    public Pannello(Raylib ray) {
        this.ray = ray;
        MAX_INPUT_CHARS=50;
        
        nome = new char[MAX_INPUT_CHARS];

        for (int i = 0; i < MAX_INPUT_CHARS; i++) {
            nome[i] = ' ';
        }

        key = 0;
        contaFrame=0;
        back = 0;
        nLettere=0;
    }

    public void draw() {
        ray.shapes.DrawRectangle(25, 25, 100, 100, Color.BLUE);
        ray.text.DrawTextEx(rText.GetFontDefault(), String.valueOf(nome), new Vector2(25, 25), 32, 2, Color.BLACK);
        key= ray.core.GetCharPressed();
            while (key > 0){
                if ((key >= 32) && (key <= 125) && (nLettere < MAX_INPUT_CHARS)){
                    nome[nLettere] = (char)key;
                    nLettere++;
                }
                if(key==32){
                    contaFrame=0;
                }

                key = ray.core.GetCharPressed();
            }

            if (rCore.IsKeyDown(Keyboard.KEY_BACKSPACE) && ((contaFrame)%5) == 0) {
                nLettere--;
                if (nLettere < 0){ nLettere = 0; }
                nome[nLettere]=' ';
                if(nLettere< MAX_INPUT_CHARS-1){ nome[nLettere+1]=' '; }
                contaFrame = 0;
            }
            
            if(ray.core.IsKeyPressed(Keyboard.KEY_ENTER)){
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
}
