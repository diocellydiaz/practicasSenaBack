package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Ordenes;

public interface IOrdenesDAO extends JpaRepository<Ordenes, Long> {

}
