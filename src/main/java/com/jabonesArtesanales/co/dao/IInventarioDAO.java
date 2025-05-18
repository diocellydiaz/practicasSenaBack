package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Inventario;

public interface IInventarioDAO extends JpaRepository<Inventario, Long> {

}
