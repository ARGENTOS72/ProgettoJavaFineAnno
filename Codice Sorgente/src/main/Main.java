package main;

import controller.Controller;
import model.Db;
import model.Product;
import view.Finestra;

public class Main {
    public static void main(String[] args) {
        Finestra finestra = new Finestra();
        Db db = Db.getInstace();
        Controller controller = new Controller(finestra.getPannello(), db);

        while (!Finestra.getRaylib().core.WindowShouldClose()) {
            finestra.draw();
            controller.update();
        }
    }
}
