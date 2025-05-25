package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IOrdenesDAO;
import com.jabonesArtesanales.co.entity.Ordenes;

@Service
public class OrdenesServiceImpl implements IOrdenesService{

	@Autowired
	private IOrdenesDAO pedidoDAO;

	@Override
	public List<Ordenes> findAll() {
		
		return (List<Ordenes>) pedidoDAO.findAll();
	}

	@Override
	public Ordenes findById(long Id) {
		
		return pedidoDAO.findById(Id).orElse(null);
	}

	@Override
	public Ordenes save(Ordenes pedidos) {
		
		return pedidoDAO.save(pedidos);
	}

	@Override
	public void delete(long id) {
		pedidoDAO.deleteById(id);
		
	}
	
	
	
}
