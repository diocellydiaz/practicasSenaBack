package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name ="producto")
public class Producto implements Serializable{
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer productoID;

	    private String nombre;

	    @Column(columnDefinition = "TEXT")
	    private String descripcion;

	    private BigDecimal precio;

	    private Inventario inventarios;
	    
	    @ManyToOne
	    @JoinColumn(name = "categoriaID")
	    private Categorias categoria;

	    @ManyToOne
	    @JoinColumn(name = "proveedorID")
	    private Proveedores proveedor;
	    
	    @ManyToOne
	    @JoinColumn(name="inventarioId")
	    private Inventario inventario;

	    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	    private Set<DetallesOrden> detallesOrden;

	    // Getters y setters

	    public Integer getProductoID() {
	        return productoID;
	    }

	    public void setProductoID(Integer productoID) {
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
 
	    
	    public Inventario getInventario() {
			return inventarios;
		}

		public void setInventario(Inventario inventarios) {
			this.inventario = inventarios;
		}





		private static final long serialVersionUID = 1L;
	}

