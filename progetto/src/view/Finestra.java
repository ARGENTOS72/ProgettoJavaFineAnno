package view;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;

public class Finestra {
    private static Raylib ray = new Raylib(0, 0, "Kiriko Amazon");
    private static Pannello pannello = new Pannello(ray);
    
    public Finestra(){
        ray.core.ToggleFullscreen();
        int screenWidth = rCore.GetScreenWidth();
        int screenHeight = rCore.GetScreenHeight();

        while (!ray.core.WindowShouldClose()) {
            ray.core.BeginDrawing();
            ray.core.ClearBackground(Color.WHITE);
            pannello.draw();
            ray.core.EndDrawing();
        }
    }

    
}
