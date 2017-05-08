/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import com.golflozano.catalogs.UnidadBase;
import com.golflozano.products.ProductoBean;

/**
 *
 * @author dideleo
 */
public final class EntradaEntry {
    
    private final ProductoBean product;
    private final UnidadBase unidad;
    private final Integer cantidad;
    private final Double precio_unitario;

    public EntradaEntry(ProductoBean product, UnidadBase unidad, Integer cantidad, Double precio_unitario) {
        this.product = product;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
    
    public String getParsedInvString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.product.getId_producto());
        sb.append("|");
        sb.append(this.unidad.getId());
        sb.append("|");
        sb.append(this.precio_unitario);
        sb.append("|");
        sb.append(this.cantidad);
        
        return sb.toString();
        
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public ProductoBean getProduct() {
        return product;
    }

    public UnidadBase getUnidad() {
        return unidad;
    }
    
    public Double getImporte(){
        return this.cantidad * this.precio_unitario;
    }
      
}
