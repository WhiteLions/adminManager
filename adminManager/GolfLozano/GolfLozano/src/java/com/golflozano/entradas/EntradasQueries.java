/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

/**
 *
 * @author dideleo
 */
public final class EntradasQueries {
    
    public static final String loadProviders = "SELECT id_proveedor,nombre,paterno FROM proveedores ORDER BY nombre ASC";
    
    public static final String loadProductos_Query = "SELECT id_producto,nacional,id_familia,id_division,id_almacen,p.nombre,p.activo,unidad_almacenamiento,oem,no_parte_proveedor,ubicacion,consignacion,desc_esp,desc_ing,observaciones,ub.nombre,foto FROM productos p INNER JOIN unidades_base ub ON ub.id_unidad = p.unidad_almacenamiento ORDER BY p.nombre ASC";
    
    //public static final String loadUnidadesEntrada = "SELECT ub.id_unidad,ub.nombre FROM conversiones c INNER JOIN unidades_base ub  ON c.to_unit = ub.id_unidad WHERE from_unit = ? ORDER BY ub.nombre ASC";
    /*
     * Solo cargar unidades que esten identificadas como listas para convertir por multiplicacion.
     */
    public static final String loadUnidadesEntrada = "SELECT ub.id_unidad,ub.nombre FROM conversiones c INNER JOIN unidades_base ub  ON c.from_unit = ub.id_unidad WHERE to_unit = ? ORDER BY ub.nombre ASC";
    
    public static final String loadDivisas = "SELECT id_divisa,nombre FROM divisas WHERE activo = 'SI' ORDER by nombre ASC";
    
    //public static final String crearFactura = "CALL crear_factura(?,?,?,?,?,?,?,?,?,?,?)";
    public static final String crearFactura = "CALL insert_factura_details(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public static final String loadFacturas = "SELECT f.id_factura,p.id_proveedor,CONCAT(p.nombre,' ',IFNULL(p.paterno,'')),f.audit_fecha,f.audit_user,f.fecha,f.folio,f.pedimento,f.tipo_cambio,f.subtotal,f.iva,f.total,f.id_divisa,d.nombre FROM facturas f INNER JOIN proveedores p ON f.id_proveedor = p.id_proveedor INNER JOIN divisas d ON f.id_divisa = d.id_divisa AND f.tipo_entrada='FACTURA' ORDER BY f.fecha DESC";
    
    public static final String loadProdFacturas = "SELECT p.nombre,ub.nombre,fd.precio_unitario,fd.cantidad FROM facturas_detalle fd INNER JOIN productos p ON p.id_producto = fd.id_producto INNER JOIN unidades_base ub ON ub.id_unidad = fd.id_unidad_compra WHERE fd.id_factura = ? ORDER BY fd.id_cons";
}
