/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.modifyUser;

/**
 *
 * @author dideleo
 */
public final class ModifyUserQueries {
    
    public static final String loadUsers_Query = "SELECT u.usuario,r.nombre,u.activo FROM usuarios u INNER JOIN usuarios_roles ur ON u.usuario = ur.usuario INNER JOIN roles r ON ur.id_rol = r.id_rol";
    public static final String loadRoles_Query = "SELECT nombre FROM roles";
    public static final String updateUser_Query = "CALL update_usuario(?,?,?,?)";
            
}
