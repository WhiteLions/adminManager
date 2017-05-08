/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

/**
 *
 * @author dideleo
 */
public final class EquivalenciasQueries {
    
    public final static String loadUnidades = "SELECT id_unidad,nombre FROM unidades_base WHERE activo = 'SI' ORDER BY nombre ASC";
    
    public final static String loadEquivalencias = "SELECT c.from_unit AS FROMID,ub.nombre AS FROMUNIT, c.to_unit AS TOID,ub1.nombre AS TOUNIT, c.qty AS QTY "
                                                        + "FROM conversiones c "
                                                        + "INNER JOIN unidades_base ub ON c.from_unit = ub.id_unidad INNER "
                                                        + "JOIN unidades_base ub1 ON c.to_unit = ub1.id_unidad ORDER BY TOUNIT ASC";
    
    public final static String createEquivalencia = "CALL crear_equivalencia(?,?,?,?)";
    
    public final static String updateEquivalencia = "CALL update_equivalencia(?,?,?,?)";
    
}
