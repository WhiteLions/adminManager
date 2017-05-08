/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

/**
 *
 * @author dideleo
 */
public final class EstadoBean {
    
    private final int id;
    private final String nombre;
    
    public EstadoBean(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
}
