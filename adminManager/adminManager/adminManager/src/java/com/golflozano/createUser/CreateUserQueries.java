/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.createUser;

/**
 *
 * @author dideleo
 */
public final class CreateUserQueries {
    
    public static final String loadMenu_Query = "SELECT nombre FROM roles";
    public static final String checkUser_Query = "SELECT 1 FROM usuarios WHERE usuario = ?";
    public static final String createUser_Query = "CALL crear_usuario(?,?,?,?)";
    
}
