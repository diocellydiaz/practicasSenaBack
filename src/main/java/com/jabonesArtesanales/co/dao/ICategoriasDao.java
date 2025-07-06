package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Categorias;

public interface ICategoriasDao extends JpaRepository<Categorias, Long> {

}
