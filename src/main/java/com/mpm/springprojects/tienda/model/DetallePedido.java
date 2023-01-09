package com.mpm.springprojects.tienda.model;

public class DetallePedido {
    private int codigo;
    private int cantidad;
    private double precio;
    private double total;
    private int codigoProducto;
    private int codigoPedido;

    public DetallePedido(int codigo, int cantidad, double precio, double total, int codigoProducto, int codigoPedido) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.codigoProducto = codigoProducto;
        this.codigoPedido = codigoPedido;
    }
    public DetallePedido(int codigo) {
        this.codigo = codigo;
    }
    public DetallePedido() {
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getCodigoProducto() {
        return codigoProducto;
    }
    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public int getCodigoPedido() {
        return codigoPedido;
    }
    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
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
        DetallePedido other = (DetallePedido) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

}
