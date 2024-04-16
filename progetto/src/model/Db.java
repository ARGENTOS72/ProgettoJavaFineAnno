package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Db {
    private static Db instance;
    private static String fileName = "data.db";

    private Db() {
        File file = new File(fileName);

        try {
            if (file.createNewFile()) { // Inizializza DB
                FileOutputStream outputStream = new FileOutputStream(fileName);

                String stringa = "Test{\nRecords 3 {int,int,int}\nColumns 0\nTable{\nRecord 1 {1,2,3}\nRecord 2 {1,2,3}\n}\n}";
                byte[] data = new byte[stringa.length()];

                for (int i = 0; i < stringa.length(); i++) {
                    data[i] = (byte) stringa.charAt(i);
                }

                outputStream.write(data);
                

                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Db getInstace() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }

    public String getValue(String dbName, String selectedItems) {
        String stringa = "";
        String finalString = "";
        boolean inDatabase = false;

        try {
            FileInputStream inputStream = new FileInputStream(fileName);

            int inputByte;
            while ((inputByte = inputStream.read()) != -1) {
                char character = (char) inputByte;
                
                if (inDatabase) {
                    finalString += character;
                } else {
                    if (character == '\n') {
                        if (stringa.equals(dbName)) { // In database
                            inDatabase = true;
                        }
                        
                        stringa = "";
                    } else {
                        stringa += character;
                    }
                }
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int counter = 0;

        for (char c : finalString.toCharArray()) {
            if (c == ',') {
                counter++;
            }
        }

        System.out.println(counter);

        return finalString;
    }
}
