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
	
	public boolean agregar ( String titulo,String autor, String editorial, String isbn) throws CamposVaciosException, IsbnException, SQLException {
		
		boolean agregado=false;
		
		Libro libro=new Libro(titulo, autor, editorial, isbn);
		
		LibroDao libroDao=new LibroDao(cn);
		
		libroDao.agregarLibroPst(libro);
		
		agregado=true;
		
		return agregado;
	}

	public List<Libro> getConsulta(String sql) throws SQLException, CamposVaciosException, IsbnException{
		
		LibroDao biblioteca=new LibroDao(cn);
		
		return biblioteca.getConsultaLibros(sql);
	}
}
