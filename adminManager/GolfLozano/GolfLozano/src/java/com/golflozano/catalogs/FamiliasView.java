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
public class FamiliasView {
    
    private List<Familia> familiaList;
    private List<String> activeList;
    
    
    @EJB
    private FamiliasEJB familiasService;
   
    
    @PostConstruct
    public void initView(){
                
        this.familiaList = this.familiasService.loadFamilias();
        
        this.activeList = new ArrayList<String>();
        this.activeList.add("SI");
        this.activeList.add("NO");
        
        
    }

    public List<String> getActiveList() {
        return activeList;
    }

    public List<Familia> getFamiliaList() {
        return familiaList;
    }

    public FamiliasEJB getFamiliasService() {
        return familiasService;
    }

    public void setfamiliasService(FamiliasEJB familiasService) {
        this.familiasService = familiasService;
    }
    
    public void onRowEdit(RowEditEvent event){
        
        Familia familia = (Familia) event.getObject();
        
        if ( familia.getId().equals(new Integer(-1))) {
            //Creacion nuevo familia
               
        if ( this.familiasService.crearFamilia(familia) ){
            //Familia creado con exito
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Familia creada : " + familia.getNombre(),"La familia fue creada con exito."));
            
        }else{
            // Fallo al crear almacen
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo crear familia : " + familia.getNombre(),"No se pudo crear la familia."));
        }
        
        }else{
            //Update de familia
            
            if ( this.familiasService.updateFamilia(familia) ){
            //Familia creado con exito
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Familia editado : " + familia.getNombre(),"La familia fue editada con exito."));
            
        }else{
            // Fallo al crear familia
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fallo edicion familia : " + familia.getNombre(),"No se pudo editar la familia."));
        }
            
        }
        
    }
    
}
