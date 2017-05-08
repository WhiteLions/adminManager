/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

/**
 *
 * @author dideleo
 */
public final class CustomerAddressQueries {
    
    public static final String loadCustomers = "SELECT id_cliente,nombre,paterno FROM clientes ORDER BY nombre ASC";
    
    public static final String createAddressCustomer = "CALL crear_direccion_cliente(?,?,?,?,?,?,?,?,?,?)";
    
    public static final String loadPais = "SELECT id_pais,nombre FROM pais ORDER BY nombre ASC";
    
    public static final String loadEstado = "SELECT id_estado,nombre FROM estado WHERE id_pais = ? ORDER BY nombre ASC";
    
    public static final String loadCiudad = "SELECT id_ciudad,nombre FROM ciudad WHERE id_estado = ? ORDER BY nombre ASC";
    
    public static final String updateAddressCustomer = "CALL update_direccion_cliente(?,?,?,?,?,?,?,?,?,?,?)";
  
}
