package com.mpm.springprojects.tienda.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mpm.springprojects.tienda.model.Cliente;

public interface ClientesService {
    public Page<Cliente> findAll(Pageable page);
    public Cliente findById(int codigo);
    public void insert(Cliente Producto);
    public void update(Cliente Producto);
    public void delete(int codigo);
}
