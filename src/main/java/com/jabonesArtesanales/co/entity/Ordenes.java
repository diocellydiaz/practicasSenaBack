package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "ordenes")
public class Ordenes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_pedidos;

	private long numeroPedido;
	private Date fechaPedido;
	@Enumerated(EnumType.STRING)
	private DetallesOrden estado;
	@Enumerated(EnumType.STRING)
	private Pagos metodoPago;

	public Ordenes(long numeroPedido, Date fechaPedido, DetallesOrden estado, Pagos metodoPago) {
		this.numeroPedido = numeroPedido;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.metodoPago = metodoPago;
	}

	public long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(long numeroPedido) {
		Random random = new Random();

		this.numeroPedido = random.nextLong(100000000L, 999999999L);
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public DetallesOrden getEstado() {
		return estado;
	}

	public void setEstado(DetallesOrden estado) {
		this.estado = estado;
	}

	public Pagos getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(Pagos metodoPago) {
		this.metodoPago = metodoPago;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ManyToMany
    @JoinTable(
        name = "pedido_producto",
        joinColumns = @JoinColumn(name = "id_pedidos"),
        inverseJoinColumns = @JoinColumn(name = "id_productos")
    )
    private Set<Producto> productos;
    
    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Envios> envios;
    
    public Set<Envios> getEnvios() {
        return envios;
    }

    public void setEnvios(Set<Envios> envios) {
        this.envios = envios;
    }
}
