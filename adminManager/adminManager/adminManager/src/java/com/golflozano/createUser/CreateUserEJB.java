/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.createUser;


import com.golflozano.db.DBUtil;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author dideleo
 */
@Stateless
public class CreateUserEJB {
    
    public Boolean UsernameExists(String username){
        
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        
            try{
                
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(CreateUserQueries.checkUser_Query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (rs.next()){
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
            
            }catch (Exception ex){
                Logger.getLogger(CreateUserEJB.class.getName()).log(Level.SEVERE,"golflozanoError",ex);
            }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
            }
            
            //This should never be reached, returning null to cause application failure.
            return null;
       
    }
    
    
    public Boolean CreateUser(String username,String password,String role){
        
        Connection conn = null;
        CallableStatement cs = null;
        //ResultSet rs = null;
        
        try{
            
            conn = DBUtil.getConnection();
            cs = conn.prepareCall(CreateUserQueries.createUser_Query);
            cs.registerOutParameter(4, Types.BOOLEAN);
            cs.setString(1, username);
            cs.setString(2,password);
            cs.setString(3, role);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
            
        }catch(Exception ex){
            Logger.getLogger(CreateUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                             
                if (cs != null)
                    cs.close();
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(CreateUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
     //This point should never be reached.
           return null;
    }
}
