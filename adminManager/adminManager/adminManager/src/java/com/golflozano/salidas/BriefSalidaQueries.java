/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class BriefSalidaQueries {
    
    
    public static final String loadSalidas = "SELECT s.id_salida,s.fecha,tp.nombre,s.folio_orden,CONCAT(CONCAT(c.nombre,' '),c.paterno),s.observaciones FROM salidas s INNER JOIN tipos_orden tp ON s.tipo_orden = tp.id_tipo_orden INNER JOIN clientes c ON c.id_cliente = s.id_cliente ORDER BY fecha DESC";
    
    public static final String loadSalidasDetalle = "SELECT p.nombre,ub.nombre,sd.cantidad FROM salidas_detalle sd INNER JOIN productos p ON sd.id_producto = p.id_producto INNER JOIN unidades_base ub ON ub.id_unidad = sd.id_unidad_venta WHERE sd.id_salida = ?";
    
}
