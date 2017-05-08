/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.modifyUser;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author dideleo
 */

@ManagedBean
@RequestScoped
public class ModifyUsersView {
    
    List<UserBean> userList ;
    List<String> roleList ;
    List<String> activeList ;
    
    @EJB
    ModifyUserEJB modifyUserService;
    
    @PostConstruct
    public void InitView(){
        
        this.userList = this.modifyUserService.LoadUsers();
        this.roleList = this.modifyUserService.LoadRoles();
        this.activeList = this.modifyUserService.loadActive();
        
    }
    
    public List<String> getActiveList(){
        return this.activeList;
    }
    
    public List<UserBean> getUserList(){
        return this.userList;
    }
    
    public List<String> getRoletList(){
        return this.roleList;
    }
    
    public void setModifyUserService(ModifyUserEJB modifyUserService){
        
        this.modifyUserService = modifyUserService;
        
    }
    
    public void onRowEdit(RowEditEvent event){
        
        UserBean editedUser = (UserBean)event.getObject();
        
        Boolean result = this.modifyUserService.updateUser(editedUser);
        
        if (result){
            //User updated successfully
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario: " + editedUser.getUser(),"Actualizado con exito."));
        }else{
            //Failed to update user
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario: " + editedUser.getUser(),"Fallo al actualizar Usuario, vuelva a intentar."));
        }
        
    }
    
}
