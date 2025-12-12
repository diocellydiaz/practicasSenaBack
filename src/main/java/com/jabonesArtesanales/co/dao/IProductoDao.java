package com.jabonesArtesanales.co.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long> {
	
	 List<Producto> findByCategoriaId(Long categoriaId);

}
