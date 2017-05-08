/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.modifyUser;

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
public class ModifyUserEJB {
    
    
    public List<String> loadActive(){
        
        List<String> temp = new ArrayList<String>();
        temp.add("SI");
        temp.add("NO");
        
        return temp;
        
    }
    
    public List<UserBean> LoadUsers(){
        
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            conn = DBUtil.getConnection();
        
        try {
                        
            ps = conn.prepareStatement(ModifyUserQueries.loadUsers_Query);
            rs = ps.executeQuery();
            List<UserBean> temp_list = new ArrayList<UserBean>();
            
            while (rs.next()){
                temp_list.add(new UserBean(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            //Something went wrong send back null and cause a runtime exception.
            return null;
            
        }finally{
            try{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
            }catch (Exception ex){
                Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE,"golflozanoError",ex);
            }
        }
        
    }
    
    public List<String> LoadRoles(){
        
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            conn = DBUtil.getConnection();
        
        try {
                        
            ps = conn.prepareStatement(ModifyUserQueries.loadRoles_Query);
            rs = ps.executeQuery();
            List<String> temp_list = new ArrayList<String>();
            
            while (rs.next()){
                temp_list.add(rs.getString(1));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            //Something went wrong send back null and cause a runtime exception.
            return null;
            
        }finally{
            try{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
            }catch (Exception ex){
                Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE,"golflozanoError",ex);
            }
        }
        
    }
    
    public Boolean updateUser(UserBean user){
        
            Connection conn = DBUtil.getConnection();
            CallableStatement cs = null;
            
        try {    
            
            cs = conn.prepareCall(ModifyUserQueries.updateUser_Query);
            cs.setString(1, user.getUser());
            cs.setString(2, user.getRol());
            cs.setString(3, user.getActive());
            cs.registerOutParameter(4, Types.BOOLEAN);
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            try {
                
                if (conn != null)
                    conn.close();
                
                if (cs != null)
                    cs.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ModifyUserEJB.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        
        //Execution should never reach this point.
        return null;
        
    }
  
}
