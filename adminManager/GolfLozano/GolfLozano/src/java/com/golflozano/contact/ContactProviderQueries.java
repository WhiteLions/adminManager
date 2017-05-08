/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

/**
 *
 * @author dideleo
 */
public final class ContactProviderQueries {
    
    public static final String createContactProvider = "CALL create_contact_provider(?,?,?,?,?,?,?,?,?,?)";
    public static final String loadProviders = "SELECT id_proveedor,nombre,paterno FROM proveedores ORDER BY nombre ASC";
    public static final String updateContactProvider = "CALL update_contacto_provider(?,?,?,?,?,?,?,?,?,?,?,?)";
    
    
}
