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
public class TipoOrdenView {
    
    
    private List<TipoOrden> TipoOrdenList;
    private List<String> activeList;
    
    
    @EJB
    private TipoOrdenEJB TipoOrdenService;
   
    
    @PostConstruct
    public void initView(){
                
        this.TipoOrdenList = this.TipoOrdenService.loadTipoOrdenes();
        
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
        
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<TipoOrden> getTipoOrdenList() {
        return TipoOrdenList;
    }

    public TipoOrdenEJB getTipoOrdenService() {
        return TipoOrdenService;
    }

    public void setTipoOrdenesService(TipoOrdenEJB TipoOrdenesService) {
        this.TipoOrdenService = TipoOrdenesService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        TipoOrden TipoOrden = (TipoOrden) event.getObject();
        
        if ( TipoOrden.getId().equals(new Integer(-1))) {
            //Creacion nuevo TipoOrden
               
        if ( this.TipoOrdenService.crearTipoOrden(TipoOrden) ){
            //TipoOrden creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"TipoOrden creada : " + TipoOrden.getNombre(),"La TipoOrden fue creada con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear TipoOrden : " + TipoOrden.getNombre(),"No se pudo crear la TipoOrden."));
        }
        
        }else{
            //Update de TipoOrden
            
            if ( this.TipoOrdenService.updateTipoOrden(TipoOrden) ){
            //TipoOrden creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"TipoOrden editado : " + TipoOrden.getNombre(),"La TipoOrden fue editada con exito."));
            
        }else{
            // Fallo al crear TipoOrden
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion TipoOrden : " + TipoOrden.getNombre(),"No se pudo editar la TipoOrden."));
        }
            
        }
        
    }
    
}
