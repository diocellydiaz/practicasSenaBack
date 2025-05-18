package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.DireccionEnvio;

public interface IDireccionEnvioService {
	
	public List<DireccionEnvio> findAll();
	public DireccionEnvio findById(long id);
	public DireccionEnvio save(DireccionEnvio dirreccion);
	public void delete(long id);
}
