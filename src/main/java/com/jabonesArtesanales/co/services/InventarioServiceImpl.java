package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jabonesArtesanales.co.dao.IInventarioDAO;
import com.jabonesArtesanales.co.entity.Inventario;

public class InventarioServiceImpl implements IInventarioService {
	
	@Autowired
	private IInventarioDAO inventarioDao;

	@Override
	public List<Inventario> findAll() {
		// TODO Auto-generated method stub
		return (List<Inventario>) inventarioDao.findAll();
	}

	@Override
	public Inventario findById(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.findById(id).orElse(null);
	}

	@Override
	public Inventario save(Inventario inventario) {
		// TODO Auto-generated method stub
		return inventarioDao.save(inventario);
	}

	@Override
	public void deleteById(Long Id) {
		inventarioDao.deleteById(Id);
	}

	
	
}
