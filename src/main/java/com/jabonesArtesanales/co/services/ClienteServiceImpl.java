package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IClienteDao;
import com.jabonesArtesanales.co.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return  clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		
	      if (cliente.getPassword() != null && !cliente.getPassword().isBlank()) {
	            cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
	        }
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}
	
    public Cliente findByCorreoElectronico(String correoElectronico) {
        return clienteDao.findByCorreoElectronico(correoElectronico);
    }

}
