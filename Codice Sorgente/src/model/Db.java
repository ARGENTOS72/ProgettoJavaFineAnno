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
    private boolean loadedProducts;

    private Db() {
        this.loadedProducts = false;
    }

    /**
     * Ritorna la istanza statica della stessa classe
     * 
     * @return l'istanza della classe
     */
    public static Db getInstace() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }

    /*
     * Carica una volta i prodotti all'interno di un array
     */
    @SuppressWarnings("unchecked")
    public void loadProducts() {
        if (!loadedProducts) {
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

            loadedProducts = true;
        }
    }

    public int nProdotti() {
        return prodotti.size();
    }
    
    /**
     * Aggiunge un prodotto all'interno dell'array della classe
     * 
     * @param prodotto che verrà aggiunto all'array
     */
    public void aggiungiProdotto(Product prodotto) {
    	prodotti.add(prodotto);
    }
    
    /**
     * Cambia la quantità di un prodotto passandoli il codice
     * 
     * @param codiceProdotto del prodotto da modificare
     * @param quantita da aggiungere al prodotto
     */
    public void cambiaQuantita(int codiceProdotto, int quantita) {
    	for (Product prodotto : prodotti) {
    		if (prodotto.getCodiceProdotto() == codiceProdotto) {
    			prodotto.cambiaQuantita(quantita);
    		}
    	}
    }

    /**
     * Cerca tutte le categorie esistenti
     * 
     * @return {@code HashSet<String>} contiene tutte le categorie (non duplicate)
     */
    public HashSet<String> categorieProdotti() {
        HashSet<String> categorieProdotti = new HashSet<String>();

        for (Product prodotto : prodotti) {
            for (String categoria : prodotto.getCategorie()) {
                categorieProdotti.add(categoria);
            }
        }

        return categorieProdotti;
    }

    /**
     * Cerca tutti i prodotti di una certa categoria
     * 
     * @param categoriaQuery la categoria da ricercare
     * @return ritorna tutti i prodotti della categoria
     */
    public ArrayList<Product> prodottoCategoria(String categoriaQuery) {
        ArrayList<Product> products = new ArrayList<Product>();

        for (Product prodotto : prodotti) {
            for (String categoria : prodotto.getCategorie()) {
                if (categoriaQuery.equals(categoria)) {
                    products.add(prodotto);
                    
                    break;
                }
            }
        }

        return products;
    }

    /**
     * Ordina i prodotti in diverse categorie
     * 
     * @return {@code HashMap<String, ArrayList<Product>>} la chiavi sono le categorie e i valori sono tutti i prodotti
     */
    public HashMap<String, ArrayList<Product>> prodottiPerCategoria() {
        HashMap<String, ArrayList<Product>> prodottiPerCategoria = new HashMap<String, ArrayList<Product>>();

        for (Product prodotto : prodotti) {
            ArrayList<String> categorie = prodotto.getCategorie();
            
            for (String categoria : categorie) {
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
            if (prodotto.getNome().contains(query)) {
                prodottiFiltrati.add(prodotto);
            } else {
                for (String categoria : prodotto.getCategorie()) {
                    if (query.contains(categoria)) {
                        prodottiFiltrati.add(prodotto);
                    }
                }
            }
        }

        return prodottiFiltrati;
    }
    
    /**
     * Salva i prodotti all'interno del file
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

    public ArrayList<Product> getProdotti() {
        return prodotti;
    }
}
