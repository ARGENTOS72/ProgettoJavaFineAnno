package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;

public class Finestra {
    public static int screenWidth, screenHeight;

    private static Raylib ray;
    private Pannello pannello;

    public Finestra() {
        ray = new Raylib(0, 0, "Kiriko Amazon");

        ray.core.ToggleFullscreen();
        screenWidth = rCore.GetScreenWidth();
        screenHeight = rCore.GetScreenHeight();
        ray.core.SetTargetFPS(60);

        pannello = new Pannello();
    }

    public void draw() {
        ray.core.BeginDrawing();
        ray.core.ClearBackground(Color.WHITE);

        pannello.draw();

        ray.core.EndDrawing();
    }

    public static Raylib getRaylib() {
        return ray;
    }

    public Pannello getPannello() {
        return pannello;
    }
}
