package com.jabonesArtesanales.co.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inventario")
public class Inventario {
	
	private int stock;
	
	
	
    public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	@OneToOne
    @JoinColumn(name = "ProductoID")
    private Producto producto;
	

}
