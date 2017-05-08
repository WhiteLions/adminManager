/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

/**
 *
 * @author dideleo
 */
public final class ContactProviderBean {
    
    private final int id;
    private final int providerId;
    private String telefono;
    private String email;
    private String celular;
    private String fax;
    private String etiqueta;
    private String nombre;
    private String paterno;
    private String materno;
    private String activo;
    
    public ContactProviderBean(){
        this.id = -1;
        this.providerId = -1;
    }
    
    public ContactProviderBean(int id, int providerId, String telefono, String email, String celular, String fax, String etiqueta, String nombre, String paterno, String materno,String activo) {
        this.id = id;
        this.providerId = providerId;
        this.telefono = telefono;
        this.email = email;
        this.celular = celular;
        this.fax = fax;
        this.etiqueta = etiqueta;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.activo = activo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    public String getCelular() {
        return celular;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getProviderId(){
        return this.providerId;
    }

    public void setCelular(String celular) {
        celular = celular.trim();
        celular = celular.toUpperCase();
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.trim();
        email = email.toUpperCase();
        this.email = email;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        etiqueta = etiqueta.trim();
        etiqueta = etiqueta.toUpperCase();
        this.etiqueta = etiqueta;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        fax = fax.trim();
        fax = fax.toUpperCase();
        this.fax = fax;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        telefono = telefono.trim();
        telefono = telefono.toUpperCase();
        this.telefono = telefono;
    }
    
    
}
