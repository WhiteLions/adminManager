/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.customer;

/**
 *
 * @author dideleo
 */
public final class CrearCustomerQueries {
    
    public static final String createCustomerFisica = "CALL create_customer_fisica(?,?,?,?,?,?)";
    
    public static final String createCustomerMoral = "CALL create_customer_moral(?,?,?,?)";
    
    public static final String loadCustomers = "SELECT id_cliente, nombre, paterno, materno, rfc, persona_fisica, descripcion, activo FROM clientes ORDER BY nombre ASC";
    
    public static final String updateCustomerFisica = "CALL update_customer_fisica(?,?,?,?,?,?,?,?)";
    
    public static final String updateCustomerMoral = "CALL update_customer_moral(?,?,?,?,?,?)";
    
    public static final String loadContacts = "SELECT id_contacto,id_cliente,telefono,email,celular,fax,etiqueta,nombre,paterno,materno,activo FROM contacto_cliente WHERE id_cliente = ?";
    
    public static final String loadAddress = "SELECT id_direccion,fiscal,direccion,cp,colonia,etiqueta,activo,p.nombre,e.nombre,c.nombre,dc.id_pais,dc.id_estado,dc.id_ciudad FROM direccion_cliente dc LEFT JOIN pais p ON dc.id_pais = p.id_pais LEFT JOIN estado e ON dc.id_estado = e.id_estado LEFT JOIN ciudad c ON dc.id_ciudad = c.id_ciudad WHERE dc.id_cliente = ?";
    
}
