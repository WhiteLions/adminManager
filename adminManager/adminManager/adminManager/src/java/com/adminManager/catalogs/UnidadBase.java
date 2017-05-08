/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class UnidadBase {
    
    private String nombre;
    private String activo;
    private final Integer id;

    
    public UnidadBase(Integer id, String nombre, String activo){
        
        super();
        
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        
    }
    
    public UnidadBase(){
        this.id = -1;
    }
    
    
    public Integer getId() {
        return id;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre.toUpperCase();
        this.nombre = nombre;
    }
    
    
    
}