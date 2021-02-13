package biblioteca;

import java.io.Serializable;

public class Ejemplar implements Serializable{
	
	private Libro l;
	private Persona socio;
	private int code;
	
	public Ejemplar(Libro l) {
		
		this.l = l;
		socio = null;
		code = l.getEjemplares().size()+1;
		this.l.añadirEjemplar(this);
		
	}

	public Libro getL() {
		return l;
	}

	public void setL(Libro l) {
		this.l = l;
	}

	public Persona getSocio() {
		return socio;
	}

	public void setSocio(Persona socio) {
		this.socio = socio;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public boolean prestado() {
		
		if(socio==null)
			return false;
		
		return true;

	}

	@Override
	public String toString() {
		
		if(socio == null)
		
			return "Code: " + code + "\n" +  "Libro: " + l.getTitulo() + "Socio: null";
		
		else
			
			return "Code: " + code + " " +  "Libro: " + l.getTitulo() + " " + "Socio: " + socio.getNombre();

	}
	
	

}
