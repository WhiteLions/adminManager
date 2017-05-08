/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import com.golflozano.catalogs.Divisa;
import com.golflozano.catalogs.UnidadBase;
import com.golflozano.contact.AutoProviderBean;
import com.golflozano.db.DBUtil;
import com.golflozano.products.ProductoBean;
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
public class NuevaEntradaEJB {
    
    
    public Boolean crearFactura(EntradaBean factura,String user){
    //CALL crear_factura('FOLIO',16,1,100.0,99.99,00.01,0.0,NOW(),'PEDIMENTO','DIEGOUSER',@result);    
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try {
            
            cs = conn.prepareCall(EntradasQueries.crearFactura);
            cs.setString(1, factura.getFolio());
            cs.setInt(2, factura.getProvider());
            cs.setInt(3, factura.getDivisa());
            cs.setDouble(4, factura.getTotal());
            cs.setDouble(5, factura.getSubtotal());
            cs.setDouble(6, factura.getIva());
            cs.setDouble(7, factura.getTipocambio());
            cs.setDate(8, new java.sql.Date(factura.getFecha().getTime()));

            String temp_pedimento = factura.getPedimento();
            
            if ( temp_pedimento == null ){
                cs.setString(9, "");
            }else{
                cs.setString(9, factura.getPedimento());
            }
            
            cs.setString(10, user);
            cs.setString(11, factura.getProductListString());
            cs.setInt(12, factura.getProductosList().size());
            cs.registerOutParameter(13, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(13); 
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorFacturaCrear", ex);
        }finally{
            try {
                if ( cs != null )
                    cs.close();
                
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorFacturaCrearRrcFree", ex);
            }
        }
        
        // Should never be reached
        return null;
        
    }
    
    
    public List<AutoProviderBean> loadProviders(){
        
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadProviders);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<AutoProviderBean> temp_list = new ArrayList<AutoProviderBean>();
            
            temp_list.add(new AutoProviderBean(-1,"SELECCIONA...",""));
            
            while ( rs.next() ){
                
                temp_list.add(new AutoProviderBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
                
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdProv", ex);
        }finally{
            try {
                
                if ( rs != null )
                    rs.close();
                    
                if ( ps != null )
                    ps.close();
                
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdProv", ex);
            }
            
        }
        
        // Shouldnt be reached
        return null;
        
    }
    
     public List<EntradaBean> loadFacturas(){
        
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadFacturas);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<EntradaBean> temp_list = new ArrayList<EntradaBean>();
            
            while ( rs.next() ){
                //int id, int provider, String provider_str, Date audit_fecha, String username, 
                //Date fecha, String folio, String pedimento, double tipocambio, double subtotal, 
                //double iva, double total, int divisa, List<EntradaEntry> productosList
                temp_list.add(new EntradaBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11),rs.getDouble(12),rs.getInt(13),rs.getString(14),new ArrayList<EntradaEntry>()));
                
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdFacturas", ex);
        }finally{
            try {
                
                if ( rs != null )
                    rs.close();
                    
                if ( ps != null )
                    ps.close();
                
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdFacturas", ex);
            }
            
        }
        
        // Shouldnt be reached
        return null;
        
    }
    
    public List<ProductoBean> loadProducts(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadProductos_Query);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<ProductoBean> temp_list = new ArrayList<ProductoBean>();
            
            ProductoBean firstOpt = new ProductoBean();
            firstOpt.setNombre("Selecciona...");
            temp_list.add(firstOpt);
            
            while (rs.next()){
                temp_list.add(new ProductoBean(rs.getInt(1),-1,rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),"",rs.getString(16),rs.getString(17)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProd", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProdRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public List<UnidadBase> loadUnidadesEntrada(int unidad_base){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadUnidadesEntrada);
            ps.setFetchSize(100);
            ps.setInt(1, unidad_base);
            rs = ps.executeQuery();
            
            List<UnidadBase> temp_list = new ArrayList<UnidadBase>();
            
            while (rs.next()){
                temp_list.add(new UnidadBase(rs.getInt(1),rs.getString(2),"SI"));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProd", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProdRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    
     public List<Divisa> loadDivisas(){
        
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadDivisas);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<Divisa> temp_list = new ArrayList<Divisa>();
                     
            
            while ( rs.next() ){
                
                temp_list.add(new Divisa(rs.getInt(1),rs.getString(2),"SI"));
                
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdDiv", ex);
        }finally{
            try {
                
                if ( rs != null )
                    rs.close();
                    
                if ( ps != null )
                    ps.close();
                
                if ( conn != null )
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "GolfLozanoEntradaLdDiv", ex);
            }
            
        }
        
        // Shouldnt be reached
        return null;
        
    }
     
         public List<FacturaDetalleProdBean> loadProductsFactura(EntradaBean factura){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(EntradasQueries.loadProdFacturas);
            ps.setFetchSize(100);
            ps.setInt(1, factura.getId());
            rs = ps.executeQuery();
            
            List<FacturaDetalleProdBean> temp_list = new ArrayList<FacturaDetalleProdBean>();
            
            while (rs.next()){
               temp_list.add(new FacturaDetalleProdBean(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getInt(4)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProd", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NuevaEntradaEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProdRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
}
