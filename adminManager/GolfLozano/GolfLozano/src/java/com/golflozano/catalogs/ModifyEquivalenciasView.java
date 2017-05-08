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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ModifyEquivalenciasView {
    
    private List<EquivalenciaBean> equivalenciaList;

    
    @EJB
    private EquivalenciasEJB equivalenciaService;
    
    
    
    @PostConstruct
    public void initView(){
        this.equivalenciaList = this.equivalenciaService.loadEquivalencias();       
    }
    
    public List<EquivalenciaBean> getEquivalenciaList() {
        return equivalenciaList;
    }
    
    public void onRowEdit(RowEditEvent event){
        EquivalenciaBean eq = (EquivalenciaBean) event.getObject();
        
        if ( this.equivalenciaService.updateEquivalencia(eq)){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Equivalencia editada satisfactoriamente.","La equivalencia ha sido modificada."));
            
        }else{
         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al modificar equivalencia.","La equivalencia NO ha sido modificada."));
            
        }
        
    }
    
    
    
}
