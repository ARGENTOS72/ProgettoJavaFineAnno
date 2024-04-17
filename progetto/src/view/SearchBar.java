package view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

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
    private Button sendButton;

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
        this.sendButton = new Button(new Rectangle((int) (rec.x + rec.width - 100), (int) rec.y, 100, rec.height), true, borderRadius, Color.GRAY, Color.DARKGRAY, Color.BEIGE);
    }

    public void draw() {
        Finestra.getRaylib().shapes.DrawRectangleRounded(rec, borderRadius, 4, recColor);

        Finestra.getRaylib().text.DrawTextEx(rText.GetFontDefault(), String.valueOf(text),
                new Vector2(rec.x + leftPadding, rec.y + 10),
                fontSize, fontSpacing, textColor);

        //Finestra.getRaylib().shapes.DrawRectangleRounded(new Rectangle(rec.x + rec.width - 100, rec.y, 100, rec.height), borderRadius, 4, Color.GRAY);
        sendButton.draw();
        Finestra.getRaylib().textures.DrawTextureEx(icon, new Vector2(rec.x + rec.width - 50 - ((icon.width * 0.08f) / 2), rec.y + rec.height / 2 - ((icon.height * 0.08f) / 2)), 0f, 0.08f, Color.WHITE);
        Finestra.getRaylib().shapes.DrawRectangleRoundedLines(rec, borderRadius, 4, borderThickness, Color.BLACK);
    }

    public void resetSearchBar() {
        if (nCurrenteLetters < maxChars) {
            if (text[nCurrenteLetters] == '_') {
                text[nCurrenteLetters] = ' ';
            }
        }

        frameCounter = 0;
    }

    public void setListener(Controller c) {
        sendButton.setName("btnsex");
        c.addListenerTo(sendButton);
    }
    
    //superclass overrides ------------------------------------
    @Override
    public boolean isHovered(Vector2 mousePos) {
        return Finestra.getRaylib().shapes.CheckCollisionPointRec(mousePos, new Rectangle(rec.x, rec.y, rec.width - 100, rec.height));
    }
    
    @Override
    public void onFocus() {
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
}
