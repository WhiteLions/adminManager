/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.provider;

import com.golflozano.address.AddressBean;
import com.golflozano.contact.ContactProviderBean;
import com.golflozano.db.DBUtil;
import com.golflozano.products.ProductoBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;


/**
 *Fix deployment error
 * @author dideleo
 */
@Stateless
public class createProviderEJB {
    
    public Boolean createProvider(ProviderBean provider){
        
            Connection conn = DBUtil.getConnection();
            CallableStatement cs = null;
        
            Boolean result = null;
            
        try {
            
            if (provider.getPfisica().equals("SI")){
                cs = conn.prepareCall(CrearProviderQueries.createProviderFisica);
                
                cs.setString(1, provider.getNombre());
                cs.setString(2, provider.getPaterno());
                cs.setString(3, provider.getMaterno());
                cs.setString(4, provider.getRfc());
                cs.setString(5, provider.getDomicilio());
                cs.setString(6,provider.getWeb());
                cs.setString(7, provider.getDesc());
                cs.setString(8, provider.getFormasPago());
                cs.registerOutParameter(9,Types.BOOLEAN); 
                
                cs.execute();
            
                result = cs.getBoolean(9);
                
            }else{
                cs = conn.prepareCall(CrearProviderQueries.createProviderMoral);
                
                cs.setString(1, provider.getNombre());
                cs.setString(2, provider.getRfc());
                cs.setString(3, provider.getDomicilio());
                cs.setString(4,provider.getWeb());
                cs.setString(5, provider.getDesc());
                cs.setString(6, provider.getFormasPago());
                cs.registerOutParameter(7,Types.BOOLEAN); 
                
                cs.execute();
            
                result = cs.getBoolean(7);
            }
            
            
            
            return result;
                        
        } catch (SQLException ex) {
           
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            
            return Boolean.FALSE;
            
        }finally{
            
            try {
                if (cs != null)
                    cs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Should never reach this point
     
    }
    
    public Boolean updateProvider(ProviderBean customer){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try{
            
        if (customer.getPfisica().equals("SI")){
            
            
            //(12, 'NOMBRE', 'PATERNO', 'MATERNO', 'RFC', 'WEB', 'PAGO', 'DOMICILIO', 'DESC', 'ACTIVO',@result);
            cs = conn.prepareCall(CrearProviderQueries.updateProviderFisica);
            cs.setInt(1, customer.getId());
            cs.setString(2, customer.getNombre());
            cs.setString(3, customer.getPaterno());
            cs.setString(4, customer.getMaterno());
            cs.setString(5, customer.getRfc());
            cs.setString(6, customer.getWeb());
            cs.setString(7, customer.getFormasPago());
            cs.setString(8, customer.getDomicilio());
            cs.setString(9, customer.getDesc());
            cs.setString(10, customer.getActivo());
            cs.registerOutParameter(11, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(11);
            
            return result;
            
        }else{
            
            cs = conn.prepareCall(CrearProviderQueries.updateProviderMoral);
            
            //(11, 'NOMBRE', 'RFC', 'WEB', 'PAGO', 'SI', 'DESC', 'SI',@result)
            cs.setInt(1, customer.getId());
            cs.setString(2, customer.getNombre());
            cs.setString(3, customer.getRfc());
            cs.setString(4, customer.getWeb());
            cs.setString(5, customer.getFormasPago());
            cs.setString(6, customer.getDomicilio());
            cs.setString(7, customer.getDesc());
            cs.setString(8, customer.getActivo());
            cs.registerOutParameter(9, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(9);
            
            return result;
        }
        
        }catch(Exception e){
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", e);
        }finally{
            
            try {
                if ( cs != null)
                    cs.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Execution should never reach this point
        return null;
    }
    
    public List<ProviderBean> loadProviders(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearProviderQueries.loadProviders);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<ProviderBean> temp_list = new ArrayList<ProviderBean>();
            
            while (rs.next()){
                temp_list.add(new ProviderBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public List<ContactProviderBean> loadContacs(ProviderBean provider){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearProviderQueries.loadContacts);
            ps.setFetchSize(100);
            ps.setInt(1, provider.getId());
            rs = ps.executeQuery();
            
            List<ContactProviderBean> temp_list = new ArrayList<ContactProviderBean>();
            
            while (rs.next()){
                temp_list.add(new ContactProviderBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
     public List<AddressBean> loadAddresses(ProviderBean provider){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearProviderQueries.loadAddress);
            ps.setFetchSize(100);
            ps.setInt(1, provider.getId());
            rs = ps.executeQuery();
            
            List<AddressBean> temp_list = new ArrayList<AddressBean>();
            
            while (rs.next()){
                temp_list.add(new AddressBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
     
    public List<ProductoBean> loadProvProds(ProviderBean provider){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearProviderQueries.loadProvProducts);
            ps.setFetchSize(100);
            ps.setInt(1, provider.getId());
            rs = ps.executeQuery();
            
            List<ProductoBean> temp_list = new ArrayList<ProductoBean>();
            
            while (rs.next()){
                temp_list.add(new ProductoBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(createProviderEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
}
