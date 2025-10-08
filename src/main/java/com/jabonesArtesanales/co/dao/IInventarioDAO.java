package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jabonesArtesanales.co.entity.Inventario;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario, Long> {

}
