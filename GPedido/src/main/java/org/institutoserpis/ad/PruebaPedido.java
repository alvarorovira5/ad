package org.institutoserpis.ad;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.istack.internal.logging.Logger;

public class PruebaPedido {

	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		System.out.println("Inicio");
		
		entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager.createQuery("from Categoria",Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManagerFactory.close();
		System.out.println("Fin");
		
	}

}
