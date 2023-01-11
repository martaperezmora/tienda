package com.mpm.springprojects.tienda.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int codigo;
    private Cliente cliente;
    private List<DetallePedido> detallePedidos;
    private double total;
    private Date fecha;

    public Pedido() {
        this.cliente = new Cliente();
        this.detallePedidos = new ArrayList<DetallePedido>();
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        cliente = cliente;
    }
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

}
