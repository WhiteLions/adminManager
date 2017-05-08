/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class TipoOrdenQueries {
    
    public static final String loadTipoOrdenes_Query = "SELECT id_tipo_orden,nombre,activo FROM tipos_orden ORDER BY nombre";
    public static final String createTipoOrden_Query = "CALL crear_tipo_orden(?,?,?)";
    public static final String updateTipoOrden_Query = "CALL update_tipo_orden(?,?,?,?)";
    
}
