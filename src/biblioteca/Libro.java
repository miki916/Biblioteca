package biblioteca;

import java.util.ArrayList;

public class Libro {
	
	private int ISBN;
	private String autor;
	private String titulo;
	private ArrayList<Ejemplar> ejemplares;
	
	
	public Libro(int ISBN, String autor, String titulo) {
		
		this.ISBN = ISBN;
		this.autor = autor;
		this.titulo = titulo;
		ejemplares = new ArrayList<Ejemplar>();
		
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(ArrayList<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}
	
	public void añadirEjemplar(Ejemplar e) {
		
		ejemplares.add(e);
		
	}

	@Override
	public String toString() {
		return "ISBN:" + ISBN + "\n" + 
				"Autor:" + autor + "\n" +
				"Titulo:" + titulo + "\n"+ 
				"Ejemplares:" + ejemplares;
	}
	
	



	
}
