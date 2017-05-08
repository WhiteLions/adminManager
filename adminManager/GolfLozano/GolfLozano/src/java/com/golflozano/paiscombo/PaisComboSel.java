/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.paiscombo;

import com.golflozano.address.*;
import java.util.List;

/**
 *
 * @author dideleo
 */
public final class PaisComboSel {
    
    private int selectedPais;
    private int selectedEstado;
    private int selectedCiudad;
    
    private List<PaisBean> paisList;
    private List<EstadoBean> estadoList;
    private List<CiudadBean> ciudadList;
    
    private CustomerAddressEJB addressService;
    private ProviderAddressEJB addressServiceProvider;
    
    
    public PaisComboSel(CustomerAddressEJB service){
       
        this.addressService = service;
        this.addressServiceProvider = null;
        this.paisList = this.addressService.loadPaisList();
       
    }
    
    public PaisComboSel(ProviderAddressEJB service){
        this.addressServiceProvider = service;
        this.addressService = null;
        this.paisList = this.addressServiceProvider.loadPaisList();
    }
    
    public void reset(){
        
        if (this.addressService != null){
            this.paisList = this.addressService.loadPaisList();
        }
        if ( this.addressServiceProvider != null ){
            this.paisList = this.addressServiceProvider.loadPaisList();
        }
        this.selectedPais = 0;
        this.selectedEstado = 0;
        this.selectedCiudad = 0;

        this.estadoList = null;
        this.ciudadList = null;
        
    }
    
    public void paisChangeListener(){
        
        
        if (this.selectedPais != 0 ){
            
            if( this.selectedEstado != 0){
                this.selectedCiudad = 0;
                this.ciudadList = null;
            }
            
            if (this.addressService != null){
                this.estadoList = this.addressService.loadEstadoList(this.selectedPais);
            }else{
                this.estadoList = this.addressServiceProvider.loadEstadoList(selectedPais);
            }
            
        }else{
            this.selectedEstado = 0;
            this.selectedCiudad = 0;
            this.estadoList = null;
            this.ciudadList = null;
        }
            
    }
    
    public void estadoChangeListener(){
        
        if (this.selectedEstado != 0 ){
            
            if (this.addressService != null){
            this.ciudadList = this.addressService.loadCiudadList(this.selectedEstado);
            }else{
                this.ciudadList = this.addressServiceProvider.loadCiudadList(selectedEstado);
            }
            
            
        }else{
            this.ciudadList = null;
        }
    }

    public int getSelectedCiudad() {
        return selectedCiudad;
    }

    public void setSelectedCiudad(int selectedCiudad) {
        this.selectedCiudad = selectedCiudad;
    }

    public int getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(int selectedEdo) {
        this.selectedEstado = selectedEdo;
    }

    public int getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(int selectedPais) {
        
        this.selectedPais = selectedPais;
    }

    public List<CiudadBean> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<CiudadBean> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public List<EstadoBean> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<EstadoBean> estadoList) {
        this.estadoList = estadoList;
    }

    public List<PaisBean> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<PaisBean> paisList) {
        this.paisList = paisList;
    }
    
    
    
    
}
