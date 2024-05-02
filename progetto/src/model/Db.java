package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Database con i prodotti
 */
public class Db {
    private static Db instance;
    private static String fileName = "products.ser";
    private ArrayList<Product> prodotti; 

    @SuppressWarnings("unchecked")
    private Db() {
    	File file = new File(fileName);
        
        try {
            if (file.createNewFile()) {
                prodotti = new ArrayList<Product>();
            } else {
                try {
                    FileInputStream fileIn = new FileInputStream(fileName);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    
                    prodotti = (ArrayList<Product>) in.readObject();
                    
                    in.close();
                    fileIn.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
        
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }

    /**
     * 
     * @return
     */
    public static Db getInstace() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }
    
    /**
     * 
     * @param prodotto
     */
    public void aggiungiProdotto(Product prodotto) {
    	prodotti.add(prodotto);
    }
    
    /**
     * 
     * @param codiceProdotto
     * @param quantita
     */
    public void cambiaQuantita(int codiceProdotto, int quantita) {
    	for (Product prodotto : prodotti) {
    		if (prodotto.getCodiceProdotto() == codiceProdotto) {
    			prodotto.cambiaQuantita(quantita);
    		}
    	}
    	
    	// Exception
    }

    /**
     * Cerca tutte le categorie esistenti
     * 
     * @return {@code HashSet<String>} contiene tutte le categorie (non duplicate)
     */
    public HashSet<String> categorieProdotti() {
        HashSet<String> categorieProdotti = new HashSet<String>();

        for (Product prodotto : prodotti) {
            categorieProdotti.add(prodotto.getCategoria());
        }

        return categorieProdotti;
    }

    /**
     * Ordina i prodotti in diverse categorie
     * 
     * @return {@code HashMap<String, ArrayList<Product>>} la chiavi sono le categorie e i valori sono tutti i prodotti
     */
    public HashMap<String, ArrayList<Product>> prodottiPerCategoria() {
        HashMap<String, ArrayList<Product>> prodottiPerCategoria = new HashMap<String, ArrayList<Product>>();

        for (Product prodotto : prodotti) {
            String categoria = prodotto.getCategoria();
            ArrayList<Product> prodottiPerCategoriaTemp = prodottiPerCategoria.get(categoria);

            if (prodottiPerCategoriaTemp == null) {
                ArrayList<Product> prodottiTemp = new ArrayList<Product>();
                prodottiTemp.add(prodotto);
                
                prodottiPerCategoria.put(categoria, prodottiTemp);
            } else {
                prodottiPerCategoriaTemp.add(prodotto);

                prodottiPerCategoria.put(categoria, prodottiPerCategoriaTemp);
            }
        }

        return prodottiPerCategoria;
    }

    /**
     * Cerca tutti i prodotti che hanno per nome o categoria la query
     * 
     * @param query La stringa che verrà usata per fare la query
     * @return Ritorna tutti i prodotti che rispettano la query
     */
    public ArrayList<Product> cercaProdotti(String query) {
        ArrayList<Product> prodottiFiltrati = new ArrayList<Product>();

        for (Product prodotto : prodotti) {
            if (prodotto.getNome().contains(query) || prodotto.getCategoria().equals(query)) {
                prodottiFiltrati.add(prodotto);
            }
        }

        return prodottiFiltrati;
    }
    
    /**
     * 
     */
    public void salvaProdotti() {
    	try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(prodotti);
            
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return
     */
    public ArrayList<Product> getProdotti() {
        return prodotti;
    }
}
