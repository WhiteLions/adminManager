/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.provider;

/**
 *
 * @author dideleo
 */
public final class ProviderBean {
    
    private final int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String rfc;
    private String pfisica;
    private String formasPago;
    private String desc;
    private String web;
    private String domicilio;
    private String activo;
    
    public ProviderBean(){
        this.id = -1;
        this.pfisica = "SI";
        this.domicilio = "NO";
    }

    public ProviderBean(int id, String nombre, String paterno, String materno, String rfc, String pfisica, String formasPago, String desc, String web, String domicilio, String activo) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.rfc = rfc;
        this.pfisica = pfisica;
        this.formasPago = formasPago;
        this.desc = desc;
        this.web = web;
        this.domicilio = domicilio;
        this.activo = activo;
    }
    
    

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        activo = activo.trim();
        activo = activo.toUpperCase();
        this.activo = activo;
    }
    
    
    
    public int getId(){
        return this.id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        desc = desc.trim();
        desc = desc.toUpperCase();
        this.desc = desc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        domicilio = domicilio.trim();
        domicilio = domicilio.toUpperCase();
        this.domicilio = domicilio;
    }

    public String getFormasPago() {
        return formasPago;
    }

    public void setFormasPago(String formasPago) {
        formasPago = formasPago.trim();
        formasPago = formasPago.toUpperCase();
        this.formasPago = formasPago;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        web = web.trim();
        this.web = web;
    }
    
    
    
}
