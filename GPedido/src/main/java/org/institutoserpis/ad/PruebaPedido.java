package org.institutoserpis.ad;

import java.util.logging.Level;

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
		
		entityManagerFactory.close();
		System.out.println("Fin");
		
	}

}
