package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Persona implements Serializable{

	private String nombre;
	private String apellidos;
	private int DNI;
	private int nPrestados;
	private ArrayList<Ejemplar> prestados;
	
	public Persona(String nombre, String apellidos, int DNI) {
		
		this.nombre=nombre;
		this.apellidos = apellidos;
		this.DNI = DNI;
		nPrestados = 0;
		prestados = new ArrayList<Ejemplar>();
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
	
		
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public ArrayList<Ejemplar> getPrestados() {
		return prestados;
	}

	public void setPrestados(ArrayList<Ejemplar> prestados) {
		this.prestados = prestados;
	}

	public boolean añadirEjemplar(Libro e) {
		
		if(e.getEjemplares().size()== 0) {
				
			return false;
				
		}else {
			
			
			for(Ejemplar ejem:e.getEjemplares()) {
				
				if(!ejem.prestado()) {
			
					prestados.add(ejem);
					ejem.setSocio(this);
					nPrestados++;
					return true;
					
				}
				
			}
			
			return false;	
		}
				
	}
	
	public boolean devolverEjemplar(int e) {
		
		Ejemplar ejem = containsEjemplar(e);
		
		if(ejem == null)
		
			return false;
		
		else {
			
			prestados.remove(ejem);
			ejem.setSocio(null);
			nPrestados--;
			return true;
		}
		
		
	}
	
	public Ejemplar containsEjemplar(int e) {
		
		
		for(Ejemplar p : prestados) {
			
			if(p.getL().getISBN() == e) {
				
				return p;
			}
			
			
		}
		
		return null;

	}
	
	public String situacionPersona() {
		
		if(prestados.size()==0) {
			
			return "Este Socio no contiene ni un libro";
			
		}else {
			String mostrar= "Total Ejemplares: " + prestados.size() + "\n" + 
					"Ejemplares: " + "\n";
			
			return mostrar + mostrarPrestados();
			
		}
		
	}

	public String mostrarPrestados() {
		
		
		String mostrar = " ";
		
		for(Ejemplar e : prestados) {
			
			mostrar+=e.toString() + "\n";
			
		}
		
		return mostrar;
			
	}

	public String toString() {
		
		return "Nombre completo: " + nombre + " " + apellidos + "\n" +
				" "	+ "DNI: " + DNI + "\n" + 
				" "	+ "Total prestados: " + nPrestados;
		
	}




	
}
