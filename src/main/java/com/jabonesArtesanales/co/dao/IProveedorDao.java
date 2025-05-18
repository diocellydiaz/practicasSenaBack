package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Proveedores;

public interface IProveedorDao extends JpaRepository<Proveedores, Long> {

}
