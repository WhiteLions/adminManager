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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dideleo
 */
@ManagedBean
@RequestScoped
public class AlmacenView {
    
    private List<Almacen> almacenList;
    private List<String> activeList;
    
    
    @EJB
    private AlmacenEJB almacenService;
    
    
    @PostConstruct
    public void initView(){
                
        this.almacenList = this.almacenService.loadAlmacenes();
        
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
        
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<Almacen> getAlmacenList() {
        return almacenList;
    }

    public AlmacenEJB getAlmacenService() {
        return almacenService;
    }

    public void setAlmacenService(AlmacenEJB almacenService) {
        this.almacenService = almacenService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        Almacen almacen = (Almacen) event.getObject();
        
        if ( almacen.getId().equals(new Integer(-1))) {
            //Creacion nuevo almacen
               
        if ( this.almacenService.crearAlmacen(almacen) ){
            //Almacen creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Almacen creado : " + almacen.getNombre(),"El almacen fue creado con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear almacen : " + almacen.getNombre(),"No se pudo crear el almacen."));
        }
        
        }else{
            //Update de almacen
            
            if ( this.almacenService.updateAlmacen(almacen) ){
            //Almacen creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Almacen editado : " + almacen.getNombre(),"El almacen fue editado con exito."));
            
        }else{
            // Fallo al crear almacen
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion almacen : " + almacen.getNombre(),"No se pudo editar el almacen."));
        }
            
        }
        
    }
}
