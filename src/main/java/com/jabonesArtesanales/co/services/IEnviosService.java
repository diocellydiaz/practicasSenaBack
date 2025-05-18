package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Envios;

public interface IEnviosService {
	
	public List<Envios> findAll();
	public Envios findById(long Id);
	public Envios save(Envios envios);
	public void delete(long Id);

}
