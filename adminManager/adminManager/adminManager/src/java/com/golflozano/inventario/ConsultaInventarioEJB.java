/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.inventario;

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
public class ConsultaInventarioEJB {
    
    public List<InventarioProductoBean> loadProductos(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<InventarioProductoBean> tempList = null;
        
        
        try {
            
            ps = conn.prepareStatement(ConsultaInventarioQueries.loadProductosQuery);
            ps.setFetchSize(100);
            
            rs = ps.executeQuery();
            
            tempList = new ArrayList<InventarioProductoBean>();
            
            while ( rs.next() ){
                
                tempList.add( new InventarioProductoBean(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5)) );
                
            }
            
            return tempList;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ConsultaInventarioEJB.class.getName()).log(Level.SEVERE, "ConsultaInventarioEJBLoadProductsError", ex);
            
        }finally{
            
            try{
            
                if ( rs != null ){
                    rs.close();
                }
                
                if( ps != null ){
                    ps.close();
                }
                if( conn != null ){
                    conn.close();
                }
                
            }catch( SQLException ex ){
                
                Logger.getLogger(ConsultaInventarioEJB.class.getName()).log(Level.SEVERE, "ConsultaInventarioEJBLoadProductsErrorFinally", ex);
                
            }
        }
        
        //Should never be reached.
            return null;
        
        
    }
    
}
