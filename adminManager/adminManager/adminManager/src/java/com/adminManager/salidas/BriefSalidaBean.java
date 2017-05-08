/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dideleo
 */
public final class BriefSalidaBean {
    
    private final int ID_SALIDA;
    private final Date FECHA;
    private final String TIPO_ORDEN;
    private final String FOLIO;
    private final String CLIENTE;
    private final String OBSERVACIONES;
    private List<BriefSalidaDetalleBean> listaDetalle;

    public BriefSalidaBean(int ID_SALIDA, Date FECHA, String TIPO_ORDEN, String FOLIO, String CLIENTE, String OBSERVACIONES) {
        this.ID_SALIDA = ID_SALIDA;
        this.FECHA = FECHA;
        this.TIPO_ORDEN = TIPO_ORDEN;
        this.FOLIO = FOLIO;
        this.CLIENTE = CLIENTE;
        this.OBSERVACIONES = OBSERVACIONES;
        this.listaDetalle = new ArrayList<BriefSalidaDetalleBean>();
    }
    

    public String getCLIENTE() {
        return CLIENTE;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public String getFOLIO() {
        return FOLIO;
    }

    public int getID_SALIDA() {
        return ID_SALIDA;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public String getTIPO_ORDEN() {
        return TIPO_ORDEN;
    }

    public List<BriefSalidaDetalleBean> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<BriefSalidaDetalleBean> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    
    
}
