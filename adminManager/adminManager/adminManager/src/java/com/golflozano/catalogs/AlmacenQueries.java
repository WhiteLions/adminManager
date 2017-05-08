/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class AlmacenQueries {
    
    public static final String loadAlmacenes_Query = "SELECT id_almacen,nombre,activo FROM almacenes ORDER BY nombre";
    public static final String createAlmacen_Query = "CALL crear_almacen(?,?,?)";
    public static final String updateAlmacen_Query = "CALL update_almacen(?,?,?,?)";
}
