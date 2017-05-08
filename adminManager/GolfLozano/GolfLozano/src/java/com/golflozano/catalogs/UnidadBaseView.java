/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

import java.util.ArrayList;
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
public class UnidadBaseView {
    
    private List<UnidadBase> UnidadBaseList;
    private List<String> activeList;
    
    
    @EJB
    private UnidadBaseEJB UnidadBaseService;
   
    
    @PostConstruct
    public void initView(){
                
        this.UnidadBaseList = this.UnidadBaseService.loadUnidadBase();
        
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
        
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<UnidadBase> getUnidadBaseList() {
        return UnidadBaseList;
    }

    public UnidadBaseEJB getUnidadBaseService() {
        return UnidadBaseService;
    }

    public void setUnidadBaseService(UnidadBaseEJB UnidadBaseService) {
        this.UnidadBaseService = UnidadBaseService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        UnidadBase UnidadBase = (UnidadBase) event.getObject();
        
        if ( UnidadBase.getId().equals(new Integer(-1))) {
            //Creacion nuevo UnidadBase
               
        if ( this.UnidadBaseService.crearUnidadBase(UnidadBase) ){
            //UnidadBase creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"UnidadBase creada : " + UnidadBase.getNombre(),"La UnidadBase fue creada con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear UnidadBase : " + UnidadBase.getNombre(),"No se pudo crear la UnidadBase."));
        }
        
        }else{
            //Update de UnidadBase
            
            if ( this.UnidadBaseService.updateUnidadBase(UnidadBase) ){
            //UnidadBase creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"UnidadBase editado : " + UnidadBase.getNombre(),"La UnidadBase fue editada con exito."));
            
        }else{
            // Fallo al crear UnidadBase
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion UnidadBase : " + UnidadBase.getNombre(),"No se pudo editar la UnidadBase."));
        }
            
        }
        
    }
    
}
