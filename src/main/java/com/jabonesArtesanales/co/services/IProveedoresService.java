package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Proveedores;

public interface IProveedoresService {
	
	public List<Proveedores> findAll();
	public Proveedores findById(long id);
	public Proveedores save(Proveedores proveedores);
	public void delete(long id);
	

}
