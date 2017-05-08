/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class CreateSalidaClienteBean {
    
    private final int ID;
    private final String NOMBRE;
    private final String PATERNO;

    public CreateSalidaClienteBean(int ID, String NOMBRE, String PATERNO) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.PATERNO = (PATERNO == null ? "" : PATERNO);
    }

    public int getID() {
        return ID;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getPATERNO() {
        return PATERNO;
    }
    
}
