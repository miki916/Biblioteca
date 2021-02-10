package biblioteca;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Biblioteca {
	
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
		
		}
		
	}

	private void listarLibros() {
		// TODO Auto-generated method stub
		
	}

	private void listarSocios() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quieres listar los socios por nombre (0) o por cantidad de prestados (1)");
		int res = sc.nextInt();
		
		if(res == 0)
			listarSNombre();
		else if (res == 1)
			listarSPrestados();
		else
			listarSocios();		
	}

	private void listarSPrestados() {
		
		socios.sort(Comparator.comparing(Persona::getnPrestados));			
		mostrarArrayList(socios);
		opcion(menu());
	}

	private void listarSNombre() {
		
		socios.add(new Persona("a", "a", 4));
		
		socios.sort((arg0,  arg1)-> arg0.getNombre().compareTo(arg1.getNombre()));
		mostrarArrayList(socios);
		opcion(menu());
	}

	private void altaEjemplar() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceme el ISBN del libro: ");
		int ISBN = sc.nextInt();
		
		int contains = containsLibro(ISBN);
		
		if(contains != -1){
			
			new Ejemplar(libros.get(contains));
			System.out.println("Ejemplar añadido");
			opcion(menu());
			
		}else {
			
			System.out.println("Este libro no se encuentra en nuestra biblioteca");
			altaEjemplar();
		}		
	}

	private int containsLibro(int ISBN) {
		
		int i = 0;
		
		for(Libro libro : libros) {
			
			if(libro.getISBN() == ISBN) 
				return i;	
			
			i++;	
		}	
		
		return -1;
		
	}

	private void altaLibro() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el ISBN del libro: ");
		int ISBN = sc.nextInt();
		System.out.println("Introduce el autor del libro: ");
		String autor = sc.nextLine();
		System.out.println("Introduceme el titulo del libro: ");
		String titulo = sc.nextLine();
		
		int contains = containsLibro(ISBN);
		
		if(contains != -1) {
			
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
		
		boolean encontrado = false;
		
		for(Persona socio : socios) {
			
			if(socio.getDNI() == DNI) 				
				encontrado = true;			
		}	
		
		if(encontrado) {
			
			System.out.println("Este socio ya ha sido añadido anteriormente");
			altaSocio();
			
		}else {
			
			socios.add(new Persona(nombre,apellidos,DNI));
			System.out.println("Usuario añadido");
			opcion(menu());
						
		}
	}
	
	private void mostrarArrayList(ArrayList<T> l) {
		
		for(Object o : l) {
			
			System.out.println(o.toString());
			
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
