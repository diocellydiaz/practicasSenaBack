package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Ordenes;

public interface IPedidoDAO extends JpaRepository<Ordenes, Long> {

}
