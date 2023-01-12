package com.mpm.springprojects.tienda.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mpm.springprojects.tienda.dao.ClientesDAO;
import com.mpm.springprojects.tienda.dao.DetallePedidoDAO;
import com.mpm.springprojects.tienda.dao.PedidosDAO;
import com.mpm.springprojects.tienda.model.Cliente;
import com.mpm.springprojects.tienda.model.DetallePedido;
import com.mpm.springprojects.tienda.model.Pedido;
import com.mpm.springprojects.tienda.services.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    PedidosDAO pedidosDAO;

    @Autowired
    DetallePedidoDAO detallePedidoDAO;

    @Autowired
    ClientesDAO clientesDAO;

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return pedidosDAO.findAll(pageable);
    }

    @Override
    public Pedido findById(int codigo) {
        Pedido pedido = pedidosDAO.findById(codigo);

        Cliente cliente = clientesDAO.findById(pedido.getCliente().getCodigo());

        pedido.setCliente(cliente);

        List<DetallePedido> detalle = detallePedidoDAO.findDetalle(pedido);
        pedido.setDetallePedidos(detalle);
        
        return pedido;
    }

    @Override
    public void insert(Pedido pedido) {
        
        pedidosDAO.insert(pedido);

        List<DetallePedido> detallePedidos = pedido.getDetallePedidos();
        for (DetallePedido detallePedido : detallePedidos) {
            detallePedidoDAO.insert(pedido, detallePedido);
        }

    }

    @Override
    public void update(Pedido pedido) {
        pedidosDAO.update(pedido);
     }

    @Override
    public void delete(int codigo) {
        pedidosDAO.delete(codigo);        
    }
}
