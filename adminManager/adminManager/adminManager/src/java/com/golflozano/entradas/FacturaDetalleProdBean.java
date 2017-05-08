/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

/**
 *
 * @author dideleo
 */
public final class FacturaDetalleProdBean {

    private String prod_nombre;
    private String unidad_nombre;
    private Double precio_unitario;
    private Integer cantidad;

    public FacturaDetalleProdBean(String prod_nombre, String unidad_nombre, Double precio_unitario, Integer cantidad) {
        this.prod_nombre = prod_nombre;
        this.unidad_nombre = unidad_nombre;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getProd_nombre() {
        return prod_nombre;
    }

    public void setProd_nombre(String prod_nombre) {
        this.prod_nombre = prod_nombre;
    }

    public String getUnidad_nombre() {
        return unidad_nombre;
    }

    public void setUnidad_nombre(String unidad_nombre) {
        this.unidad_nombre = unidad_nombre;
    }
    
    
    
    
}


