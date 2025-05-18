package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IDireccionEnvioDAO;
import com.jabonesArtesanales.co.entity.DireccionEnvio;

@Service
public class DireccionEnvioServiceImpl implements IDireccionEnvioService {
	
	@Autowired
	private IDireccionEnvioDAO direccionDao;

	@Override
	public List<DireccionEnvio> findAll() {
		return (List<DireccionEnvio>) direccionDao.findAll();
	}

	@Override
	public DireccionEnvio findById(long id) {
		return direccionDao.findById(id).orElse(null);
	}

	@Override
	public DireccionEnvio save(DireccionEnvio dirreccion) {
		return direccionDao.save(dirreccion);
	}

	@Override
	public void delete(long id) {
		direccionDao.deleteById(id);
		
	}

}
