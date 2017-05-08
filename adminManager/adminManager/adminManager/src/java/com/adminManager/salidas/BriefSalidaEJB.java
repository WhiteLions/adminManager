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
public class BriefSalidaEJB {
    
    
    public List<BriefSalidaBean> loadSalidas(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BriefSalidaBean> tempList = null;
        
        try {
            
            ps =  conn.prepareStatement(BriefSalidaQueries.loadSalidas);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<BriefSalidaBean>();
            
            while ( rs.next() ){
                
                tempList.add(new BriefSalidaBean(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
  
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(BriefSalidaEJB.class.getName()).log(Level.SEVERE, "loadSalidasOverviewError", ex);   
                          
        }finally{
            
             try {
                    
                    if ( rs != null )
                        rs.close();
                    
                    if ( ps != null )
                        rs.close();
                    
                    if ( conn != null )
                        conn.close();
                    
                } catch (SQLException ex1) {
                    Logger.getLogger(BriefSalidaEJB.class.getName()).log(Level.SEVERE, "loadSalidasOverviewErrorRsrcFree", ex1);
                }
            
        }
        
        //Should never be reached
        return null;
        
    }
    
    public List<BriefSalidaDetalleBean> loadSalidasDetalle(BriefSalidaBean salida){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BriefSalidaDetalleBean> tempList = null;
        
        try {
            
            ps =  conn.prepareStatement(BriefSalidaQueries.loadSalidasDetalle);
            ps.setInt(1,salida.getID_SALIDA());
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            tempList = new ArrayList<BriefSalidaDetalleBean>();
            
            while ( rs.next() ){
                
                tempList.add(new BriefSalidaDetalleBean(rs.getString(1),rs.getString(2),rs.getDouble(3)));
  
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(BriefSalidaEJB.class.getName()).log(Level.SEVERE, "loadSalidasDetalleError", ex);
            
        }finally{
            
            try {
                    
                    if ( rs != null )
                        rs.close();
                    
                    if ( ps != null )
                        rs.close();
                    
                    if ( conn != null )
                        conn.close();
                    
                } catch (SQLException ex1) {
                    Logger.getLogger(BriefSalidaEJB.class.getName()).log(Level.SEVERE, "loadSalidasDetalleErrorRsrcFree", ex1);
                }
            
        }
        
        //Should never be reached
        return null;
        
    }
    
}
