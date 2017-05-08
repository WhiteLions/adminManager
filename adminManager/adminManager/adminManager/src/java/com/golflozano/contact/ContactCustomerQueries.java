/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

/**
 *
 * @author dideleo
 */
public final class ContactCustomerQueries {
    
    public static final String createContactCustomer = "CALL create_contact_customer(?,?,?,?,?,?,?,?,?,?)";
    
    public static final String autoLoadCustomers = "SELECT id_cliente,nombre,paterno FROM clientes";
    
    public static final String updateContactCustomer = "CALL update_contacto_customer(?,?,?,?,?,?,?,?,?,?,?,?)";
    
}
