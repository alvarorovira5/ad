package org.institutoserpis.ad;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pedido {
	private long id;
	private Cliente cliente;
	private Calendar fecha;
	

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString(){
		return String.format("%s %s %s", 
				id,
				cliente ==null ? null : cliente.getId(),
				fecha == null ? null : fecha.getTime()
		);
	}
	
	
}
