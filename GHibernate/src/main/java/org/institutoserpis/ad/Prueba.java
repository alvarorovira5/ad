package org.institutoserpis.ad;

import java.awt.Event;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//idea: hacer un metodo menu para poner el "menu" para empezar el programa.
		//deja el main solo con el metodo menu() y refactorizar el codigo
		System.out.println("Inicio");
		
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Articulo> result = entityManager.createQuery( "from Articulo", Articulo.class ).getResultList();
		for ( Articulo articulo : result ) {
			System.out.println( "Articulo (" + articulo.getNombre() + ") :: " + articulo.getCategoria()+"::"+articulo );
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		/*
		System.out.println("Borrar");
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		*/
		//ojo en el borrado cuando introduzco la id tengo que poner un L del tipo de datos LONG
	}

}