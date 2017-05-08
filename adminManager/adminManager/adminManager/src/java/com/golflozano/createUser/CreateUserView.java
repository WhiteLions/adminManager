/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.createUser;

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
public class CreateUserView {
    
    private String user;
    private String pass;
    private String confirmPass;
    private RoleMenu roleMenu; 
    private UIComponent usertxt;
    private UIComponent passtxt;
    private UIComponent passconfirmtxt;

    @EJB
    private CreateUserEJB userService;
    
    @PostConstruct
    public void InitView(){
        this.roleMenu = new RoleMenu();
        this.roleMenu.loadRoles();
    }

    public UIComponent getPassconfirmtxt() {
        return passconfirmtxt;
    }

    public UIComponent getPasstxt() {
        return passtxt;
    }

    public UIComponent getUsertxt() {
        return usertxt;
    }
    
    

    public void setPassconfirmtxt(UIComponent passconfirmtxt) {
        this.passconfirmtxt = passconfirmtxt;
    }

    public void setPasstxt(UIComponent passtxt) {
        this.passtxt = passtxt;
    }

    public void setUsertxt(UIComponent usertxt) {
        this.usertxt = usertxt;
    }
    
    
    public RoleMenu getRoleMenu(){
        return this.roleMenu;
    }
    
    
    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        confirmPass = confirmPass.trim();
        this.confirmPass = confirmPass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        pass = pass.trim();
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        user = user.trim();
        user = user.toUpperCase();
        this.user = user;
    }
    
    public void checkUser(){
        if(this.user != null){
            
        if (this.userService.UsernameExists(this.user)){
            FacesContext.getCurrentInstance().addMessage(this.usertxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario en uso.",""));
        }else{
            FacesContext.getCurrentInstance().addMessage(this.usertxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Nombre de usuario disponible.",""));
        }
        
        }
    }
    
    public void matchPasswords(){
        if (this.pass.equals(this.confirmPass)){
            FacesContext.getCurrentInstance().addMessage(this.passconfirmtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Password correcto.",""));
        }else{
            FacesContext.getCurrentInstance().addMessage(this.passconfirmtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Passwords NO son iguales.",""));
        }
    }
    
    public void createUser(){
        if (!this.userService.UsernameExists(this.user)){
            
            if(this.pass.equals(this.confirmPass)){
                
                Boolean result = this.userService.CreateUser(user, pass, this.roleMenu.getSelectedRole());
                
                if (result){
                    
                    this.user = "";
                    this.pass = "";
                    this.confirmPass = "";
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario creado con exito.","El usuario fue creado con exito."));
                    FacesContext.getCurrentInstance().addMessage(this.usertxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario creado con exito.",""));
                    
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR al crear usuario.","Error al crear usuario."));
                }
                
                
            }else{
                //Passwords do not match send error
                FacesContext.getCurrentInstance().addMessage(this.passconfirmtxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Passwords NO son iguales.",""));
            }
        }else{
            //Username exists send back error
            FacesContext.getCurrentInstance().addMessage(this.usertxt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario en uso.",""));
        }
    }
}
