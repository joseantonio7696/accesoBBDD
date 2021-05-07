package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroDao {
	
	private Connection cn; //Importamos la libreria de java.sql

	public LibroDao(Connection cn) {
		
		this.cn=cn;
	}
	
	
	
	public List<Libro> getConsultaLibros(String sql) throws SQLException, CamposVaciosException, IsbnException{
		
		List<Libro> listaLibros=new ArrayList<Libro>();
		
		//sql="select * from libros where autor = ?";
		//sql="select * from libros where autor = ? and idlibros=?";
		
		PreparedStatement pst=cn.prepareStatement(sql);
		
		
		//pst.setString(1, "Perez Reverte;Arturo"); //pst.setString(2, "14");
		
		//pst.setString(1, "Auel,Jean M.");
		
		ResultSet rs=pst.executeQuery();
		
		Libro libro;
		
		while (rs.next()) {
			int id=rs.getInt("idlibros");
			String titulo = rs.getString("titulo");
			String autor = rs.getString("autor");
			String editorial = rs.getString("editorial");
			String isbn = rs.getString("isbn");
			boolean prestado = rs.getBoolean("prestado");
			Date fechaPrestamo = rs.getDate("fechaPrestamo");
			Date fechaDevolucion = rs.getDate("fechaDevolucion");
			Timestamp fechaAlta = rs.getTimestamp("fechaAlta");
			libro = new Libro(id, titulo, autor, editorial, isbn, prestado, fechaPrestamo, fechaDevolucion, fechaAlta);
			
			listaLibros.add(libro);
			libro=null;
		}
		
		return listaLibros;
		
	}
	
	public boolean agregarLibroPst(Libro libro) throws SQLException {
		
		boolean agregado=false;
		
		String titulo = libro.getTitulo();
		String autor = libro.getAutor();
		String editorial = libro.getEditorial();
		String isbn = libro.getIsbn();
		boolean prestado= libro.isPrestado();
		//idLibro,titulo,autor,editorial,prestado,fechaPrestamo,fechaDevolucion,isbn,fechaAlta
		String sql = "insert into libros values (?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement= cn.prepareStatement(sql);
		
		java.util.Date date=new java.util.Date();
		Timestamp time=new Timestamp(date.getTime()); //El campo fecha alta coge la fecha del sistema
		
		preparedStatement.setInt(1, 0);	//le mandamos cualquier cosa porque es un campo autonumerico,sino daria error
		preparedStatement.setString(2, titulo);
		preparedStatement.setString(3, autor);
		preparedStatement.setString(4, editorial);
		preparedStatement.setBoolean(5, prestado);
		preparedStatement.setDate(6, null);
		preparedStatement.setDate(7, null);
		preparedStatement.setString(8, isbn);
		preparedStatement.setTimestamp(9, time);
		preparedStatement.executeUpdate();
		preparedStatement=null;
		
		agregado=true;
		
				
		return agregado;
		
		
	}

}
