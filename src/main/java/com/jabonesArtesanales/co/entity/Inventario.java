package com.jabonesArtesanales.co.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long  productoID;

    @OneToOne
    @MapsId
    @JoinColumn(name = "productoID")
    private Producto producto;

    private int stock;
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
    
}
