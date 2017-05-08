/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.passwordUser;

import com.golflozano.db.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author dideleo
 */
@Stateless
public class PasswordUserEJB {
    
    public List<String> loadUsers(){
        
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
        
        try {       
            
            ps = conn.prepareStatement(PasswordUserQueries.loadUsers_Query);
            rs = ps.executeQuery();
            
            List<String> temp_list = new ArrayList<String>();
            
            while ( rs.next() ){
                
                temp_list.add(rs.getString(1));
                
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasswordUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                
                if ( ps != null )
                    ps.close();
                
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PasswordUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        //Execution should never reach this point
        return null;
        
        
    }
    
    public Boolean updatePassword(String user, String password){
            Connection conn = DBUtil.getConnection();
            CallableStatement cs = null;
            
        
        try {       
            
            cs = conn.prepareCall(PasswordUserQueries.updatePassword_Query);
            cs.setString(1, user);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasswordUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                
                if ( cs != null )
                    cs.close();
                
                if ( conn != null )
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(PasswordUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        //Execution should never reach this point
        return null;
    }
    
    public Boolean checkPasswordsMatch(String pass, String confPass){
        
        return pass.equals(confPass);
        
    }
    
}
