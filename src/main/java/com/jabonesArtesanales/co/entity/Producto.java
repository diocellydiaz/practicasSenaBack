package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoID;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private BigDecimal precio;

    @Column(name = "nombre_foto")
    private String nombrefoto;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "categoriaid")
    private Categorias categoria;

    @ManyToOne
    @JoinColumn(name = "proveedorID")
    private Proveedores proveedor;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonIgnore   // 👈 IMPORTANTÍSIMO: que no intente cargar la colección LAZY al convertir a JSON
    private List<DetallesOrden> detallesOrden;

    // ====== CONSTRUCTORES ======

    public Producto() {
    }

    public Producto(
            Long productoID,
            String nombre,
            String descripcion,
            BigDecimal precio,
            String nombrefoto,
            Cliente cliente,
            Categorias categoria,
            Proveedores proveedor,
            Inventario inventario,
            List<DetallesOrden> detallesOrden) {

        this.productoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombrefoto = nombrefoto;
        this.cliente = cliente;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.inventario = inventario;
        this.detallesOrden = detallesOrden;
    }

    // ====== GETTERS y SETTERS ======

    public Long getProductoID() {
        return productoID;
    }

    public void setProductoID(Long productoID) {
        this.productoID = productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombrefoto() {
        return nombrefoto;
    }

    public void setNombrefoto(String nombrefoto) {
        this.nombrefoto = nombrefoto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<DetallesOrden> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(List<DetallesOrden> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }
}
