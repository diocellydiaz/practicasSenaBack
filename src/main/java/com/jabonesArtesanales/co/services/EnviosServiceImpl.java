package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IEnvioDAO;
import com.jabonesArtesanales.co.entity.Envios;

@Service
public class EnviosServiceImpl implements IEnviosService {

	@Autowired
	private IEnvioDAO envioDao;

	@Override
	public List<Envios> findAll() {
		
		return (List<Envios>) envioDao.findAll();
	}

	@Override
	public Envios findById(long Id) {
		
		return envioDao.findById(Id).orElse(null);
	}

	@Override
	public Envios save(Envios envios) {
		
		return envioDao.save(envios);
	}

	@Override
	public void delete(long Id) {
		envioDao.deleteById(Id);
		
	}
	
	
}
