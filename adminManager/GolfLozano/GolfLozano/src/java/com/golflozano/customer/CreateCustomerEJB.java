/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.customer;

import com.golflozano.address.AddressBean;
import com.golflozano.contact.ContactCustomerBean;
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
public class CreateCustomerEJB {
    
    public Boolean createCustomer(CustomerBean customer){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try{
            
        if (customer.getPfisica().equals("SI")){
            
            cs = conn.prepareCall(CrearCustomerQueries.createCustomerFisica);
            cs.setString(1, customer.getNombre());
            cs.setString(2, customer.getPaterno());
            cs.setString(3, customer.getMaterno());
            cs.setString(4, customer.getRfc());
            cs.setString(5, customer.getDesc());
            cs.registerOutParameter(6, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(6);
            
            return result;
            
        }else{
            
            cs = conn.prepareCall(CrearCustomerQueries.createCustomerMoral);
            
            cs.setString(1, customer.getNombre());
            cs.setString(2, customer.getRfc());
            cs.setString(3, customer.getDesc());
            cs.registerOutParameter(4, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
        }
        
        }catch(Exception e){
            Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", e);
        }finally{
            
            try {
                if ( cs != null)
                    cs.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Execution should never reach this point
        return null;
    }
    
    
    public Boolean updateCustomer(CustomerBean customer){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try{
            
        if (customer.getPfisica().equals("SI")){
            
            
            //call update_customer_fisica(21,'NOMBRE','PATERNO','MATERNO','RFC','DESC','NO',@result);
            cs = conn.prepareCall(CrearCustomerQueries.updateCustomerFisica);
            cs.setInt(1, customer.getId());
            cs.setString(2, customer.getNombre());
            cs.setString(3, customer.getPaterno());
            cs.setString(4, customer.getMaterno());
            cs.setString(5, customer.getRfc());
            cs.setString(6, customer.getDesc());
            cs.setString(7, customer.getActivo());
            cs.registerOutParameter(8, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(8);
            
            return result;
            
        }else{
            
            cs = conn.prepareCall(CrearCustomerQueries.updateCustomerMoral);
            
            //call update_customer_moral(22,'NOMBRE','RFC','DESC','NO',@result);
            cs.setInt(1, customer.getId());
            cs.setString(2, customer.getNombre());
            cs.setString(3, customer.getRfc());
            cs.setString(4, customer.getDesc());
            cs.setString(5, customer.getActivo());
            cs.registerOutParameter(6, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(6);
            
            return result;
        }
        
        }catch(Exception e){
            Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", e);
        }finally{
            
            try {
                if ( cs != null)
                    cs.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            }
            
        }
        
        //Execution should never reach this point
        return null;
    }
    
    public List<CustomerBean> loadCustomers(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearCustomerQueries.loadCustomers);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<CustomerBean> temp_list = new ArrayList<CustomerBean>();
            
            while (rs.next()){
                temp_list.add(new CustomerBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public List<ContactCustomerBean> loadContacs(CustomerBean customer){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearCustomerQueries.loadContacts);
            ps.setFetchSize(100);
            ps.setInt(1, customer.getId());
            rs = ps.executeQuery();
            
            List<ContactCustomerBean> temp_list = new ArrayList<ContactCustomerBean>();
            
            while (rs.next()){
                temp_list.add(new ContactCustomerBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public List<AddressBean> loadAddresses(CustomerBean customer){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(CrearCustomerQueries.loadAddress);
            ps.setFetchSize(100);
            ps.setInt(1, customer.getId());
            rs = ps.executeQuery();
            
            List<AddressBean> temp_list = new ArrayList<AddressBean>();
            
            while (rs.next()){
                temp_list.add(new AddressBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        //Should never be reached
        return null;
    }
}
