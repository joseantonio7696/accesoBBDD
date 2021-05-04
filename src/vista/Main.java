package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import controller.LibroController;
import dao.DbConnection;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		DbConnection dbc=new DbConnection();
		
		Connection cn=dbc.getConnection();
		
		LibroController libroController=new LibroController(cn);
		
		List<Libro> listaLibros;
		try {
			listaLibros = libroController.getLibros();
			
			for (Libro libros : listaLibros) {
				
				System.out.println(libros);
			}
		} catch (SQLException | CamposVaciosException | IsbnException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		dbc.disconnect();
		
		

	}

}
