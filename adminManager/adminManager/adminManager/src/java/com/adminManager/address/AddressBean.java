/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

/**
 *
 * @author dideleo
 */
public final class AddressBean {
    
    private String fiscal;
    private String direccion;
    private String cp;
    private String colonia;
    private String etiqueta;
    private int pais;
    private int estado;
    private int municipio;
    private final int id;
    private String status;
    private String paisStr;
    private String edoStr;
    private String municipioStr;

    
    public AddressBean(){
        this.id = -1;
        this.fiscal = "NO";
    }

    public AddressBean(int id, String fiscal, String direccion, String cp, String colonia, String etiqueta, String status, String paisStr, String edoStr, String municipioStr, int pais, int estado, int municipio) {
        this.id = id;
        this.fiscal = fiscal;
        this.direccion = direccion;
        this.cp = cp;
        this.colonia = colonia;
        this.etiqueta = etiqueta;
        this.status = status;
        this.paisStr = paisStr;
        this.edoStr = edoStr;
        this.municipioStr = municipioStr;
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }
   
    public String getEdoStr() {
        return edoStr;
    }

    public void setEdoStr(String edoStr) {
        edoStr = edoStr.trim();
        edoStr = edoStr.toUpperCase();
        this.edoStr = edoStr;
    }

    public String getMunicipioStr() {
        return municipioStr;
    }

    public void setMunicipioStr(String municipioStr) {
        municipioStr = municipioStr.trim();
        municipioStr = municipioStr.trim();
        this.municipioStr = municipioStr;
    }

    public String getPaisStr() {
        return paisStr;
    }

    public void setPaisStr(String paisStr) {
        paisStr = paisStr.trim();
        paisStr = paisStr.toUpperCase();
        this.paisStr = paisStr;
    }
  
    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        colonia = colonia.trim();
        colonia = colonia.toUpperCase();
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        cp = cp.trim();
        cp = cp.toUpperCase();
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        direccion = direccion.trim();
        direccion = direccion.toUpperCase();
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        etiqueta = etiqueta.trim();
        etiqueta = etiqueta.toUpperCase();
        this.etiqueta = etiqueta;
    }

    public String getFiscal() {
        return fiscal;
    }

    public void setFiscal(String fiscal) {
        fiscal = fiscal.trim();
        fiscal = fiscal.toUpperCase();
        this.fiscal = fiscal;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        
        this.pais = pais;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status.trim();
        status = status.toUpperCase();
        this.status = status;
    }
    
    
    
    
    
}
