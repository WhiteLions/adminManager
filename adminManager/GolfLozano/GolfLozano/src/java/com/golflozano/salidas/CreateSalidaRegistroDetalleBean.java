/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public class CreateSalidaRegistroDetalleBean {
    
    private CreateSalidaProductoBean producto;
    private CreateSalidaUnidadBean unidad;
    private double cantidad;

    public CreateSalidaRegistroDetalleBean(CreateSalidaProductoBean producto, CreateSalidaUnidadBean unidad, double cantidad) {
        this.producto = producto;
        this.unidad = unidad;
        this.cantidad = cantidad;
    }
    
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public CreateSalidaProductoBean getProducto() {
        return producto;
    }

    public void setProducto(CreateSalidaProductoBean producto) {
        this.producto = producto;
    }

    public CreateSalidaUnidadBean getUnidad() {
        return unidad;
    }

    public void setUnidad(CreateSalidaUnidadBean unidad) {
        this.unidad = unidad;
    }
    
    
    
}
