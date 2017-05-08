/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.modifyUser;

/**
 *
 * @author dideleo
 */
public final class UserBean {
    
    private String user;
    private String active;
    private String rol;

    public UserBean(String user,String rol,String active){
        
        this.user = user;
        this.rol = rol;
        this.active = active;
    }
     
    
    public String getActive() {
       
        return active;
    }


    public String getUser() {
        
        return user;
    }
    
    public String getRol(){
        
        return this.rol;
        
    }
    
    public void setRol(String rol){
        
        this.rol = rol;
        
    }

    public void setUser(String user) {
        
        this.user = user;
    }
    
    public void setActive(String active){
        
        this.active = active;
        
    }
    
}
