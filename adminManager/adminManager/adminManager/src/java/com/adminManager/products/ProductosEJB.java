/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.products;

import com.golflozano.catalogs.Almacen;
import com.golflozano.catalogs.Division;
import com.golflozano.catalogs.Familia;
import com.golflozano.catalogs.UnidadBase;
import com.golflozano.contact.AutoProviderBean;
import com.golflozano.db.DBUtil;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dideleo
 */
@Stateless
public class ProductosEJB {
    
    
    //Need to add SAVE Photo
    public Integer createProduct(ProductoBean product){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try {
            cs = conn.prepareCall(ProductosQueries.createProducto);
            //cs.setInt(1,product.getId_proveedor());
            cs.setString(1, product.getNacional());
            cs.setInt(2, product.getId_familia());
            cs.setInt(3,product.getId_division());
            cs.setInt(4,product.getId_almacen());
            cs.setString(5, product.getNombre());
            cs.setInt(6, product.getId_unidadAlmacenamiento());
            cs.setString(7, product.getOem());
            cs.setString(8, product.getNoPteProv());
            cs.setString(9, product.getUbicacion());
            cs.setString(10, product.getConsignacion());
            cs.setString(11, product.getFoto());
            cs.setString(12, product.getDesc_esp());
            cs.setString(13, product.getDesc_ing());
            cs.setString(14, product.getObservaciones());
            cs.registerOutParameter(15, Types.BOOLEAN);
            cs.registerOutParameter(16, Types.INTEGER);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(15);
            Integer id = cs.getInt(16);
            
            return id;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GolfLozanoErrorCreateProducto", ex);
        }finally{
            try {
                if (cs != null)
                    cs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GlofLozanoErrorFreeRsrcProducto", ex);
            }
        }
        
        return null;
    }
    
