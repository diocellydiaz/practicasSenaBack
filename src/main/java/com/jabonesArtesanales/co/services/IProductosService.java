package com.jabonesArtesanales.co.services;

import java.util.List;


import com.jabonesArtesanales.co.entity.Producto;

public interface IProductosService {

	public List<Producto> findAll();
	public Producto findById(long id);
	public Producto save (Producto productos);
	public void delete(long id);
	public boolean reducirStock(Long id, int cantidad, Producto producto);
	public List<Producto> findByCategoriaId(Long categoriaId);
}
