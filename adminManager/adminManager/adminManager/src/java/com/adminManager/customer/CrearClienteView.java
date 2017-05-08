/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.customer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class CrearClienteView implements Serializable{
    
    private CustomerBean customer = new CustomerBean();
    
    @EJB
    private CreateCustomerEJB customerService;

    public CustomerBean getCustomer() {
        return customer;
    }
    
    public void setCustomerService(CreateCustomerEJB customerService){
        this.customerService = customerService;
    }
    
    public void createCustomer(){
        if ( this.customerService.createCustomer(this.customer) ){
            
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente creado con éxito.","El cliente fue creado satisfactoriamente."));
            
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Creación de cliente falló.","Error al crear cliente."));
        }
    }
    
    @PostConstruct
    public void initView(){
        
        this.customer = new CustomerBean();
    }
    
    private void resetView(){
        this.customer = new CustomerBean();
    }
       
    
}
