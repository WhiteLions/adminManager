/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.provider;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class crearProviderView implements Serializable{
    
    private ProviderBean provider;
    
    @EJB
    private createProviderEJB providerService;
    
    @PostConstruct
    public void initView(){
        this.provider = new ProviderBean();
    }
    
    private void resetView(){
        this.provider = new ProviderBean();
    }
    
    public ProviderBean getProvider(){
        return this.provider;
    }
    
    public void setProviderService(createProviderEJB providerService){
        this.providerService = providerService;
    }
    
    public void createProvider(){
        if ( this.providerService.createProvider(this.provider) ){
            
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Proveedor creado con éxito.","El proveedor fue agregado satisfactoriamente."));
            
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Creación de proveedor falló.","Error al crear proveedor."));
        }
    }
}
