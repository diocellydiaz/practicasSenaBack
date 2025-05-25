package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordenes")
public class Ordenes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedidos;

    private Long numeroPedido;

    private Date fechaPedido;

    @Enumerated(EnumType.STRING)
    private EstadoDetalleOrden estado;

    @Enumerated(EnumType.STRING)
    private Pagos metodoPago;

    @ManyToMany
    @JoinTable(
        name = "pedido_producto",
        joinColumns = @JoinColumn(name = "id_pedidos"),
        inverseJoinColumns = @JoinColumn(name = "id_productos")
    )
    private Set<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Envios> envios;

    /**
     * Método para generar un número de pedido aleatorio
     * Puedes llamarlo desde el servicio o constructor si quieres.
     */
    public void generarNumeroPedido() {
        long min = 100000000L;
        long max = 999999999L;
        this.numeroPedido = (long) (Math.random() * (max - min + 1) + min);
    }

	public Long getId_pedidos() {
		return id_pedidos;
	}

	public void setId_pedidos(Long id_pedidos) {
		this.id_pedidos = id_pedidos;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public EstadoDetalleOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoDetalleOrden estado) {
		this.estado = estado;
	}

	public Pagos getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(Pagos metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Envios> getEnvios() {
		return envios;
	}

	public void setEnvios(Set<Envios> envios) {
		this.envios = envios;
	}
     
    
}
