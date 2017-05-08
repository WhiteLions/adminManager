/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@RequestScoped
public class ContactProviderView {
    
    
    
    private ContactProviderBean contact;
    private List<AutoProviderBean> providerList;
    private int selectedProvider;
    
    @EJB
    private ContactProviderEJB contactProviderService;
    
    @PostConstruct
    public void initView(){
        this.providerList = this.contactProviderService.loadProviders();
        this.contact = new ContactProviderBean();
    }
    
    private void resetView(){
        this.providerList = this.contactProviderService.loadProviders();
        this.contact = new ContactProviderBean();
    }    
    
    private void loadProviders(){
        this.providerList = this.contactProviderService.loadProviders();
    }

    public List<AutoProviderBean> getProviderList() {
        return providerList;
    }

    public int getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(int selectedProvider) {
        this.selectedProvider = selectedProvider;
    }
    
    
    
    public ContactProviderBean getContact(){
        return this.contact;
    }
    
    public void setContactProviderService(ContactProviderEJB service){
        this.contactProviderService = service;
    } 
    
    public void createContactProvider(){
      
        if ( this.contactProviderService.createContactProvider(this.contact,this.selectedProvider) ){
            
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Contacto creado con éxito.","El contacto fue ligado al proveedor."));
            
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Creación de contacto falló.","Error al ligar contacto al proveedor."));
        }
        
        
    }
    
    
}
