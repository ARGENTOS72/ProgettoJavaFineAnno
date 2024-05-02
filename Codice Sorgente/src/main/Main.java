package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

import controller.Controller;
import controller.WaitProducts;
import model.Db;
import model.LoadProducts;
import model.Product;
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

/*
 * Db db = Db.getInstace();

        String[] nomi = {
            "Controller PS5", "Cuffie", "Smart Watch", "Tastiera Meccanica", "Laptop Azzus",
            "Fortinte Gratis", "IPhone 69", "Persona 3 Reload", "Camicia Heken", "Borsa Lidl",
            "Nintendo Switch", "Kiriko the game", "Barchellona T-shirt", "Mouse", "Lampada",
            "Scala Muro", "125 Moto Degenerata", "Casa Fai Da Te", "GamePass", "Giardiniere",
        };

        String[] descrizioni = {
            "Controller PS5, connesione PS5, avariato, fatto cose inesplicabili.",
            "Puoi ascoltare musica sennò ci ascolti altre cose dipende da cosa guardi.",
            "Lo usi per copiare o lo usi per copiare nessun'altro utilizzo.",
            "Lo compri solo perché c'è scritto meccanica e per nessun'altro motivo.",
            "Comprato solo perché sei obbligato sennò non lo prendi nemmeno se gratis.",
            "La copia essata di fortnite a pagamento, perché l'altro costa troppo.",
            "Uguale come gli altri Iphone però ha una feature in più come sempre inutile.",
            "Mi compri solo se sei così tanto voglioso di vedere ragazze che ti maltrattano.",
            "Una camica come le altre però con una marca speciale, limited edition.",
            "Borsa così speciale targata Lidl non la trovi da nessuna parte.",
            "Non sono nemmeno come le altre console ho solo l'esclusiva e il party.",
            "Miglior gioco mai creato, chi pensa che non sia finito ha torto è una feature.",
            "Per favore compratela non abbiamo soldi, siam poveri, siamo in debito con tutti.",
            "Posso essere usato nel computer sennò posso essere usato come animale.",
            "Potrei far luce, dipende da quanto ho voglia, prova ad accendermi se riesci.",
            "Tipica tecnica utilizzata utilizzata da Napoleone per vincere tutte le guerre.",
            "Sono messa male però se riesco posso fare un kilometro a piedi, se voglio.",
            "Casa fatta da gente sottopagata, se crolla chiamate S0mby official saprà dar risposte.",
            "Gamepass di una console che nessuno utilizza, abbastanzza imbarazzante.",
            "Vendiamo anche umani, il traffico di umani è legalissimo, attenti alla legge.",
        };
        
        String[][] categorie = {
            {"Elettronica", "Console", "Periferica"},
            {"Elettronica", "Audio", "Periferica"},
            {"Elettronica", "Orologio", "Periferica"},
            {"Elettronica", "Tastiera", "Periferica"},
            {"Elettronica", "Computer", "Video"},
            {"Elettronica", "Videogioco", "Assurdo"},
            {"Elettronica", "Telefono", "Scaga"},
            {"Elettronica", "Videogioco", "Scaga"},
            {"Abbigliamento", "Limited Edition", "Luxury"},
            {"Accessorio", "Borsa", "Luxury"},
            {"Elettronica", "Console", "Videogioco"},
            {"Elettronica", "Miglior", "Videogioco"},
            {"Abbigliamento", "Calcio", "Povero"},
            {"Elettronica", "Mouse", "Periferica"},
            {"Casa", "Mobile", "Arredamento"},
            {"Costruzioni", "Tattica", "Videogioco"},
            {"Mezzo", "Povero", "Ruote"},
            {"Casa", "Fai Da Te", "Povera"},
            {"Elettronica", "Videogioco", "Povera"},
            {"Persona", "Illegale", "Gratis"},
        };

        for (int i = 0; i < 20; i++) {
            db.aggiungiProdotto(new Product(Math.random() * 100, nomi[i], descrizioni[i], new ArrayList<String>(Arrays.asList(categorie[i])), (int) (Math.random() * 5) + 1));
        }

        db.salvaProdotti();
 */
