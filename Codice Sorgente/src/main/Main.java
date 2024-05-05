package main;

import java.util.concurrent.Semaphore;

import controller.Controller;
import controller.WaitProducts;
import model.Db;
import model.LoadProducts;
import view.Finestra;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Semaphore produttore = new Semaphore(1);
        Semaphore consumatore = new Semaphore(0);

        Finestra finestra = new Finestra();
        Db db = Db.getInstace();
        Controller controller = new Controller(finestra.getPannello(), db);
        LoadProducts loadProducts = new LoadProducts(db, produttore, consumatore);
        WaitProducts waitProducts = new WaitProducts(db, produttore, consumatore, finestra.getPannello());

        while (!Finestra.getRaylib().core.WindowShouldClose()) {
            finestra.draw();
            controller.update();
        }
    }
}