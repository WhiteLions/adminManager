
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

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
public class AlmacenEJB {
    
    public List<Almacen> loadAlmacenes(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(AlmacenQueries.loadAlmacenes_Query);
            rs = ps.executeQuery();
            
            List<Almacen> temp_list = new ArrayList<Almacen>();
            temp_list.add(new Almacen(new Integer(-1),"Agregar Nuevo...","Agregar Nuevo..."));
            
            while ( rs.next() ){
                temp_list.add( new Almacen(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            
            return null;
            
        }finally{
            try {
                
                if (rs != null)
                    rs.close();
                
                if (ps != null)
                    ps.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
    }
    
    
    public Boolean crearAlmacen(Almacen almacen){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(AlmacenQueries.createAlmacen_Query);
            cs.setString(1,almacen.getNombre());
            cs.setString(2, almacen.getActivo());
            cs.registerOutParameter(3,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
    
    
    public Boolean updateAlmacen(Almacen almacen){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(AlmacenQueries.updateAlmacen_Query);
            cs.setInt(1,almacen.getId());
            cs.setString(2, almacen.getNombre());
            cs.setString(3, almacen.getActivo());
            cs.registerOutParameter(4,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
}
