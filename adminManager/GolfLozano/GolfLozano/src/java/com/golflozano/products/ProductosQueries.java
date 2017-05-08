/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.products;

/**
 *
 * @author dideleo
 */
public final class ProductosQueries {
    
    public static final String createProducto = "CALL create_producto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public static final String liga_prov_prod = "CALL ligar_producto_proveedor(?,?,?)";
    
    public static final String updateProducto = "CALL update_producto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    //public static final String loadProviders = "SELECT id_proveedor,nombre,paterno FROM proveedores ORDER BY nombre ASC";
    
    public static final String loadProviders = "SELECT p.id_proveedor,p.nombre,p.paterno FROM proveedores p WHERE p.id_proveedor NOT IN (SELECT pp.id_proveedor FROM proveedores_productos pp WHERE pp.id_producto = ?)";
    
    public static final String loadProductProviders = "SELECT p.id_proveedor,p.nombre,p.paterno FROM proveedores_productos pp INNER JOIN proveedores p  WHERE p.id_proveedor = pp.id_proveedor AND pp.id_producto = ?";
    
    //public static final String loadProductProviders = "SELECT p.id_proveedor,p.nombre,p.paterno FROM proveedores p WHERE p.id_proveedor NOT IN (SELECT pp.id_proveedor FROM proveedores_productos pp WHERE pp.id_producto = ?)";
    
    public static final String loadUnidadBaseQuery = "SELECT id_unidad,nombre,activo FROM unidades_base ORDER BY nombre";
    
    public static final String loadAlmacenes_Query = "SELECT id_almacen,nombre,activo FROM almacenes ORDER BY nombre";
       
    public static final String loadDivisiones_Query = "SELECT id_division,nombre,activo FROM divisiones ORDER BY nombre";
    
    public static final String loadFamilias_Query = "SELECT id_familia,nombre,activo FROM familias ORDER BY nombre";
    
    public static final String loadProductos_Query = "SELECT id_producto,nacional,id_familia,id_division,id_almacen,p.nombre,p.activo,unidad_almacenamiento,oem,no_parte_proveedor,ubicacion,consignacion,desc_esp,desc_ing,observaciones,ub.nombre,foto FROM productos p INNER JOIN unidades_base ub ON ub.id_unidad = p.unidad_almacenamiento ORDER BY p.nombre ASC";
    
    public static final String deleteProvProd = "CALL delete_provedor_producto(?,?,?)";
    
    
}
