package org.institutoserpis.ad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pedido {
	private long id;
	private Cliente cliente;
	private Calendar fecha;
	private List<PedidoLinea> pedidoLinea = new ArrayList<>();

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="cliente")
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
	

	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	public List<PedidoLinea> getPedidoLinea() {
		return pedidoLinea;
	}

	public void setPedidoLinea(List<PedidoLinea> pedidoLinea) {
		this.pedidoLinea = pedidoLinea;
	}
	
	@Override
	public String toString(){
		return String.format("%s [cliente-%s] %s", 
				id,
				cliente ==null ? null : cliente.getId(),
				fecha == null ? null : fecha.getTime()
		);
	}
}
