package com.jabonesArtesanales.co.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabonesArtesanales.co.entity.Proveedores;

public interface IProveedoresDao extends JpaRepository<Proveedores, Long> {

}
