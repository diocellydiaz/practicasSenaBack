package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IProveedorDao;
import com.jabonesArtesanales.co.entity.Proveedores;

@Service
public class ProveedoresServiceImpl implements IProveedoresService  {
	
	@Autowired
	private IProveedorDao proveedorDao;

	@Override
	public List<Proveedores> findAll() {
		return (List<Proveedores>) proveedorDao.findAll();
	}

	@Override
	public Proveedores findById(long id) {		
		return proveedorDao.findById(id).orElse(null);
	}

	@Override
	public Proveedores save(Proveedores proveedores) {
		return proveedorDao.save(proveedores);
	}

	@Override
	public void delete(long id) {
		proveedorDao.deleteById(id);
		
	}

}
