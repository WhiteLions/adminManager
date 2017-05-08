/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class EquivalenciaBean {
    
    private final UnidadBase from;
    private final UnidadBase to;
    private double qty;
    
    public EquivalenciaBean(UnidadBase from, UnidadBase to,double qty){
        this.from = from;
        this.to = to;
        this.qty = qty;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public UnidadBase getFrom() {
        return from;
    }

    public UnidadBase getTo() {
        return to;
    }
    
    
    
}
