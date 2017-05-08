/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

import com.golflozano.contact.AutoCustomerBean;
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
public class CustomerAddressEJB {
    
    public List<AutoCustomerBean> loadCustomers(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
       try {
           
            ps = conn.prepareStatement(CustomerAddressQueries.loadCustomers);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<AutoCustomerBean> customers = new ArrayList<AutoCustomerBean>();
            
            while(rs.next()){
                customers.add(new AutoCustomerBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            
            return customers;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
       }
        
       //Should never be reached
        return null;
    }
    
    public List<PaisBean> loadPaisList(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
       try {
           
            ps = conn.prepareStatement(CustomerAddressQueries.loadPais);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<PaisBean> customers = new ArrayList<PaisBean>();
            
            while(rs.next()){
                customers.add(new PaisBean(rs.getInt(1),rs.getString(2)));
            }
            
            return customers;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
       }
        
       //Should never be reached
        return null;
    }
    
    public List<EstadoBean> loadEstadoList(int paisId){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
       try {
           
            ps = conn.prepareStatement(CustomerAddressQueries.loadEstado);
            ps.setFetchSize(100);
            ps.setInt(1, paisId);
            rs = ps.executeQuery();
            
            List<EstadoBean> customers = new ArrayList<EstadoBean>();
            
            while(rs.next()){
                customers.add(new EstadoBean(rs.getInt(1),rs.getString(2)));
            }
            
            return customers;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
       }
        
       //Should never be reached
        return null;
    }
    
    
    public List<CiudadBean> loadCiudadList(int estadoId){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
       try {
           
            ps = conn.prepareStatement(CustomerAddressQueries.loadCiudad);
            ps.setFetchSize(100);
            ps.setInt(1, estadoId);
            rs = ps.executeQuery();
            
            List<CiudadBean> customers = new ArrayList<CiudadBean>();
            
            while(rs.next()){
                customers.add(new CiudadBean(rs.getInt(1),rs.getString(2)));
            }
            
            return customers;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
       }
        
       //Should never be reached
        return null;
    }
    
    
    //CALL crear_direccion_cliente(1,'DIR','CP','COL','ETI',null,null,null,@result);
    
    public Boolean crearDireccionCliente(AddressBean address, int pais, int estado, int ciudad, int cliente){
        
            Connection conn = DBUtil.getConnection();
            CallableStatement cs = null;
            
        
        try {
            
            cs = conn.prepareCall(CustomerAddressQueries.createAddressCustomer);
            cs.setFetchSize(10);
            cs.setInt(1, cliente);
            cs.setString(2, address.getDireccion());
            cs.setString(3, address.getCp());
            cs.setString(4,address.getColonia());
            cs.setString(5, address.getEtiqueta());
            cs.setInt(6, pais);
            cs.setInt(7, estado);
            cs.setInt(8,ciudad);
            cs.setString(9,address.getFiscal());
            cs.registerOutParameter(10, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(10);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if ( conn != null)
                    conn.close();
                if (cs != null)
                    cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }       
        //Should never be reached.
        return null;
    }
    
    
    //CALL update_direccion_cliente(id_dir, 'UPDATEDIR', 'UPCP', 'UPCOLOMIA', 'UPETIQUETA', 1 , 2 , 3 ,'FISCAL', 'ACIVO', @result);
     public Boolean updateDireccionCliente(AddressBean address, int pais, int estado, int ciudad){
        
            Connection conn = DBUtil.getConnection();
            CallableStatement cs = null;
            
            //Values are coming as 0 inside address
            
            if ( pais == 0 ){
                
                pais = address.getPais();
            }
            if ( estado == 0){
              
                estado = address.getEstado();
            }
            if ( ciudad == 0 ){
                
                ciudad = address.getMunicipio();
            }
                   
        
        try {
            
            cs = conn.prepareCall(CustomerAddressQueries.updateAddressCustomer);
            cs.setFetchSize(10);
            cs.setInt(1, address.getId());
            cs.setString(2, address.getDireccion());
            cs.setString(3, address.getCp());
            cs.setString(4,address.getColonia());
            cs.setString(5, address.getEtiqueta());
            cs.setInt(6, pais);
            cs.setInt(7, estado);
            cs.setInt(8,ciudad);
            cs.setString(9,address.getFiscal());
            cs.setString(10, address.getStatus());
            cs.registerOutParameter(11, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(11);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
        }finally{
            try {
                if ( conn != null)
                    conn.close();
                if (cs != null)
                    cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerAddressEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }       
        //Should never be reached.
        return null;
    }
}
