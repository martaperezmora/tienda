package com.mpm.springprojects.tienda.model;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int codigo;
    private Cliente cliente;
    private List<Producto> productos;
    private Date fecha;
    private double importeTotal;

    
    public Pedido(int codigo, Cliente cliente, List<Producto> productos, Date fecha, double importeTotal) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.productos = productos;
        this.fecha = fecha;
        this.importeTotal = importeTotal;
    }
    public Pedido(int codigo) {
        this.codigo = codigo;
    }
    public Pedido() {
    }


    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public double getImporteTotal() {
        return importeTotal;
    }
    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
