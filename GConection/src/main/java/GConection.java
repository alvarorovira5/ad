import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
public class GConection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		int op=0;
		do{
			
			op =Integer.parseInt(JOptionPane.showInputDialog(""
					+ "Elige una opcion\n"
					+ "0.- Salir\n"
					+ "1.- Leer\n"
					+ "2.- Nuevo\n"
					+ "3.- Editar\n"
					+ "4.- Eliminar\n"
					+ "5.- Listar Todos\n"));

		
//				op = Integer.parseInt(teclado.nextLine());
				
				
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
	
	public static Connection connect() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba","root","");
		System.out.println("Conexion");
		
		return connection;
		
	}
	public static void eliminar() throws SQLException{
		//Connection connection = DriverManager.getConnection(
			//	"jdbc:mysql://localhost/dbprueba","root","");
//		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		
		//System.out.println("Dime la id del producto que quieres eliminar: ");
		int id;
		
//		cad=teclado.nextLine();
//		id=Integer.parseInt(cad);
		
		id=Integer.parseInt(JOptionPane.showInputDialog("Dime la id del producto que quieres eliminar: "));
		
		String query="DELETE FROM articulos WHERE id="+id;
		PreparedStatement preparedStmt = connect().prepareStatement(query);
		preparedStmt.execute();
		
		connect().close();
		
	}
	public static void editar() throws SQLException{
		
		
		//System.out.println("Estoy editando.");
		
//		Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost/dbprueba","root","");
//		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad,nombre,categoria;
		float precio;
		
		int id;
//		System.out.println("Dime la id del producto que quieres actualizar: ");
//		cad=teclado.nextLine();
//		id=Integer.parseInt(cad);
		
		id=Integer.parseInt(JOptionPane.showInputDialog("Dime la id del producto que quieres actualizar: "));
		
//		System.out.println("Nuevo nombre del articulo: ");
//		nombre=teclado.nextLine();
		
		nombre=JOptionPane.showInputDialog("Nuevo nombre del articulo: ");
		
//		System.out.println("Nuevo precio del articulo: ");
//		cad=teclado.nextLine();
//		precio=Float.parseFloat(cad);
		
		precio=Float.parseFloat(JOptionPane.showInputDialog("Nuevo precio del articulo: "));
		
//		System.out.println("Nueva categoria del articulo: ");
//		categoria=teclado.nextLine();
		categoria=JOptionPane.showInputDialog("Nueva categoria del articulo: ");
		String query="UPDATE articulos "
				+    "SET nombre=?, precio=?, categoria=?"
				+    "WHERE id="+id;
		
		PreparedStatement preparedStmt = connect().prepareStatement(query);
		
		preparedStmt.setString (1, nombre);
		preparedStmt.setInt(2, (int) precio);
		preparedStmt.setString (3, categoria);
		
		preparedStmt.execute();
		
		connect().close();
		
		
	}
	
	public static void nuevo() throws SQLException{
//		Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost/dbprueba","root","");
//		System.out.println("Conexion");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		//Insert
		
		String nombre,categoria;
		float precio;
		
//		System.out.println("Introduce el nombre del nuevo articulo: ");
//		nombre=teclado.nextLine();
		
		nombre=JOptionPane.showInputDialog("Introduce el nombre del nuevo articulo: ");
		
//		System.out.println("Introduce el precio del nuevo articulo: ");
//		cad=teclado.nextLine();
//		precio=Float.parseFloat(cad);
		
		precio=Float.parseFloat(JOptionPane.showInputDialog("Introduce el precio del nuevo articulo: "));
		
//		System.out.println("Introduce la categoria del nuevo articulo ");
//		categoria=teclado.nextLine();
		
		categoria=JOptionPane.showInputDialog("Introduce la categoria del nuevo articulo ");
		
		
		String query="INSERT INTO articulos(nombre,precio,categoria) VALUES(?,?,?)";
		
		PreparedStatement preparedStmt = connect().prepareStatement(query);
		preparedStmt.setString (1, nombre);
		preparedStmt.setInt(2, (int) precio);
		preparedStmt.setString (3, categoria);
		
		preparedStmt.execute();
		/*
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("INSERT INTO articulos(nombre,precio,categoria)"
				+ " VALUES("+nombre+","+precio+","+categoria+")");
				
		
		*/
		connect().close();
	}
	
	public static void listarTodos() throws SQLException{
//		Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost/dbprueba","root","");
//		System.out.println("Conexion");
		
		//SELECT
		
		Statement statement = (Statement) connect().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM articulos");
		String resultado="";
		while (resultSet.next()) {
			//System.out.println(resultSet.getString("nombre"));
			resultado+=resultSet.getString("nombre")+"\n";
		}
		JOptionPane.showMessageDialog(null, resultado);
				
		connect().close();
		
	}
	
	public static void leer() throws SQLException{
		
		//nos pide el id del articulo y se muestran todos los articulos con ese id
		Scanner teclado = new Scanner(System.in);
		String cad;
		int id;
				
//		Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost/dbprueba","root","");
//		System.out.println("Conexion");
		
		//System.out.println("¿Que artículo quieres? Danos su id: ");
		//cad=teclado.nextLine();
		id=Integer.parseInt(JOptionPane.showInputDialog("¿Que artículo quieres? Danos su id: "));
		
		
		
		Statement statement = (Statement) connect().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT nombre,precio,categoria FROM articulos WHERE id="+id);
		String resultado="";
		while (resultSet.next()) {
//			System.out.println(resultSet.getString("nombre"));
//			System.out.println(resultSet.getString("precio"));
//			System.out.println(resultSet.getString("categoria"));
			resultado+=resultSet.getString("nombre")+" "+resultSet.getString("precio")+" "+resultSet.getString("categoria");
		}
		
		JOptionPane.showMessageDialog(null, resultado);
		
		connect().close();
	}
}
