/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dideleo
 */
public final class CreateSalidaRegistroBean {
    
    private final int idSalida;
    private String folio;
    private int idCliente;
    private int idTipoOrden;
    private String observaciones;
    
    private final List<CreateSalidaRegistroDetalleBean> productosList;

    public CreateSalidaRegistroBean() {
        
        this.idSalida = -1;
        this.productosList = new ArrayList<CreateSalidaRegistroDetalleBean>();
    }

    public List<CreateSalidaRegistroDetalleBean> getProductosList() {
        return productosList;
    }

    public int getIdSalida() {
        return idSalida;
    }
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        folio = folio.toUpperCase();
        this.folio = folio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        observaciones  = observaciones.toUpperCase();
        this.observaciones = observaciones;
    }
    
    
    
    
}
