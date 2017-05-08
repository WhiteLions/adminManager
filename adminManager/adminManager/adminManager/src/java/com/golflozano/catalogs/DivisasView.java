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
public class DivisasView {
    
    private List<Divisa> divisaList;
    private List<String> activeList;
    
    
    @EJB
    private DivisasEJB divisasService;
    
    @PostConstruct
    public void initView(){
                
        this.divisaList = this.divisasService.loadDivisas();    
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
 
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<Divisa> getDivisaList() {
        return divisaList;
    }

    public DivisasEJB getDivisasService() {
        return divisasService;
    }

    public void setdivisasService(DivisasEJB divisasService) {
        this.divisasService = divisasService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        Divisa divisa = (Divisa) event.getObject();
        
        if ( divisa.getId().equals(new Integer(-1))) {
            //Creacion nuevo divisa
               
        if ( this.divisasService.crearDivisa(divisa) ){
            //Divisa creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Divisa creada : " + divisa.getNombre(),"La divisa fue creada con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear divisa : " + divisa.getNombre(),"No se pudo crear la divisa."));
        }
        
        }else{
            //Update de divisa
            
            if ( this.divisasService.updateDivisa(divisa) ){
            //Divisa creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Divisa editado : " + divisa.getNombre(),"La divisa fue editada con exito."));
            
        }else{
            // Fallo al crear divisa
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion divisa : " + divisa.getNombre(),"No se pudo editar la divisa."));
        }
            
        }
        
    }
    
}
