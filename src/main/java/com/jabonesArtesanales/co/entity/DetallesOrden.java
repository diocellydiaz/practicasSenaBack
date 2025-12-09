package com.jabonesArtesanales.co.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_orden")
public class DetallesOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoDetalleOrden estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore   // 👈 evitamos ciclos Producto ↔ DetallesOrden si se llega a serializar esta entidad
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    @JsonIgnore   // 👈 opcional, pero ayuda si Ordenes tiene @OneToMany detallesOrden
    private Ordenes orden;

    // ====== CONSTRUCTORES ======

    public DetallesOrden() {
    }

    public DetallesOrden(Long id, Integer cantidad, EstadoDetalleOrden estado, Producto producto, Ordenes orden) {
        this.id = id;
        this.cantidad = cantidad;
        this.estado = estado;
        this.producto = producto;
        this.orden = orden;
    }

    // ====== GETTERS y SETTERS ======

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
        this.orden = orden;
    }
}
