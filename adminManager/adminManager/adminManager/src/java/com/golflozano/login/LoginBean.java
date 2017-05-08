/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.login;

import com.golflozano.db.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    
    private String username;
    private String password;
    private Boolean authenticated = Boolean.FALSE;
    private String role;
    private String activo;
    

    public String getActivo(){
        return this.activo;
    }
    
    public Boolean isAuthenticated() {
        return authenticated;
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
         this.password = password.trim();
    }

    public String getRole() {
        return role;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username.trim();
        username = username.toUpperCase();
        this.username = username;
    }
    
    public String doLogin(){
        
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(LoginQueries.login_query);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                
                this.role = rs.getString(1);
                this.activo = rs.getString(2);
                
                if(this.activo.equals("SI")){
                    
                    this.authenticated = Boolean.TRUE;
                    return "home.xhtml?faces-redirect=true";
                    
                }else{
                    
                    this.authenticated = Boolean.FALSE;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario inactivo.","Contacta a un administrador."));
                    return "login.xhtml";
                    
                }
                
            }else{
                
                this.username = null;
                this.password = null;
                this.authenticated = Boolean.FALSE;
                this.role = null;
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo de login","usuario/password invalido(s)"));
                return "login.xhtml";
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE,"golflozanoError",ex);
        }finally{
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, "golflozanoError", ex);
            }
        }
        
        
        
        //This should never be reached.
        return "login.xhtml";
        
    }
    
    public String doLogout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.username = null;
        this.password = null;
        this.authenticated = Boolean.FALSE;
        
        return "login.xhtml?faces-redirect=true";
    }
    
    
   
}
