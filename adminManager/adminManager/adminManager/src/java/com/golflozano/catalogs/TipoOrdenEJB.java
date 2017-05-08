
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
public class TipoOrdenEJB {
    
    public List<TipoOrden> loadTipoOrdenes(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(TipoOrdenQueries.loadTipoOrdenes_Query);
            rs = ps.executeQuery();
            
            List<TipoOrden> temp_list = new ArrayList<TipoOrden>();
            temp_list.add(new TipoOrden(new Integer(-1),"Agregar Nuevo...","Agregar Nuevo..."));
            
            while ( rs.next() ){
                temp_list.add( new TipoOrden(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            
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
                Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
    }
    
    
    public Boolean crearTipoOrden(TipoOrden TipoOrden){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(TipoOrdenQueries.createTipoOrden_Query);
            cs.setString(1,TipoOrden.getNombre());
            cs.setString(2,TipoOrden.getActivo());
            cs.registerOutParameter(3,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
    
    
    public Boolean updateTipoOrden(TipoOrden TipoOrden){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(TipoOrdenQueries.updateTipoOrden_Query);
            cs.setInt(1,TipoOrden.getId());
            cs.setString(2, TipoOrden.getNombre());
            cs.setString(3, TipoOrden.getActivo());
            cs.registerOutParameter(4,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoOrdenEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
}
