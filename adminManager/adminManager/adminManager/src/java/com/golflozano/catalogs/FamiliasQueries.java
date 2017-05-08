/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class FamiliasQueries {
    
    public static final String loadFamilias_Query = "SELECT id_familia,nombre,activo FROM familias ORDER BY nombre";
    public static final String createFamilia_Query = "CALL crear_familia(?,?,?)";
    public static final String updateFamilia_Query = "CALL update_familia(?,?,?,?)";
    
}
