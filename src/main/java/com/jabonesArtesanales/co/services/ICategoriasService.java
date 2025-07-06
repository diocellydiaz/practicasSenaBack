package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Categorias;

public interface ICategoriasService {
	 
	public List<Categorias> findAll();
	public Categorias findById(Long id);
	public Categorias save(Categorias categorias);
	public void delete(Long id); 

}