    public Boolean updateProduct(ProductoBean product){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        try {
            cs = conn.prepareCall(ProductosQueries.updateProducto);
            cs.setInt(1,product.getId_proveedor());
            cs.setInt(2,product.getId_producto());
            cs.setString(3, product.getNacional());
            cs.setInt(4, product.getId_familia());
            cs.setInt(5,product.getId_division());
            cs.setInt(6,product.getId_almacen());
            cs.setString(7, product.getNombre());
            cs.setString(8,product.getActivo());
            cs.setString(9, product.getOem());
            cs.setString(10, product.getNoPteProv());
            cs.setString(11, product.getUbicacion());
            cs.setString(12, product.getConsignacion());
            cs.setString(13, product.getFoto());
            cs.setString(14, product.getDesc_esp());
            cs.setString(15, product.getDesc_ing());
            cs.setString(16, product.getObservaciones());
            cs.registerOutParameter(17, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(17);
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GolfLozanoErrorUpdateProducto", ex);
        }finally{
            try {
                if (cs != null)
                    cs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GlofLozanoErrorFreeRsrcUpdProducto", ex);
            }
        }
        
        return null;
        
    }
    
    public List<AutoProviderBean> loadProviders(int id_producto){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadProviders);
            ps.setFetchSize(100);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            
            List<AutoProviderBean> temp_list = new ArrayList<AutoProviderBean>();
            
            while (rs.next()){
                temp_list.add(new AutoProviderBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProv", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProvRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    public List<AutoProviderBean> loadProductProviders(ProductoBean product){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadProductProviders);
            ps.setFetchSize(100);
            ps.setInt(1, product.getId_producto());
            rs = ps.executeQuery();
            
            List<AutoProviderBean> temp_list = new ArrayList<AutoProviderBean>();
            
            while (rs.next()){
                temp_list.add(new AutoProviderBean(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProv", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProvRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
    
    
    public List<UnidadBase> loadUnidadBase(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadUnidadBaseQuery);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<UnidadBase> temp_list = new ArrayList<UnidadBase>();
            //temp_list.add(new UnidadBase(new Integer(-1),"Agregar Nuevo...","Agregar Nuevo..."));
            
            while ( rs.next() ){
                temp_list.add( new UnidadBase(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLoadUnidades", ex);
            
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
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorProductosLoadUnidades", ex);
            }
        }
        
    }
    
    
    public List<Almacen> loadAlmacenes(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadAlmacenes_Query);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<Almacen> temp_list = new ArrayList<Almacen>();          
            
            while ( rs.next() ){
                temp_list.add( new Almacen(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLoadAlmacenes", ex);
            
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
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLoadAlmacenesFly", ex);
            }
        }
        
    }
    
    public List<Division> loadDivisiones(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadDivisiones_Query);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<Division> temp_list = new ArrayList<Division>();
           
            
            while ( rs.next() ){
                temp_list.add( new Division(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorDivisionesLoad", ex);
            
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
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorDivisionesFlyLd", ex);
            }
        }
        
    }
    
     public List<Familia> loadFamilias(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadFamilias_Query);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<Familia> temp_list = new ArrayList<Familia>();
            
            
            while ( rs.next() ){
                temp_list.add( new Familia(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosEJBLoadFamilias", ex);
            
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
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorProductosLoadFamiliasFly", ex);
            }
        }
        
    }
     
     public Boolean savePictureToDisk(UploadedFile file, Integer id_producto){
         
         if ( file == null ){
             return Boolean.FALSE;
         }
                           
        
        
         InputStream is = null;
         FileOutputStream fos = null;
                 
        try{
            
            int size = (int) file.getSize();
            
            byte[] file_bytes = new byte[size];
            
            is = file.getInputstream();
            
            is.read(file_bytes, 0, size);
            
            Properties props = new Properties();
            props.load(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/ini/properties.ini"));           
            
            String savePath = props.getProperty("photo_save_loc");
            
            String[] temp = file.getFileName().split("\\.");
            String file_ext = temp[temp.length - 1];
            
            File dest_file = new File(savePath + "\\" + id_producto.toString() + "." + file_ext );
            
            fos = new FileOutputStream(dest_file);
            fos.write(file_bytes);
            
            return Boolean.TRUE;
            
        }catch(Exception ex){
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GolfLozanoErrorWritingFile", ex);
            return Boolean.FALSE;
            
        }finally{
            
            try {
                is.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "GolfLozanoErrorWritingFile", ex);
            }
            
        }
     }
     
     public List<ProductoBean> loadProducts(){
        
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            
            ps = conn.prepareStatement(ProductosQueries.loadProductos_Query);
            ps.setFetchSize(100);
            rs = ps.executeQuery();
            
            List<ProductoBean> temp_list = new ArrayList<ProductoBean>();
            
            while (rs.next()){
                temp_list.add(new ProductoBean(rs.getInt(1),-1,rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),"",rs.getString(16),rs.getString(17)));
            }
            
            return temp_list;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golfLozanoErrorProductosLdProd", ex);
        }finally{
            try {
                if ( rs != null)
                    rs.close();
                if ( ps != null)
                    ps.close();
                if ( conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, "golflozanoErrorproductosLdProdRlsRsr", ex);
            }
        }
        //Should never be reached
        return null;
    }
     
    public Boolean ligar_proveedor_producto(int id_producto,int id_proveedor){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        
        try {
            
            cs = conn.prepareCall(ProductosQueries.liga_prov_prod);
            cs.setInt(1, id_producto);
            cs.setInt(2, id_proveedor);
            cs.registerOutParameter(3, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            try {
                            
                if ( cs != null )
                       cs.close();          
                if (conn != null)
                       conn.close();
                
            } catch (SQLException ex1) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        //Should never be reached
        return null;
        
    }
    
     public Boolean borrar_proveedor_producto(int id_producto,int id_proveedor){
        
        Connection conn = DBUtil.getConnection();
        CallableStatement cs = null;
        
        
        try {
            
            cs = conn.prepareCall(ProductosQueries.deleteProvProd);
            cs.setInt(1, id_proveedor);
            cs.setInt(2, id_producto);
            cs.registerOutParameter(3, Types.BOOLEAN);
            
            cs.execute();
            
            Boolean result = cs.getBoolean(3);
            
            return result;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            try {
                            
                if ( cs != null )
                       cs.close();          
                if (conn != null)
                       conn.close();
                
            } catch (SQLException ex1) {
                Logger.getLogger(ProductosEJB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        //Should never be reached
        return null;
        
    }
    
}
