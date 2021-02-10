package biblioteca;

public class Ejemplar{
	
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

}
