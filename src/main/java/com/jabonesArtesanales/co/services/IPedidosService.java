package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Ordenes;

public interface IPedidosService {
	
	public List<Ordenes> findAll();
	public Ordenes findById(long Id);
	public Ordenes save(Ordenes pedidos);
	public void delete(long id);
}
