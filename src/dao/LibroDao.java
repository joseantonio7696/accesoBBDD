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

}
