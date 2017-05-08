
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
public class DivisionesEJB {
    
    public List<Division> loadDivisiones(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(DivisionesQueries.loadDivisiones_Query);
            rs = ps.executeQuery();
            
            List<Division> temp_list = new ArrayList<Division>();
            temp_list.add(new Division(new Integer(-1),"Agregar Nuevo...","Agregar Nuevo..."));
            
            while ( rs.next() ){
                temp_list.add( new Division(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golfLozanoError", ex);
            
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
                Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
    }
    
    
    public Boolean crearDivision(Division division){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(DivisionesQueries.createDivision_Query);
            cs.setString(1,division.getNombre());
            cs.setString(2, division.getActivo());
            cs.registerOutParameter(3,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
    
    
    public Boolean updateDivision(Division division){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            
            cs = conn.prepareCall(DivisionesQueries.updateDivision_Query);
            cs.setInt(1,division.getId());
            cs.setString(2, division.getNombre());
            cs.setString(3, division.getActivo());
            cs.registerOutParameter(4,Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(4);
            
            return result;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
            
        }finally{
            try {
                
                if (cs != null)
                    cs.close();
                
                if (conn != null)
                    conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DivisionesEJB.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
    }
}
