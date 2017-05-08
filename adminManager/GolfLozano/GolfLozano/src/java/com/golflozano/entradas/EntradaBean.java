/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dideleo
 */
public final class EntradaBean {
    
    
    private final int id;
    private int provider;
    private String provider_str;
    private Date audit_fecha;
    private String username;
    private Date fecha;
    private String folio;
    private String pedimento;
    private double tipocambio;
    private double subtotal;
    private double iva;
    private double total;
    private int divisa;
    private String divisa_str;
    
    private final List<EntradaEntry> productosList;

    public EntradaBean(int id, int provider, String provider_str, Date audit_fecha, String username, Date fecha, String folio, String pedimento, double tipocambio, double subtotal, double iva, double total, int divisa,String divisa_str, List<EntradaEntry> productosList) {
        this.id = id;
        this.provider = provider;
        this.provider_str = provider_str;
        this.audit_fecha = audit_fecha;
        this.username = username;
        this.fecha = fecha;
        this.folio = folio;
        this.pedimento = pedimento;
        this.tipocambio = tipocambio;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.divisa = divisa;
        this.divisa_str = divisa_str;
        this.productosList = productosList;
    }
    
    
    public EntradaBean(){
        this.id = -1;
        this.productosList = new ArrayList<EntradaEntry>();
    }
    
    public String getProductListString(){
        
        StringBuilder sb = new StringBuilder();
        
        Iterator<EntradaEntry> it = this.productosList.iterator();
        
        while(it.hasNext()){
            
            EntradaEntry temp = it.next();
            
            sb.append(temp.getParsedInvString());
            sb.append(";");
            
        }
        
        return sb.toString();
    }

    public String getDivisa_str() {
        return divisa_str;
    }

    public void setDivisa_str(String divisa_str) {
        divisa_str =  divisa_str.trim();
        divisa_str = divisa_str.toUpperCase();
        this.divisa_str = divisa_str;
    }
    
    

    public String getProvider_str() {
        return provider_str;
    }

    public void setProvider_str(String provider_str) {
        provider_str = provider_str.trim();
        provider_str = provider_str.toUpperCase();
        this.provider_str = provider_str;
    }
    
    

    public Date getAudit_fecha() {
        return audit_fecha;
    }

    public void setAudit_fecha(Date audit_fecha) {
        this.audit_fecha = audit_fecha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username.toUpperCase();
        username = username.trim();
        this.username = username;
    }

    
    
    public int getId() {
        return id;
    }

    public List<EntradaEntry> getProductosList() {
        return productosList;
    }
    
    public int getDivisa() {
        return divisa;
    }

    public void setDivisa(int divisa) {
        this.divisa = divisa;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        folio = folio.trim();
        folio = folio.toUpperCase();
        this.folio = folio;
    }

    public String getPedimento() {
        return pedimento;
    }

    public void setPedimento(String pedimento) {
        pedimento = pedimento.trim();
        pedimento = pedimento.toUpperCase();
        this.pedimento = pedimento;
    }

    public double getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(double tipocambio) {
        this.tipocambio = tipocambio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
}
