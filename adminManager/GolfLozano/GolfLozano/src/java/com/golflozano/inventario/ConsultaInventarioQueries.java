/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.inventario;

/**
 *
 * @author dideleo
 */
public final class ConsultaInventarioQueries {
    
    public static final String loadProductosQuery = "SELECT p.id_producto,SUM(i.existencia) AS existencia,p.nombre,ub.nombre AS unidad,p.ubicacion FROM inventario i INNER JOIN productos p ON i.id_producto = p.id_producto INNER JOIN unidades_base ub ON p.unidad_almacenamiento = ub.id_unidad GROUP BY i.id_producto ORDER BY p.nombre ASC";
    
}
