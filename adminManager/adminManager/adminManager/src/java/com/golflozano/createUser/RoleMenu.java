/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.createUser;

import com.golflozano.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dideleo
 */

public class RoleMenu {
    
    private List<String> roles;
    private String selectedRole;
    
    
    public void loadRoles(){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            this.roles = new ArrayList<String>();
            
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(CreateUserQueries.loadMenu_Query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                this.roles.add(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleMenu.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try{
            ps.close();
            rs.close();
            conn.close();
            }catch (Exception ex){
               Logger.getLogger(RoleMenu.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
    }

    public List<String> getRoles() {
        return roles;
    }
    
    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    
    
}
