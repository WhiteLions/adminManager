/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

/**
 *
 * @author dideleo
 */
public final class CreateSalidaQueries {
    
    
    public static final String loadTiposOrden = "SELECT id_tipo_orden,nombre FROM tipos_orden WHERE activo = 'SI' ORDER BY nombre ASC";
    
    public static final String loadClientes = "SELECT id_cliente,nombre,paterno FROM clientes ORDER BY nombre,paterno ASC";
    
    public static final String loadProductos = "SELECT id_producto,nombre,unidad_almacenamiento FROM productos ORDER BY nombre ASC";
    
    public static final String loadUnidades = "SELECT ub.id_unidad,ub.nombre FROM conversiones c INNER JOIN unidades_base ub  ON c.from_unit = ub.id_unidad WHERE to_unit = ? ORDER BY ub.nombre ASC";

    //UNIDAD DE ALMACENAMIENTO MISSING ON PRODUCTO TO LOAD UNIDADES!!!
}
