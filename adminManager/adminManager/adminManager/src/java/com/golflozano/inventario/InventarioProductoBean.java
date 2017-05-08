/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.inventario;

/**
 *
 * @author dideleo
 */
public final class InventarioProductoBean {
    
    private final int ID_PRODUCTO;
    private final double EXISTENCIA;
    private final String NOMBRE;
    private final String UNIDAD;
    private final String UBICACION;

    public InventarioProductoBean(int ID_PRODUCTO, double EXISTENCIA, String NOMBRE, String UNIDAD, String UBICACION) {
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.EXISTENCIA = EXISTENCIA;
        this.NOMBRE = NOMBRE;
        this.UNIDAD = UNIDAD;
        this.UBICACION = UBICACION;
    }
    
    public double getEXISTENCIA() {
        return EXISTENCIA;
    }

    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getUBICACION() {
        return UBICACION;
    }

    public String getUNIDAD() {
        return UNIDAD;
    }
    
    
}
