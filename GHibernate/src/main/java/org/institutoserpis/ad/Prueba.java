package org.institutoserpis.ad;

import java.awt.Event;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.sun.istack.internal.logging.Logger;

public class Prueba {

	public static void main(String[] args) throws ParseException {
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
		        			listarPorId();
		        		 break;
		        case 2:  System.out.println("Caso 2");
		        			añadirNuevo();
		                 break;
		        case 3:  System.out.println("Caso 3");
		        			update();
		                 break;
		        case 4:  System.out.println("Caso 4");
		        			borrarArticulo();
		                 break;
		        case 5:  System.out.println("caso 5");
		        			listarArticulos();
		                 break;
		        
		        default: System.out.println("Numero invalido.");
		                 break;      
		       
		    }
		} while(op != 0);
		
		


		
		//idea: hacer un metodo menu para poner el "menu" para empezar el programa.
		//deja el main solo con el metodo menu() y refactorizar el codigo
		
		/*
		 * Listar
		 * 
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Articulo> result = entityManager.createQuery( "from Articulo", Articulo.class ).getResultList();
		for ( Articulo articulo : result ) {
			System.out.println( "Articulo (" + articulo.getNombre() + ") :: " + articulo.getCategoria()+"::"+articulo );
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		*/
		/*
		System.out.println("Borrar");
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		*/
		//ojo en el borrado cuando introduzco la id tengo que poner un L del tipo de datos LONG
	}
	public static EntityManager getEntityManager(){
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
		
	}
	public static void listarArticulos(){
		System.out.println("Listar");
		EntityManager entityManager= getEntityManager();
		entityManager.getTransaction().begin();
		
		List<Articulo> result = entityManager.createQuery( "from Articulo", Articulo.class ).getResultList();
		String resultado="";
		for ( Articulo articulo : result ) {
			//System.out.println( "Articulo (" + articulo.getNombre() + ") :: " + articulo.getCategoria()+"::"+articulo.getPrecio() );
			resultado+=articulo.getNombre()+"\n";
		}
		
		JOptionPane.showMessageDialog(null, resultado);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	public static void borrarArticulo(){
		System.out.println("Borrar articulo");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		long id;
		
		id=(long)Integer.parseInt(JOptionPane.showInputDialog("¿Que artículo quieres borrar? Danos su id: "));
		
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Articulo articulo = entityManager.find(Articulo.class, id);
		
		entityManager.remove(articulo);
		
		JOptionPane.showMessageDialog(null, "Elemento eliminado es: "+articulo.getNombre());
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	public static void añadirNuevo() throws ParseException{
		//persist
		System.out.println("Añadir");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		
		String nombre,categoria;
		String precioS;
		
		nombre=JOptionPane.showInputDialog("Introduce el nombre del nuevo articulo: ");
		
		precioS=JOptionPane.showInputDialog("Introduce el precio del nuevo articulo: ");
		DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance();
		df.setParseBigDecimal(true);
		BigDecimal precio = (BigDecimal) df.parse(precioS);
		
		categoria=JOptionPane.showInputDialog("Introduce la categoria del nuevo articulo ");
		
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		
		Articulo articulo = new Articulo();
		articulo.setNombre(nombre);
		articulo.setCategoria(categoria);
		articulo.setPrecio(precio);
		entityManager.persist(articulo);
		
		JOptionPane.showMessageDialog(null, "Elemento insertado es: "+articulo.getNombre()+" "+articulo.getPrecio()+" "+articulo.getCategoria());
		entityManager.getTransaction().commit();
		entityManager.close();
		
		
	}
	
	public static void listarPorId(){
		System.out.println("find articulo");
		
		Scanner teclado = new Scanner(System.in);
		String cad;
		long id;
		
		
		
		id=(long)Integer.parseInt(JOptionPane.showInputDialog("¿Que artículo quieres? Danos su id: "));
		
		
		
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Articulo articulo = entityManager.find(Articulo.class, id);
		
		//System.out.println( "Articulo (" + articulo.getNombre() + ") :: " + articulo.getCategoria()+"::"+articulo.getPrecio() );
		
		JOptionPane.showMessageDialog(null, articulo.getNombre()+" "+articulo.getPrecio()+" "+articulo.getCategoria());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public static void update() throws ParseException{
		System.out.println("actualizar");
		
		Scanner teclado = new Scanner(System.in);
		String cad,nombre,categoria;
		
		
		long id;
		
		String precioS;
		
		
		
	
		
		id=Integer.parseInt(JOptionPane.showInputDialog("Dime la id del producto que quieres actualizar: "));
		
		nombre=JOptionPane.showInputDialog("Nuevo nombre del articulo: ");
		precioS=JOptionPane.showInputDialog("Nuevo precio del articulo: ");
		DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance();
		df.setParseBigDecimal(true);
		BigDecimal precio = (BigDecimal) df.parse(precioS);
		categoria=JOptionPane.showInputDialog("Nueva categoria del articulo: ");
		
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Articulo articulo = entityManager.find(Articulo.class, id);
		articulo.setNombre(nombre);
		articulo.setPrecio(precio);
		articulo.setCategoria(categoria);
		
		JOptionPane.showMessageDialog(null,"Articulo actualizado "+articulo.getNombre()+" "+articulo.getPrecio()+" "+articulo.getCategoria());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

}