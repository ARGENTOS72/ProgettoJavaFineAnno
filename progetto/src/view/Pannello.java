package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;

import controller.Controller;

public class Pannello {
    private Raylib ray;

    private Button btn1, btn2;
    private TextButton txtBtn;
    private Texture2D texture;
    private TextureButton textureBtn;
    private SearchBar searchBar;

    public Pannello() {
        this.ray = Finestra.getRaylib();

        searchBar = new SearchBar(50, 10, 1000, 4, 0.06f, 10, (byte) 40, 32, 2, Color.LIGHTGRAY, Color.BLACK);
        btn1 = new Button(100, 100, 100, 30, true, 0.3f, Color.GRAY, Color.BLACK, Color.ORANGE);
        
        btn2 = new Button(btn1);
        btn2.setLocation(210, 100);
        btn2.setColors(Color.GREEN, Color.RED, Color.BLACK);
        
        txtBtn = new TextButton(btn2, 10, 20, "ciao", Color.WHITE, Color.YELLOW, Color.GOLD);
        txtBtn.setLocation(320, 100);
        
        texture = new Texture2D();
        
        textureBtn = new TextureButton(texture, 300, 300, 100, 100, true, 0, 0, Color.BLACK, Color.DARKGRAY, Color.GRAY);
    }

    public void draw() {
        btn1.draw();
        btn2.draw();
        txtBtn.draw();
        textureBtn.draw();
        searchBar.draw();
    }

    public void registraEventi(Controller c) {
        btn1.setName("btn");
        c.addListenerTo(btn1);
        
        btn2.setName("btn2");
        c.addListenerTo(btn2);
        
        txtBtn.setName("textBtn");
        c.addListenerTo(txtBtn);
        
        textureBtn.setName("btn texture");
        c.addListenerTo(textureBtn);
        
        searchBar.setName("search bar");
        c.addListenerTo(searchBar);
        searchBar.setListener(c);
    }
}
