/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class BriefSalidaDetalleBean {
    
    private final String PRODUCTO;
    private final String UNIDAD;
    private final double CANTIDAD;

    public BriefSalidaDetalleBean(String PRODUCTO, String UNIDAD, double CANTIDAD) {
        this.PRODUCTO = PRODUCTO;
        this.UNIDAD = UNIDAD;
        this.CANTIDAD = CANTIDAD;
    }

    public double getCANTIDAD() {
        return CANTIDAD;
    }

    public String getPRODUCTO() {
        return PRODUCTO;
    }

    public String getUNIDAD() {
        return UNIDAD;
    }    
    
}
