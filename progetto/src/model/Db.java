package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
*/

public class Db {
    private static Db instance;
    private static String fileName = "products.ser";

    private Db() {}

    public static Db getInstace() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }

    public Product getProduct(int code) {
        Product product = null;

        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            product = (Product) in.readObject();

            

            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return product;
    }
}
