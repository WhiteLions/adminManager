/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.passwordUser;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@RequestScoped
public class PasswordUserView {
    
    private String password;
    private String confirm_password;
    private String user;
    private List<String> userList;
    private UIComponent passtxt;
    
    @EJB
    private PasswordUserEJB passwordUserService;
    
    @PostConstruct
    public void initView(){
        
        this.userList = this.passwordUserService.loadUsers();
        
    }
    
    public void updateUserPassword(){
        
        if ( this.passwordUserService.checkPasswordsMatch(this.password, this.confirm_password) ){
            //Passwords do match, make user update.
            
            if ( this.passwordUserService.updatePassword(this.user, this.password) ){
                // Updated user successfully
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario : " + this.user + " modificado con exito.",""));
                FacesContext.getCurrentInstance().addMessage(this.passtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Password cambiado con exito.",""));
                
                this.password = "";
                this.confirm_password = "";
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario : " + this.user + " fallo al modificar.",""));
                FacesContext.getCurrentInstance().addMessage(this.passtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Password NO fue cambiado.",""));
                
                this.password = "";
                this.confirm_password = "";
            }
            
            
        }else{
            //Passwords do not match when wanting to update, notify user
            FacesContext.getCurrentInstance().addMessage(this.passtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Passwords NO son iguales.",""));
        }
        
        
    }

    public UIComponent getPasstxt() {
        return passtxt;
    }

    public void setPasstxt(UIComponent passtxt) {
        this.passtxt = passtxt;
    }

    
    
    
    public List<String> getUserList() {
        return userList;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        confirm_password = confirm_password.trim();
        this.confirm_password = confirm_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password.trim();
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
}
