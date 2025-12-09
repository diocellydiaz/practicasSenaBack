package com.jabonesArtesanales.co.entity;

import jakarta.persistence.*;
import java.io.Serializable;




@Entity
@Table(name = "detalles_orden")
public class DetallesOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    
   

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public EstadoDetalleOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoDetalleOrden estado) {
		this.estado = estado;
	}

	@Enumerated(EnumType.STRING)
    private EstadoDetalleOrden estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Ordenes orden;

}
