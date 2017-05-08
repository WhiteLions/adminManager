/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.passwordUser;

/**
 *
 * @author dideleo
 */
public final class PasswordUserQueries {
    
    public static final String loadUsers_Query = "SELECT usuario FROM usuarios order by usuario asc";
    public static final String updatePassword_Query = "CALL update_password(?,?,?)";
    
}
