package org.institutoserpis.ad;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PruebaPedido {

	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		System.out.println("Inicio");
		
		entityManagerFactory= Persistence.createEntityManagerFactory("org.institutoserpis.ad");
		persist();
		query();
		
		entityManagerFactory.close();
		System.out.println("Fin");
		
	}
	
	private static void show(Pedido pedido){
		System.out.println(pedido);
		for(PedidoLinea pedidoLinea : pedido.getPedidoLinea())
			System.out.println(pedidoLinea);
	}
	
	private static void query(){
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Pedido> pedidos = entityManager.createQuery("from Pedido",Pedido.class).getResultList();
		for (Pedido pedido : pedidos)
			System.out.println(pedido);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	private static Long persist(){
		System.out.println("persist: ");
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Cliente cliente = entityManager.find(Cliente.class, 1L);
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setFecha(Calendar.getInstance());
		
		for (Long articuloId = 1L; articuloId <=3; articuloId++){
			Articulo articulo = entityManager.find(Articulo.class, articuloId);
			PedidoLinea pedidoLinea = new PedidoLinea();
			pedidoLinea.setPedido(pedido);
			
			//ojo
			pedido.getPedidoLinea().add(pedidoLinea);
		}
		//Todo aqui
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		entityManager.close();
		show(pedido);
		
		return pedido.getId();
	}

}
