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
public class EquivalenciasEJB {
    
    public List<UnidadBase> loadUnidadesList(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
        
        ps = conn.prepareStatement(EquivalenciasQueries.loadUnidades);        
        ps.setFetchSize(100);
        rs = ps.executeQuery();
        
        List<UnidadBase> temp_list = new ArrayList<UnidadBase>();
        
        while (rs.next()){
            temp_list.add(new UnidadBase(rs.getInt(1),rs.getString(2),"SI"));
        }
        
        return temp_list;
        
        } catch (SQLException ex) {
            Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
            }
        }
        
        //Should never be reached
        return null;
    }
    
    public List<EquivalenciaBean> loadEquivalencias(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
        
        ps = conn.prepareStatement(EquivalenciasQueries.loadEquivalencias);        
        ps.setFetchSize(100);
        rs = ps.executeQuery();
        
        List<EquivalenciaBean> temp_list = new ArrayList<EquivalenciaBean>();
        
        while (rs.next()){
            temp_list.add(new EquivalenciaBean(new UnidadBase(rs.getInt("FROMID"),rs.getString("FROMUNIT"),""),new UnidadBase(rs.getInt("TOID"),rs.getString("TOUNIT"),""),rs.getDouble("QTY")));
        }
        
        return temp_list;
        
        } catch (SQLException ex) {
            Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if ( ps != null )
                    ps.close();
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
            }
        }
        
        //Should never be reached
        return null;
    }
    
    public Boolean crearEquivalencia(int from, int to, double qty){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try {
            cs = conn.prepareCall(EquivalenciasQueries.createEquivalencia);
            cs.setInt(1, from);
            cs.setInt(2, to);
            cs.setDouble(3, qty);
            cs.registerOutParameter(4, Types.BOOLEAN);
            cs.execute();
            
            boolean result = cs.getBoolean(4);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
        }finally{
            try {
                if (cs != null)
                    cs.close();
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
            }
        }
        
        //Should never be reached
        return null;
        
    }
    
    public Boolean updateEquivalencia(EquivalenciaBean equivalencia){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try {
            cs = conn.prepareCall(EquivalenciasQueries.updateEquivalencia);
            cs.setInt(1, equivalencia.getFrom().getId());
            cs.setInt(2, equivalencia.getTo().getId());
            cs.setDouble(3, equivalencia.getQty());
            cs.registerOutParameter(4, Types.BOOLEAN);
            cs.execute();
            
            boolean result = cs.getBoolean(4);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
        }finally{
            try {
                if (cs != null)
                    cs.close();
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EquivalenciasEJB.class.getName()).log(Level.SEVERE, "GolfLozanoError", ex);
            }
        }
        
        //Should never be reached
        return null;
        
    }
}
