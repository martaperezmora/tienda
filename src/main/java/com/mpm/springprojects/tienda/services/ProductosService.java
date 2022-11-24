package com.mpm.springprojects.tienda.services;

import java.util.List;

import com.mpm.springprojects.tienda.model.Producto;

public interface ProductosService {
    
    public List<Producto> findAll();
    public Producto findById(int codigo);
    public void insert(Producto Producto);
    public void update(Producto Producto);
    public void delete(int codigo);
}
