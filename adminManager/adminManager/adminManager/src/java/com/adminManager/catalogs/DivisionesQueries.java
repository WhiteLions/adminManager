/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class DivisionesQueries {
    
    public static final String loadDivisiones_Query = "SELECT id_division,nombre,activo FROM divisiones ORDER BY nombre";
    public static final String createDivision_Query = "CALL crear_division(?,?,?)";
    public static final String updateDivision_Query = "CALL update_division(?,?,?,?)";
    
}
