package model;

import java.io.Serializable;

import com.raylib.java.textures.Texture2D;

public class Product implements Serializable {
	private double prezzo;
	private String nome, descrizione;
	private int quantita;
	private int codice;
	
	//Constructor -----------------------------------------------
	public Product(double prezzo, String nome, String descrizione, int quantita, int codice) {
		super();
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.codice = codice;
	}
	
	public Product(double prezzo, String nome, String descrizione, int quantita) {
		super();
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantita = quantita;
	}

    //getters & setters -------------------------------------------------
	public double getPrezzo() {
		return prezzo;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getQuantita() {
		return quantita;
	}

	public int getCodice() {
		return codice;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	//toSting ---------------------------------
	@Override
	public String toString() {
		return "Product [prezzo=" + prezzo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", quantita=" + quantita + ", codice=" + codice + "]";
	}

}
