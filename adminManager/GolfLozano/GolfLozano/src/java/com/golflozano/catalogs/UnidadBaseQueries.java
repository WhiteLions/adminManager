/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class UnidadBaseQueries {
    
    public static final String loadUnidadBase_Query = "SELECT id_unidad,nombre,activo FROM unidades_base ORDER BY nombre";
    public static final String createUnidadBase_Query = "CALL crear_unidadBase(?,?,?)";
    public static final String updateUnidadBase_Query = "CALL update_unidadBase(?,?,?,?)";
    
}
