package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Envios;

public interface IEnvioDAO extends JpaRepository<Envios, Long> {

}
