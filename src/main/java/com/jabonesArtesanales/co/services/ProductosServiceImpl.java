package com.jabonesArtesanales.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabonesArtesanales.co.dao.IInventarioDAO;
import com.jabonesArtesanales.co.dao.IProductoDao;
import com.jabonesArtesanales.co.entity.Inventario;
import com.jabonesArtesanales.co.entity.Producto;

@Service
public class ProductosServiceImpl implements IProductosService {

    @Autowired
    private IProductoDao productoDao;

    @Autowired 
    private IInventarioDAO inventarioDao;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public Producto findById(long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void delete(long id) {
        productoDao.deleteById(id);
    }

    @Override
    public boolean reducirStock(Long id, int cantidad, Producto producto) {
        Inventario inventario = producto.getInventario();

        if (inventario == null) {
            throw new IllegalStateException("El producto no tiene inventario asociado");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        if (inventario.getStock() >= cantidad) {
            inventario.setStock(inventario.getStock() - cantidad);
            inventarioDao.save(inventario); // Guarda el inventario actualizado
            return true;
        } else {
            System.out.println("No hay suficiente stock para el producto con ID: " + id);
            return false;
        }
    }
}
