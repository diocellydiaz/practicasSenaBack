package com.jabonesArtesanales.co.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer productoID;

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
