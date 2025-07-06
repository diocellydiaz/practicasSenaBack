package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.ICategoriasDao;
import com.jabonesArtesanales.co.entity.Categorias;

@Service
public class CategoriasServiceImpl implements ICategoriasService{
	
	@Autowired
	private ICategoriasDao categoriasDao;
	
	@Override
	public List<Categorias> findAll() {
		return (List<Categorias>) categoriasDao.findAll();
	}

	@Override
	public Categorias findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorias save(Categorias categorias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		categoriasDao.deleteById(id);
	}

}
