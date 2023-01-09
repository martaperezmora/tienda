package com.mpm.springprojects.tienda.model;

import java.sql.Date;

public class Pedido {
    private int codigo;
    private int codigoCliente;
    private double total;
    private Date fecha;

    public Pedido(int codigo, int codigoCliente, double total, Date fecha) {
        this.codigo = codigo;
        this.codigoCliente = codigoCliente;
        this.total = total;
        this.fecha = fecha;
    }
    public Pedido(int codigo) {
        this.codigo = codigo;
    }
    public Pedido() {
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
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
