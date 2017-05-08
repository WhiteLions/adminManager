/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class CreateSalidaProductoBean {
    
    private final int ID;
    private final String NOMBRE;
    private final int ID_UNIDAD;
    
    public CreateSalidaProductoBean(){
        this.ID = -1;
        this.NOMBRE = "";
        this.ID_UNIDAD = -1;
    }

    public CreateSalidaProductoBean(int ID, String NOMBRE,int ID_UNIDAD) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.ID_UNIDAD = ID_UNIDAD;
    }

    public int getID() {
        return ID;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public int getID_UNIDAD() {
        return ID_UNIDAD;
    }
    
    
    
}
