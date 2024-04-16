package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

import controller.Controller;

public class Pannello {
    private Raylib ray;

    private Button btn1;
    private SearchBar searchBar;

    public Pannello() {
        this.ray = Finestra.getRaylib();

        searchBar = new SearchBar(50, 10, 1000, 4, 0.06f, 10, (byte) 50, 32, 2, Color.LIGHTGRAY, Color.BLACK);
        btn1 = new Button(Color.BLACK, true, 5, 20, 60, "Sono un bottone", Color.ORANGE, 20);
    }

    public void draw() {
        btn1.draw();
        searchBar.draw();
    }

    public void registraEventi(Controller c) {
        btn1.setName("btn");
        c.addListenerTo(btn1);
        searchBar.setName("search bar");
        c.addListenerTo(searchBar);
    }
}
