/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import com.golflozano.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CreateSalidaEJB {
    
    public List<CreateSalidaTipoOrdenBean> loadTiposOrden(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CreateSalidaTipoOrdenBean> tempList;
        
        try {
            
            ps = conn.prepareStatement(CreateSalidaQueries.loadTiposOrden);
            ps.setFetchSize(50);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<CreateSalidaTipoOrdenBean>();
            tempList.add( new CreateSalidaTipoOrdenBean(-1,"SELECCIONA..."));
            
            while ( rs.next() ){
                
                tempList.add(new CreateSalidaTipoOrdenBean(rs.getInt(1),rs.getString(2)));
                
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "LoadTiposCreateSalidaError", ex);
            
        }finally{
            
            try {
                
                
                if ( rs != null )
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "CreateSalidaLoadTiposFreeError", ex);
                
            }
            
        }
        
        //Should never be reached
        return null;
        
    }
    
     public List<CreateSalidaClienteBean> loadClientes(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CreateSalidaClienteBean> tempList;
        
        try {
            
            ps = conn.prepareStatement(CreateSalidaQueries.loadClientes);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<CreateSalidaClienteBean>();
            
            tempList.add(new CreateSalidaClienteBean(-1,"SELECCIONA...",""));
            
            while ( rs.next() ){
                
                tempList.add(new CreateSalidaClienteBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
                
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "LoadClientesCreateSalidaError", ex);
            
        }finally{
            
            try {
                
                
                if ( rs != null )
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "CreateSalidaLoadClientesFreeError", ex);
                
            }
            
        }
        
        //Should never be reached
        return null;
        
    }
     
      public List<CreateSalidaProductoBean> loadProductos(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CreateSalidaProductoBean> tempList;
        
        try {
            
            ps = conn.prepareStatement(CreateSalidaQueries.loadProductos);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<CreateSalidaProductoBean>();
            //Add first option to combo box
            tempList.add(new CreateSalidaProductoBean(-1,"SELECCIONA...",-1));
            
            while ( rs.next() ){
                
                tempList.add(new CreateSalidaProductoBean(rs.getInt(1),rs.getString(2),rs.getInt(3)));
                
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "LoadProductosCreateSalidaError", ex);
            
        }finally{
            
            try {
                
                
                if ( rs != null )
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "CreateSalidaLoadProductosFreeError", ex);
                
            }
            
        }
        
        //Should never be reached
        return null;
        
    }
      //IMPLEMENT THIS
      public List<CreateSalidaUnidadBean> loadUnidades(int productId){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CreateSalidaUnidadBean> tempList;
        
          System.out.println(CreateSalidaQueries.loadUnidades);
          System.out.println("Unidad: " + productId);
        
        try {
            
            ps = conn.prepareStatement(CreateSalidaQueries.loadUnidades);
            ps.setFetchSize(50);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<CreateSalidaUnidadBean>();
            
            while ( rs.next() ){
                
                tempList.add(new CreateSalidaUnidadBean(rs.getInt(1),rs.getString(2)));
                
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "LoadUnidadCreateSalidaError", ex);
            
        }finally{
            
            try {
                
                
                if ( rs != null )
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(CreateSalidaEJB.class.getName()).log(Level.SEVERE, "CreateSalidaLoadUnidadFreeError", ex);
                
            }
            
        }
        
        //Should never be reached
        return null;
        
    }
    
}
