package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Inventario;

public interface IInventarioService {
	
	public List<Inventario> findAll();
	public Inventario findById(Long id); 
	public Inventario save (Inventario inventario);
	public void deleteById (Long Id);

}
