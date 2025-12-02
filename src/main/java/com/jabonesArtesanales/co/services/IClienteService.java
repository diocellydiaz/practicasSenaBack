package com.jabonesArtesanales.co.services;

import java.util.List;

import com.jabonesArtesanales.co.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public Cliente save (Cliente cliente);
	public void delete(Long id);
	public Cliente findByEmail(String correoElectronico);


}
