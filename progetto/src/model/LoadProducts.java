package model;

import java.util.concurrent.Semaphore;

public class LoadProducts implements Runnable {
    private Db db;
    private Semaphore produttore;
    private Semaphore consumatore;
    private Thread thread;

    public LoadProducts(Db db, Semaphore produttore, Semaphore consumatore) {
        this.db = db;
        this.produttore = produttore;
        this.consumatore = consumatore;

        this.thread = new Thread(this, "Loading products");
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            produttore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        db.loadProducts();

        consumatore.release();
    }
}
