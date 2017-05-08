/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class BriefSalidaView {
    
    @EJB
    private BriefSalidaEJB service;
    
    private List<BriefSalidaBean> salidasList;
    private List<BriefSalidaBean> salidasListFiltered;
    
    private BriefSalidaBean selectedSalida;
    
    
    @PostConstruct
    public void initView(){
    
        this.salidasList = this.service.loadSalidas();
        
    }

    public List<BriefSalidaBean> getSalidasList() {
        
        return salidasList;
        
    }

    public List<BriefSalidaBean> getSalidasListFiltered() {
        return salidasListFiltered;
    }

    public void setSalidasListFiltered(List<BriefSalidaBean> salidasListFiltered) {
        this.salidasListFiltered = salidasListFiltered;
    }

    public BriefSalidaBean getSelectedSalida() {
        return selectedSalida;
    }

    public void setSelectedSalida(BriefSalidaBean selectedSalida) {
        this.selectedSalida = selectedSalida;
    }
    
    public void onRowSelect(SelectEvent event){
        
        if ( this.selectedSalida != null ){
            
            this.selectedSalida.setListaDetalle(this.service.loadSalidasDetalle(this.selectedSalida));
            
        }
        
    }
    
    
}
