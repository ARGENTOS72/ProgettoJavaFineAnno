package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* 
 * try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            prodotti = (ArrayList<Product>) in.readObject();
            
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        prodotti = new ArrayList<Product>();
    	prodotti.add(new Product(11.0, "Test", "Test Test", 1));
    	prodotti.add(new Product(6.0, "Prova", "Prova Prova", 2));
    	prodotti.add(new Product(72.72, "Argentos", "Argentos Argentos", 7));
    	prodotti.add(new Product(345.21, "Hakani", "Hakani Hakani", 192));
    	
    	try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(prodotti);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

public class Db {
	private static final long serialVersionUID = 1234567L;
    private static Db instance;
    private static String fileName = "products.ser";
    private ArrayList<Product> prodotti; 

    private Db() {
    	try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            prodotti = (ArrayList<Product>) in.readObject();
            
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
    	System.out.println(prodotti);
    }

    public static Db getInstace() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }
    
    public void aggiungiProdotto(Product prodotto) {
    	prodotti.add(prodotto);
    }
    
    public void cambiaQuantita(int codiceProdotto, int quantita) {
    	for (Product prodotto : prodotti) {
    		if (prodotto.getCodiceProdotto() == codiceProdotto) {
    			prodotto.cambiaQuantita(quantita);
    		}
    	}
    	
    	// Exception
    }
    
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
}
