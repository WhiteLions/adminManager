/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

import com.golflozano.contact.AutoProviderBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ProviderAddressView implements Serializable{
    
    private AddressBean address;
    private List<AutoProviderBean> providerList;
    private List<PaisBean> paisList;
    private List<EstadoBean> estadoList;
    private List<CiudadBean> ciudadList;
    private int selectedProvider;
    private int selectedPais;
    private int selectedEstado;
    private int selectedCiudad;
    
    private UIComponent paisBox;
    private UIComponent estadoBox;
    private UIComponent ciudadBox;
    
    @EJB
    private ProviderAddressEJB providerService;
    
    @PostConstruct
    public void initView(){
        this.address = new AddressBean();
        this.providerList = this.providerService.loadCustomers();
        this.paisList = this.providerService.loadPaisList();
    }
    
    private void resetView(){
        
        this.providerList = this.providerService.loadCustomers();
        this.paisList = this.providerService.loadPaisList();
        this.selectedPais = 0;
        this.selectedEstado = 0;
        this.selectedCiudad = 0;

        this.estadoList = null;
        this.ciudadList = null;
        
        this.address = new AddressBean();
        
    }

    public UIComponent getCiudadBox() {
        return ciudadBox;
    }

    public void setCiudadBox(UIComponent ciudadBox) {
        this.ciudadBox = ciudadBox;
    }

    public UIComponent getEstadoBox() {
        return estadoBox;
    }

    public void setEstadoBox(UIComponent estadoBox) {
        this.estadoBox = estadoBox;
    }

    public UIComponent getPaisBox() {
        return paisBox;
    }

    public void setPaisBox(UIComponent paisBox) {
        this.paisBox = paisBox;
    }
    
    

    public AddressBean getAddress() {
        return address;
    }

    public int getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(int selectedPais) {
        this.selectedPais = selectedPais;
    }

    public List<PaisBean> getPaisList() {
        return paisList;
    }
    

    public List<AutoProviderBean> getProviderList() {
        return providerList;
    }

    public int getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(int selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public List<CiudadBean> getCiudadList() {
        return ciudadList;
    }

    public List<EstadoBean> getEstadoList() {
        return estadoList;
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

    public void setSelectedEstado(int selectedEstado) {
        this.selectedEstado = selectedEstado;
    }
    
        
    public void createProviderAddress(){
        
         if ( this.selectedPais == 0){
            FacesContext.getCurrentInstance().addMessage(this.paisBox.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Pais es un campo obligatorio.",""));
            
            return;
        }
        
        if ( this.selectedEstado == 0){
            FacesContext.getCurrentInstance().addMessage(this.estadoBox.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Estado es un campo obligatorio.",""));
            
            return;
        }
        
        if ( this.selectedCiudad == 0){
            FacesContext.getCurrentInstance().addMessage(this.ciudadBox.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ciudad es un campo obligatorio.",""));
            
            return;
        }
        
        if (this.providerService.crearDireccionProvider(this.address, this.selectedPais, this.selectedEstado, this.selectedCiudad, this.selectedProvider)){
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dirección ligada a proveedor con éxito.","La dirección fue ligada al proveedor satisfactoriamente."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al ligar dirección a proveedor.","Error al crear dirección del proveedor."));
        }
        
    }
    
     public void paisChangeListener(){
        
        if (this.selectedPais != 0 ){
            
            if( this.selectedEstado != 0){
                this.selectedCiudad = 0;
                this.ciudadList = null;
            }
            
            this.estadoList = this.providerService.loadEstadoList(this.selectedPais);
            
        }else{
            this.selectedEstado = 0;
            this.selectedCiudad = 0;
            this.estadoList = null;
            this.ciudadList = null;
        }
            
    }
    
      public void estadoChangeListener(){
        
        if (this.selectedEstado != 0 ){
            this.ciudadList = this.providerService.loadCiudadList(this.selectedEstado);
        }else{
            this.ciudadList = null;
        }
    }
     
}
