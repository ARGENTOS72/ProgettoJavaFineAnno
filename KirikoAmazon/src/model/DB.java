package model;

import java.io.File;
import java.io.IOException;

public class DB {
	private static String dbName = "test.db";
	private File file;
	
	public DB() {
		File file = new File(dbName);
		
		try {
			if (file.createNewFile()) {
				// Inizializza db
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.file = file;
	}
	
	private static void inizializzaDB() {
		// ...
	}
}
