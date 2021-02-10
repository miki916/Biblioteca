package biblioteca;

import java.util.Comparator;

public class Persona implements Comparator{

	private String nombre;
	private String apellidos;
	private int DNI;
	private int nPrestados;
	
	public Persona(String nombre, String apellidos, int DNI) {
		
		this.nombre=nombre;
		this.apellidos = apellidos;
		this.DNI = DNI;
		nPrestados = 0;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellidos;
	}
	public void setApellido(String apellido) {
		this.apellidos = apellido;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public int getnPrestados() {
		return nPrestados;
	}
	public void setnPrestados(int nPrestados) {
		this.nPrestados = nPrestados;
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {

		if(arg0 instanceof Persona && arg1 instanceof Persona) {
			
			if(((Persona)arg0).getNombre().compareTo(((Persona) arg1).getNombre()) < 0) 				
				return -1;
			
			else if(((Persona)arg0).getNombre().compareTo(((Persona) arg1).getNombre()) > 0)
				return 1;
						
			else 	
				return 0;	
			
		}
		
		return 0;
	}
	
	public String toString() {
		
		return "Nombre completo: " + nombre + " " + apellidos + "\n" +
				" "	+ "DNI: " + DNI + "\n" + 
				" "	+ "Total prestados: " + nPrestados + "\n";
		
	}



	
}
