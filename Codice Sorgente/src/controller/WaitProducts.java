package controller;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import model.Db;
import model.Product;
import view.Pannello;

public class WaitProducts implements Runnable {
    private Db db;
    private Controller c;
    private Semaphore produttore;
    private Semaphore consumatore;
    private Pannello pannello;
    private Thread thread;

    public WaitProducts(Db db, Semaphore produttore, Semaphore consumatore, Pannello pannello, Controller c) {
        this.db = db;
        this.produttore = produttore;
        this.consumatore = consumatore;
        this.pannello = pannello;
        this.c = c;

        this.thread = new Thread(this, "Waiting products");
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            consumatore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Product> prodotti = db.getProdotti();

        pannello.loadHomeProducts(prodotti);
        pannello.loadCategorie(new ArrayList<String>(db.categorieProdotti()));
        pannello.setProdottoConsigliato(prodotti.get(10 + (int) (Math.random() * (prodotti.size() - 10))));
        pannello.showHomePage(c);
        pannello.registraHeaderFooter(c);

        produttore.release();
    }
}
