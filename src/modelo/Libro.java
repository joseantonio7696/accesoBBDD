package modelo;

import java.sql.Date;
import java.sql.Timestamp;

import excepciones.IsbnException;
import excepciones.CamposVaciosException;

public class Libro {
	
	private int idLibros;
	private String titulo;
	private String autor;
	private String editorial;
	private String isbn;
	private boolean prestado;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Timestamp fechaAlta;


	public Libro(String titulo, String autor, String editorial, String isbn) throws CamposVaciosException, IsbnException {
		super();
		
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setIsbn(isbn);
		this.setPrestado(false);
		java.util.Date fecha=new java.util.Date();
		this.setFechaAlta(new java.sql.Timestamp(fecha.getTime()));
		this.setFechaPrestamo(null);
		this.setFechaDevolucion(null);
		
	}

	public Libro(int idLibros, String titulo, String autor, String editorial, String isbn, boolean prestado, Date fechaPrestamo, Date fechaDevolucion, Timestamp fechaAlta) throws CamposVaciosException, IsbnException {
		super();
		this.idLibros = idLibros;
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setIsbn(isbn);
		this.setPrestado(prestado);
		this.setFechaAlta(fechaAlta);
		this.setFechaPrestamo(fechaPrestamo);
		this.setFechaDevolucion(fechaDevolucion);
		
	}

	public String getIdLibros() {
		return Integer.toString(idLibros); //esto va aqui o en el Set???????
	}

	public void setIdLibros(int idLibros) {
		this.idLibros = idLibros;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws CamposVaciosException {
		if (titulo.equals("")) {
			throw new CamposVaciosException();
		}
		
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) throws CamposVaciosException {
		if (autor.equals("")) {
			throw new CamposVaciosException();
		}
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) throws CamposVaciosException {
		if (editorial.equals("")) {
			throw new CamposVaciosException();
		}
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) throws CamposVaciosException, IsbnException {
		if (isbn.equals("")) {
			throw new CamposVaciosException();
		}
		String isbnSinGuiones=isbn.replaceAll("-", "");
		String isbnNum=isbnSinGuiones.substring(0,12);
		String dControl=isbnSinGuiones.substring(12);
		
		
		int suma=0;
		boolean impar=true;
		
		for (int x = 0; x < isbnNum.length(); x++) {
			if (impar) {
				suma+=Integer.parseInt(Character.toString(isbnNum.charAt(x)));
				impar=false;
			}
			else {
				suma+=Integer.parseInt(Character.toString(isbnNum.charAt(x)))*3;
				impar=true;
			}
		}
		
		int dc=10-suma%10;
		if (dc==10) {
			dc=0;
		}
		if (dc==Integer.parseInt(dControl)) {
			this.isbn = isbn;
		}
		else {
			throw new IsbnException();
		}
		
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro = " +idLibros + "," + titulo + "," + autor + "," + editorial+ "," + isbn + "," + prestado + "," + fechaPrestamo + ","+ fechaDevolucion + "," + fechaAlta;
	}

}

