package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		//*************************************************************************
		
		System.out.println("AGREGAR UN LIBRO.........");
		 //Establecemos la conexion
		
		dbc=new DbConnection();
		cn=dbc.getConnection();
		
		String titulo="Arquictecta en Apuros",autor="Garcia Saen de Urturi;Eva",editorial="Planeta",isbn="978-84-08-23777-8";
		
		//Instanciamos el controlador
		
		libroController=new LibroController(cn);
		
		
		try {
			if (libroController.agregar(titulo, autor, editorial, isbn)) {
				System.out.println("EL LIBRO SE AGREGO CORRECTAMENTE");
			}
		} catch (CamposVaciosException | IsbnException | SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		libroController=null;
		
		dbc.disconnect();
		dbc=null;
		
		//********************************************************************
		
		String campo="autor",cadenaBusqueda="Auel,Jean M.";
		
		String sql="select * from libros where "+campo+" = '"+cadenaBusqueda+"'";
		
		List<Libro> listaConsulta;
		
		dbc=new DbConnection();
		cn=dbc.getConnection();
		
		libroController=new LibroController(cn);
		
		try {
			listaConsulta=libroController.getConsulta(sql);
			
			for (Libro libro : listaConsulta) {
				System.out.println(libro);
			}
		} catch (SQLException | CamposVaciosException | IsbnException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		libroController=null;
		dbc.disconnect();
		dbc=null;
		
		
	}

}
