/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

/**
 *
 * @author dideleo
 */
public final class ProviderAddressQueries {
    
    public static final String loadProviders = "SELECT id_proveedor,nombre,paterno FROM proveedores ORDER BY nombre ASC";
    
    public static final String loadPais = "SELECT id_pais,nombre FROM pais ORDER BY nombre ASC";
    
    public static final String loadEstado = "SELECT id_estado,nombre FROM estado WHERE id_pais = ? ORDER BY nombre ASC";
    
    public static final String loadCiudad = "SELECT id_ciudad,nombre FROM ciudad WHERE id_estado = ? ORDER BY nombre ASC";
    
    public static final String createProviderAddress = "CALL crear_direccion_proveedor(?,?,?,?,?,?,?,?,?,?)";
    
     public static final String updateAddressProvider = "CALL update_direccion_proveedor(?,?,?,?,?,?,?,?,?,?,?)";
}
