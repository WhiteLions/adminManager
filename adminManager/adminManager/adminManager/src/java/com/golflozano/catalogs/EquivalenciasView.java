/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.catalogs;

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
public class EquivalenciasView {
   
    private List<UnidadBase> unidadesListFrom;
    private List<UnidadBase> unidadesListTo;
    private int selectedFrom;
    private int selectedTo;
    private Double qty;
    
    @EJB
    private EquivalenciasEJB equivalenciasService;
    

    @PostConstruct
    public void InitView(){
        
        this.unidadesListFrom = this.equivalenciasService.loadUnidadesList();
        this.unidadesListTo = this.equivalenciasService.loadUnidadesList();
        this.qty = new Double(1.0);
    }
    
    public List<UnidadBase> getUnidadesListTo() {
        return unidadesListTo;
    }
    
    
    private void resetView(){
        
        this.selectedFrom = 0;
        this.selectedTo = 0;
        this.qty = 1.0;
        
    }
    
    public Double getQty() {
        return qty;
    }

    public int getSelectedFrom() {
        return selectedFrom;
    }

    public int getSelectedTo() {
        return selectedTo;
    }

    public List<UnidadBase> getunidadesListFrom() {
        return unidadesListFrom;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public void setSelectedFrom(int selectedFrom) {
        this.selectedFrom = selectedFrom;
    }

    public void setSelectedTo(int selectedTo) {
        this.selectedTo = selectedTo;
    }
    
    public void createRelation(){
        
        if (this.equivalenciasService.crearEquivalencia(selectedFrom, selectedTo, qty)){
            
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Equivalencia creada satisfactoriamente.", "La equivalencia ha sido agregada."));
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear equivalencia.", "La equivalencia NO ha sido agregada."));
            
        }
        
    }
    
    
}
