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
public class CrearContactCustomerView {
    
    private ContactCustomerBean contact;
    
    private List<AutoCustomerBean> customerList;
    
    private int selectedCustomer;
    
    @EJB
    private ContactCustomerEJB contactCustomerService;
    
    @PostConstruct
    public void initView(){
        this.customerList = this.loadCustomers();
        this.contact = new ContactCustomerBean();
    }
    
    private void resetView(){
        this.customerList = this.loadCustomers();
        this.contact = new ContactCustomerBean();
    }
    
    public void setContactCustomerService(ContactCustomerEJB service){
        
        this.contactCustomerService = service;
    }

    public List<AutoCustomerBean> getCustomerList(){
        return this.customerList;
    }
    
    public int getSelectedCustomer(){
        return this.selectedCustomer;
    }
    
    public void setSelectedCustomer(int selectedCustomer){
        this.selectedCustomer = selectedCustomer;
    }
    
    public ContactCustomerBean getContact() {
        return contact;
    }
    
    public void createContactCustomer(){
      
        if ( this.contactCustomerService.createContactCustomer(this.contact,this.selectedCustomer) ){
            
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Contacto creado con éxito.","El contacto fue ligado al cliente."));
            
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Creación de contacto falló.","Error al ligar contacto a cliente."));
        }
        
        
    }
    
    private List<AutoCustomerBean> loadCustomers(){
        
        List<AutoCustomerBean> customers = this.contactCustomerService.loadCustomers(); 
        
        return customers;
    }
    
    
}
