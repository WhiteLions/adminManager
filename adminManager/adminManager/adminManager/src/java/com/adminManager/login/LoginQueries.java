/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.login;

/**
 *
 * @author dideleo
 */
public final class LoginQueries {
    
    public static final String login_query = "SELECT r.nombre,u.activo FROM usuarios u INNER JOIN usuarios_roles ur ON u.usuario = ur.usuario INNER JOIN roles r ON ur.id_rol = r.id_rol WHERE u.usuario = ? and u.password = MD5(?)";
    
}
