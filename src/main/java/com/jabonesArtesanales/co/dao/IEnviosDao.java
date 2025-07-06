package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Envios;

public interface IEnviosDao extends JpaRepository<Envios, Long> {

}
