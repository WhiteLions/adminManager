/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class DivisasQueries {
    
    public static final String loadDivisas_Query = "SELECT id_divisa,nombre,activo FROM divisas ORDER BY nombre";
    public static final String createDivisa_Query = "CALL crear_divisa(?,?,?)";
    public static final String updateDivisa_Query = "CALL update_divisa(?,?,?,?)";
    
}
