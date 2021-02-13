package biblioteca;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Biblioteca implements Serializable {
	
	public ArrayList<Persona> socios;
	public ArrayList<Libro> libros;
	
	public Biblioteca() {
		
		socios = new ArrayList<Persona>();
		libros = new ArrayList<Libro>();		
		opcion(menu());
		
	}

	private void opcion(int menu) {
		
		switch(menu) {
			
		case 1: altaSocio();
			break;
			
		case 2: altaLibro();
			break;
			
		case 3: altaEjemplar();
			break;
			
		case 4: listarSocios();
			break;
			
		case 5 : listarLibros();
			break;
		
		case 6:  prestamoLibros();
			break;
		
		case 7:  devolucionLibro();
			break;
			
		case 8: situacionSocio();
			break;
		
		case 9: situacionLibro();
			break;
			
		case 10: guardar();
			break;
			
		case 11: cargarInfo();
			break;
		
		default: System.out.println("BYE!!");
			break;
		
		
		}
		
	}

	private void cargarInfo() {
		
		load();
		System.out.println("Cargado");
		opcion(menu());
	}

	private void guardar() {
		
		store();
		System.out.println("Guardado");
		opcion(menu());
	}

	private void situacionLibro() {
		
		Libro l = containsLibro(preguntarLibro());
		
		if(l == null) {
			
			System.out.println("No existe ese libro");
			opcion(menu());
			
		}else {
			
			System.out.println(l.situacionLibro());
			opcion(menu());

		}
			
		
	}

	private void situacionSocio() {
		
		Persona p = containsSocios(preguntarUsuario());
		
		if(p == null) {
			
			System.out.println("No existe ese socio");
			opcion(menu());
			
		}else {
			
			System.out.println(p.situacionPersona());
			opcion(menu());

		}	
		
		
	}

	private void devolucionLibro() {
		
		Persona contains = containsSocios(preguntarUsuario());
		
		if(contains != null) {

				if(contains.devolverEjemplar(preguntarLibro())) {						
					System.out.println("Libro devuelto");
					opcion(menu());
							
				}else {
							
					System.out.println("No tiene ese ejemplar");
					opcion(menu());
							
				}
				
		}else
			opcion(menu());		
	}

	private int preguntarLibro() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime el ISBN del libro: ");
		return sc.nextInt();
	}

	private int preguntarUsuario() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduceme el DNI del socio: ");
		return sc.nextInt();	
		
	}

	private void prestamoLibros() {
		
		
		Persona containsSocio= containsSocios(preguntarUsuario());
		
		if(containsSocio != null) {
		
			
			Libro containsLibros = containsLibro(preguntarLibro());
				
				if(containsLibros != null) {
					
					if(containsSocio.añadirEjemplar(containsLibros)) {
						
						System.out.println("Libro prestado");
						opcion(menu());

						
					}else {
						
						System.out.println("No quedan ejemplares de ese libro");
						opcion(menu());
						
					}
				}else
					opcion(menu());
		}else
			opcion(menu());
	}

	private void listarLibros() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Como quieres mostrar los libros ? ");
		System.out.println("  Titulo [0]");
		System.out.println("  Autor [1]");
		System.out.println("  Cantidad de ejemplares [2]");
		int res = sc.nextInt();
		
		switch(res) {
		
			case 0: listarLTitulo();
				break;
			case 1: listarLAutor();
				break;
			case 2: listarLEjemplares();
				break;
			default: listarLibros();
		
		}
		
	}

	private void listarLEjemplares() {
		
		libros.sort((arg0,  arg1)-> arg0.getEjemplares().size()-arg1.getEjemplares().size());
		mostrarListLibros(libros);
		opcion(menu());
		
	}

	private void listarLAutor() {
		
		libros.sort((arg0,  arg1)-> arg0.getAutor().compareTo(arg1.getAutor()));
		mostrarListLibros(libros);
		opcion(menu());
		
	}

	private void listarLTitulo() {
		
		libros.sort((arg0,  arg1)-> arg0.getTitulo().compareTo(arg1.getTitulo()));
		mostrarListLibros(libros);
		opcion(menu());
		
	}

	private void listarSocios() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quieres mostrar los socios ?");
		System.out.println("  Nombre [0]");
		System.out.println("  Cantidad de Prestados [1]");
		int res = sc.nextInt();
		
		switch(res) {
		
		case 0: listarSNombre();
			break;
		case 1: listarSPrestados();
			break;
		default: listarSocios();
	
		}
	}

	private void listarSPrestados() {
		
		socios.sort(Comparator.comparing(Persona::getnPrestados));			
		mostrarListSocios(socios);
		opcion(menu());
	}

	private void listarSNombre() {
				
		socios.sort((arg0,  arg1)-> arg0.getNombre().compareTo(arg1.getNombre()));
		mostrarListSocios(socios);
		opcion(menu());
	}

	private void altaEjemplar() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceme el ISBN del libro: ");
		int ISBN = sc.nextInt();
		
		Libro contains = containsLibro(ISBN);
		
		if(contains != null){
			
			System.out.println("Cuantos desea añadir?");
			int c = sc.nextInt();
			
			for(int i = 0;i<c;i++)
				new Ejemplar(contains);
			
			System.out.println("Ejemplares añadidos");
			opcion(menu());
			
		}else {
			
			System.out.println("Este libro no se encuentra en nuestra biblioteca");
			altaEjemplar();
		}		
	}

	private void altaLibro() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el autor del libro: ");
		String autor = sc.nextLine();
		System.out.println("Introduce el ISBN del libro: ");
		int ISBN = sc.nextInt();
		System.out.println("Introduceme el titulo del libro: ");
		String titulo = sc.next();
		
		Libro contains = containsLibro(ISBN);
		
		if(contains != null) {
			
			System.out.println("Este libro ya ha sido  añadido anteriormente");
			System.out.println("Quieres añadir un ejemplar? S/N");
			String res = sc.nextLine();
			
				if(res.toUpperCase().compareTo("N") == 0) 
					altaLibro();
				else
					altaEjemplar();					
		}else {
			
			libros.add(new Libro(ISBN,autor,titulo));
			System.out.println("Libro añadido");
			opcion(menu());
			
		}	
	}

	private void altaSocio() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del socio: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce los apellidos del socio: ");
		String apellidos = sc.nextLine();
		System.out.println("Introduceme el DNI del socio: ");
		int DNI = sc.nextInt();
		
		Persona contains = containsSocios(DNI);
		
		if(contains != null) {
			
			System.out.println("Este socio ya ha sido aÃ±adido anteriormente");
			altaSocio();
			
		}else {
			
			socios.add(new Persona(nombre,apellidos,DNI));
			System.out.println("Usuario añadido");
			opcion(menu());
						
		}
	}

	private Libro containsLibro(int ISBN) {
		
		
		for(Libro libro : libros) {
			
			if(libro.getISBN() == ISBN) 
				return libro;	
			
		}	
		
		return null;
		
	}
	
	private Persona containsSocios(int DNI) {
		
		for(Persona p : socios) {
			
			if(p.getDNI() == DNI) 
				
				return p;	
		
		}	
		
		return null;
		
	}
	
	private void mostrarListSocios(ArrayList<Persona> l) {
		
		for(Object o : l) {
			
			System.out.println(o.toString());
			
		}
		
	}
	
	private void mostrarListLibros(ArrayList<Libro> l) {
		
		for(Object o : l) {
				
			System.out.println(o.toString());
				
		}
	}
	
	public void load() {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fichero.txt"))) {

			socios= (ArrayList<Persona>)ois.readObject();
			libros= (ArrayList<Libro>) ois.readObject();
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void store() {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fichero.txt"))) {

			oos.writeObject(socios);
			oos.writeObject(libros);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private int menu() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("BIENVENIDO A LA BIBLIOTECA DE MIGUEL");
		System.out.println("1. Dar de alta a un socio");
		System.out.println("2. Dar de alta a libros");
		System.out.println("3. Dar de alta a un ejemplar");
		System.out.println("4. Listar socios");
		System.out.println("5. Listar libros");
		System.out.println("6. Prestar un libro");
		System.out.println("7. Devolver un libro");
		System.out.println("8. Situacion de socio");
		System.out.println("9. Situacion de libro");
		System.out.println("10. Guardar");
		System.out.println("11. Cargar");
		
		return sc.nextInt();
		
		
	}
	
}
