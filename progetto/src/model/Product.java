package model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1234567L;
	private double prezzo;
	private String nome, descrizione, categoria;
	private int quantita;
	private int codiceProdotto;
	private static int codiceUnico = 0;
	
	//Constructor -----------------------------------------------
	public Product(double prezzo, String nome, String descrizione, String categoria, int quantita) {
		super();
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.quantita = quantita;
		this.codiceProdotto = codiceUnico;
		codiceUnico += 1;
	}
	
	public void cambiaQuantita(int quantita) {
		this.quantita += quantita;
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

	public String getCategoria() {
		return categoria;
	}

	public int getQuantita() {
		return quantita;
	}
	
	public int getCodiceProdotto() {
		return codiceProdotto;
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

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	//toSting ---------------------------------
	@Override
	public String toString() {
		return "Product [prezzo=" + prezzo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", categoria=" + categoria + ", quantita=" + quantita + ", codiceProdotto=" + codiceProdotto + "]";
	}

}
