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
public class DivisionesView {
    
    private List<Division> divisionList;
    private List<String> activeList;
    
    
    @EJB
    private DivisionesEJB divisionesService;
   
    
    public DivisionesView(){
    super();
    }
    
    @PostConstruct
    public void initView(){
                
        this.divisionList = this.divisionesService.loadDivisiones();
        
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
        
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public DivisionesEJB getDivisionesService() {
        return divisionesService;
    }

    public void setdivisionesService(DivisionesEJB divisionesService) {
        this.divisionesService = divisionesService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        Division division = (Division) event.getObject();
        
        if ( division.getId().equals(new Integer(-1))) {
            //Creacion nuevo division
               
        if ( this.divisionesService.crearDivision(division) ){
            //Division creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Division creada : " + division.getNombre(),"La division fue creada con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear division : " + division.getNombre(),"No se pudo crear la division."));
        }
        
        }else{
            //Update de division
            
            if ( this.divisionesService.updateDivision(division) ){
            //Division creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Divisioneditado : " + division.getNombre(),"La divisionfue editada con exito."));
            
        }else{
            // Fallo al crear division
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion division : " + division.getNombre(),"No se pudo editar la division."));
        }
            
        }
        
    }
    
}
