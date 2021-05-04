package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.LibroDao;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroController {

	private List<Libro> libros;
	Connection cn;
	
	public LibroController(Connection cn) {
		
		this.cn=cn;
	}
	
	public List<Libro> getLibros() throws SQLException, CamposVaciosException, IsbnException{
		
		LibroDao biblioteca=new LibroDao(cn);
		
		return biblioteca.getConsultaLibros("select * from libros");
		
	}

}
