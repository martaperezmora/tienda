package com.mpm.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpm.springprojects.tienda.dao.ProductosDAO;
import com.mpm.springprojects.tienda.model.Producto;
import com.mpm.springprojects.tienda.services.ProductosService;

@Service
public class ProductoServiceImpl implements ProductosService{

    @Autowired
    ProductosDAO productosDAO;

    @Override
    public List<Producto> findAll() {
        return productosDAO.findAll();
    }

    @Override
    public Producto findById(int codigo) {
        return productosDAO.findById(codigo);
    }

    @Override
    public void insert(Producto Producto) {
        productosDAO.insert(Producto);
    }

    @Override
    public void update(Producto producto) {
        productosDAO.update(producto);

        if(producto.getImagen() != null && producto.getImagen().length > 0){
            productosDAO.updateImage(producto);
        }
    }

    @Override
    public void delete(int codigo) {
        productosDAO.delete(codigo);
    }
    
    
}
