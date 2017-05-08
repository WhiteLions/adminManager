/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class CreateSalidaTipoOrdenBean {
    
    private final int ID;
    private final String NOMBRE;

    public CreateSalidaTipoOrdenBean(int ID, String NOMBRE) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
    }

    public int getID() {
        return ID;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }
    
    
    
}
