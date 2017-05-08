/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

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
public class ContactProviderEJB {
    
    
    public Boolean createContactProvider(ContactProviderBean contact,int id_proveedor){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try{
            
            
            cs = conn.prepareCall(ContactProviderQueries.createContactProvider);
            cs.setInt(1,id_proveedor);
            cs.setString(2, contact.getNombre());
            cs.setString(3, contact.getPaterno());
            cs.setString(4, contact.getMaterno());
            cs.setString(5, contact.getTelefono());
            cs.setString(6, contact.getCelular());
            cs.setString(7, contact.getFax());
            cs.setString(8, contact.getEmail());
            cs.setString(9, contact.getEtiqueta());
            cs.registerOutParameter(10, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(10);
            
            return result;
            
       
        
        }catch(Exception e){
            Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", e);
        }finally{
            
            try {
                if ( cs != null)
                    cs.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Should never be reached
        return null;
        
    }
    
    public List<AutoProviderBean> loadProviders(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(ContactProviderQueries.loadProviders);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<AutoProviderBean> temp_list = new ArrayList<AutoProviderBean>();
            
            while (rs.next()){
                temp_list.add(new AutoProviderBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public Boolean updateContactProvider(ContactProviderBean contact){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try{
            
            
            cs = conn.prepareCall(ContactProviderQueries.updateContactProvider);
            cs.setInt(1,contact.getId());
            cs.setInt(2, contact.getProviderId());
            cs.setString(3, contact.getNombre());
            cs.setString(4, contact.getPaterno());
            cs.setString(5, contact.getMaterno());
            cs.setString(6, contact.getActivo());
            cs.setString(7, contact.getTelefono());
            cs.setString(8, contact.getCelular());
            cs.setString(9, contact.getFax());
            cs.setString(10, contact.getEmail());
            cs.setString(11, contact.getEtiqueta());
            cs.registerOutParameter(12, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(12);
            
            return result;
            
       
        
        }catch(Exception e){
            
            Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", e);
            
        }finally{
            
            try {
                if ( cs != null)
                    cs.close();
                if ( conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(ContactProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Should never be reached
        return null;
    }
    
}
