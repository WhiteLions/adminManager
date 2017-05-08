/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.provider;

/**
 *
 * @author dideleo
 */
public final class CrearProviderQueries {
    
    public static final String createProviderFisica = "CALL create_provider_fisica(?,?,?,?,?,?,?,?,?)";
    
    public static final String createProviderMoral = "CALL create_provider_moral(?,?,?,?,?,?,?)";
    
    public static final String loadProviders = "SELECT id_proveedor,nombre,paterno,materno,rfc,persona_fisica,formas_pago,p.desc,web,entrega_domicilio,activo FROM proveedores p ORDER BY nombre ASC";
    
    public static final String updateProviderFisica = "CALL update_provider_fisica(?,?,?,?,?,?,?,?,?,?,?)";
    
    public static final String updateProviderMoral = "CALL update_provider_moral(?,?,?,?,?,?,?,?,?)";
    
    public static final String loadContacts = "SELECT id_contacto,id_proveedor,telefono,email,celular,fax,etiqueta,nombre,paterno,materno,activo FROM contacto_proveedor WHERE id_proveedor = ?";
    
    public static final String loadAddress = "SELECT id_direccion,fiscal,direccion,cp,colonia,etiqueta,activo,p.nombre,e.nombre,c.nombre,dc.id_pais,dc.id_estado,dc.id_ciudad FROM direccion_proveedor dc LEFT JOIN pais p ON dc.id_pais = p.id_pais LEFT JOIN estado e ON dc.id_estado = e.id_estado LEFT JOIN ciudad c ON dc.id_ciudad = c.id_ciudad WHERE dc.id_proveedor = ?";
    
    public static final String loadProvProducts = "SELECT id_producto,nombre,no_parte_proveedor,ubicacion FROM productos WHERE id_producto IN (SELECT id_producto FROM proveedores_productos WHERE id_proveedor = ? )";
}
