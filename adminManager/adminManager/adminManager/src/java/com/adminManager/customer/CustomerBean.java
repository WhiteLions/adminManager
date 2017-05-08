/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.customer;

/**
 *
 * @author dideleo
 */
public final class CustomerBean {
    
    private final int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String rfc;
    private String pfisica;
    private String desc;
    private String activo;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    
    public CustomerBean(){
        this.id = -1;
        this.pfisica = "SI";
    }
    
    public CustomerBean(int id, String nombre, String paterno, String materno, String rfc, String pfisica, String desc, String activo){
        
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.rfc = rfc;
        this.pfisica = pfisica;
        this.desc = desc;
        this.activo = activo;
        
    }
    
    public CustomerBean(int id, String nombre, String paterno, String materno, String rfc, String pfisica){
        
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.rfc = rfc;
        this.pfisica = pfisica;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        desc = desc.trim();
        desc = desc.toUpperCase();
        this.desc = desc;
    }
    
    
    
    public int getId(){
        return this.id;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        materno = materno.trim();
        materno = materno.toUpperCase();
        this.materno = materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre.trim();
        nombre = nombre.toUpperCase();
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        paterno = paterno.trim();
        paterno = paterno.toUpperCase();
        this.paterno = paterno;
    }

    public String getPfisica() {
        return pfisica;
    }

    public void setPfisica(String pfisica) {
        pfisica = pfisica.trim();
        pfisica = pfisica.toUpperCase();
        this.pfisica = pfisica;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        rfc = rfc.trim();
        rfc = rfc.toUpperCase();
        this.rfc = rfc;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    
}
