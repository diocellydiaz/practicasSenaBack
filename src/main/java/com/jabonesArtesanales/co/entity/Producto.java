package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoID;

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
    @JoinColumn(name = "categoriaID")
    private Categorias categoria;

    @ManyToOne
    @JoinColumn(name = "proveedorID")
    private Proveedores proveedor;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetallesOrden> detallesOrden;

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    public String getNombrefoto() {
		return nombrefoto;
	}
    
    public void setNombrefoto(String nombrefoto) {
		this.nombrefoto = nombrefoto;
	}
    
}
