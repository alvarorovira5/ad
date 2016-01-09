import java.sql.*;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
public class GConection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		String cad;
		int op;
		
		
		System.out.println("Elige una opcion" ); 
		System.out.println("0.- Salir" ); 
		System.out.println("1.- Leer" ); //nos pide el id del articulo y se muestran todos los articulos con ese id
		System.out.println("2.- Nuevo" ); // crear nuevo articulo, con todos sus campos, "nombre,precio,categoria" insert
		System.out.println("3.- Editar" ); // hacer un update de un articulo con los campos a modificar
		System.out.println("4.- Eliminar" );  //pide el id y se elimina
		System.out.println("5.- Listar Todos" ); 
		
		do{
		
		
				//System.out.println("Operación: "+op);
				cad=teclado.nextLine();
				op=Integer.parseInt(cad);
				
				switch (op) {
				
		       
		        case 1:  System.out.println("Caso 1");
		        		 leer();
		        		 break;
		        case 2:  System.out.println("Caso 2");
		        		 nuevo();
		                 break;
		        case 3:  System.out.println("Caso 3");
		        		 editar();
		                 break;
		        case 4:  System.out.println("Caso 4");
		        		 eliminar();
		                 break;
		        case 5:  System.out.println("caso 5");
		        		 listarTodos();
		                 break;
		        
		        default: System.out.println("Numero invalido.");
		                 break;      
		       
		    }
		} while(op != 0);
			
	}
	public static void eliminar() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		
		System.out.println("Dime la id del producto que quieres eliminar: ");
		int id;
		
		cad=teclado.nextLine();
		id=Integer.parseInt(cad);
		
		
		String query="DELETE FROM articulos WHERE id="+id;
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.execute();
		
		connection.close();
		
	}
	public static void editar() throws SQLException{
		//por hacer...
		
		//System.out.println("Estoy editando.");
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad,nombre,categoria;
		float precio;
		
		int id;
		System.out.println("Dime la id del producto que quieres actualizar: ");
		cad=teclado.nextLine();
		id=Integer.parseInt(cad);
		
		System.out.println("Nuevo nombre del articulo: ");
		nombre=teclado.nextLine();
		
		System.out.println("Nuevo precio del articulo: ");
		cad=teclado.nextLine();
		precio=Float.parseFloat(cad);
		
		System.out.println("Nueva categoria del articulo: ");
		categoria=teclado.nextLine();
		String query="UPDATE articulos "
				+    "SET nombre=?, precio=?, categoria=?"
				+    "WHERE id="+id;
		
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		
		preparedStmt.setString (1, nombre);
		preparedStmt.setInt(2, (int) precio);
		preparedStmt.setString (3, categoria);
		
		preparedStmt.execute();
		
		connection.close();
		
		
	}
	
	public static void nuevo() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		//Insert
		
		String nombre,categoria;
		float precio;
		
		System.out.println("Introduce el nombre del nuevo articulo: ");
		nombre=teclado.nextLine();
		
		System.out.println("Introduce el precio del nuevo articulo: ");
		cad=teclado.nextLine();
		precio=Float.parseFloat(cad);
		
		System.out.println("Introduce la categoria del nuevo articulo ");
		categoria=teclado.nextLine();
		
		
		String query="INSERT INTO articulos(nombre,precio,categoria) VALUES(?,?,?)";
		
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString (1, nombre);
		preparedStmt.setInt(2, (int) precio);
		preparedStmt.setString (3, categoria);
		
		preparedStmt.execute();
		/*
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("INSERT INTO articulos(nombre,precio,categoria)"
				+ " VALUES("+nombre+","+precio+","+categoria+")");
				
		
		*/
		connection.close();
	}
	
	public static void listarTodos() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		//SELECT
		
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM articulos");
		while (resultSet.next()) {
			System.out.println(resultSet.getString("nombre"));
		}
				
		connection.close();
		
	}
	
	public static void leer() throws SQLException{
		
		//nos pide el id del articulo y se muestran todos los articulos con ese id
		Scanner teclado = new Scanner(System.in);
		String cad;
		int id;
				
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		System.out.println("¿Que artículo quieres? Danos su id: ");
		cad=teclado.nextLine();
		id=Integer.parseInt(cad);
		
		
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT nombre,precio,categoria FROM articulos WHERE id="+id);
		while (resultSet.next()) {
			System.out.println(resultSet.getString("nombre"));
			System.out.println(resultSet.getString("precio"));
			System.out.println(resultSet.getString("categoria"));
		}
		
		connection.close();
	}
}
