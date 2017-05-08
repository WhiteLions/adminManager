/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author dideleo
 */
public final class DBUtil {
    
    private static DataSource dataSource;
    
    static{
        try{
            
            Properties props = new Properties();
            props.load(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/ini/properties.ini"));
            
            dataSource = (DataSource) new InitialContext().lookup(props.getProperty("default_pool"));
        }catch(Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }
    
    
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            return null;
        }
       
    }
            
}
