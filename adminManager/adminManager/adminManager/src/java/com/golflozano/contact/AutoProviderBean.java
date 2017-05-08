/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

/**
 *
 * @author dideleo
 */
public final class AutoProviderBean {
    
    private final int id_proveedor;
    private final String nombre;
    private final String paterno;
    
    public AutoProviderBean(int id,String nombre, String paterno){
        this.id_proveedor = id;
        this.nombre = nombre;
        this.paterno = paterno;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaterno() {
        return paterno;
    }
    
    
    
    
}
