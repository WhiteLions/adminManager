/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

/**
 *
 * @author dideleo
 */
 public final class AutoCustomerBean {
    
    private final int id;
    private final String nombre;
    private final String paterno;
    
    public AutoCustomerBean(int id, String nombre, String paterno){
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
    }
   

    public String getPaterno() {
        return paterno;
    }

    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    
}
