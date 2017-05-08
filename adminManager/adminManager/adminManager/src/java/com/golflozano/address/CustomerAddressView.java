/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.address;

import com.golflozano.contact.AutoCustomerBean;
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
public class CustomerAddressView implements Serializable{
    
    private List<AutoCustomerBean> customerList;
    private List<PaisBean> paisList;
    private List<EstadoBean> estadoList;
    private List<CiudadBean> ciudadList;
    private int selectedCustomer;
    private int selectedEstado;
    private int selectedPais;
    private int selectedCiudad;
    private AddressBean address;
    
    private UIComponent paisBox;
    private UIComponent estadoBox;
    private UIComponent ciudadBox;
    
    @EJB
    private CustomerAddressEJB addressService;
    
    @PostConstruct
    public void initView(){
        this.customerList = this.addressService.loadCustomers();
        this.paisList = this.addressService.loadPaisList();
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
    
    

    public List<CiudadBean> getCiudadList() {
        return ciudadList;
    }
 
    
    public int getSelectedCiudad() {
        return selectedCiudad;
    }

    public void setSelectedCiudad(int selectedCiudad) {
        this.selectedCiudad = selectedCiudad;
    }   
    
    public List<EstadoBean> getEstadoList() {
        return estadoList;
    }
    
    
    public int getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(int selectedEstado) {
        this.selectedEstado = selectedEstado;
    }
    
     public int getSelectedPais() {
        return selectedPais;
    }

     public List<PaisBean> getPaisList() {
        return paisList;
    }
     
    public void setSelectedPais(int selectedPais) {
        this.selectedPais = selectedPais;
    }
    
    private void resetView(){
        this.customerList = this.addressService.loadCustomers();
        this.paisList = this.addressService.loadPaisList();
        this.selectedPais = 0;
        this.selectedEstado = 0;
        this.selectedCiudad = 0;

        this.estadoList = null;
        this.ciudadList = null;
        
        this.address = new AddressBean();
        
    }

    public AddressBean getAddress() {
        return address;
    }

    public List<AutoCustomerBean> getCustomerList() {
        return customerList;
    }

    public int getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setAddressService(CustomerAddressEJB addressService) {
        this.addressService = addressService;
    }

    public void setSelectedCustomer(int selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
    
    public void createCustomerAddress(){
        
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
        
              
        if (this.addressService.crearDireccionCliente(this.address, this.selectedPais, this.selectedEstado, this.selectedCiudad, this.selectedCustomer)){
            this.resetView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dirección ligada a cliente con éxito.","La dirección fue ligada al cliente satisfactoriamente."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al ligar dirección a cliente.","Error al crear dirección del cliente."));
        }
        
    }
    
    public void paisChangeListener(){
        
        if (this.selectedPais != 0 ){
            
            if( this.selectedEstado != 0){
                this.selectedCiudad = 0;
                this.ciudadList = null;
            }
            
            this.estadoList = this.addressService.loadEstadoList(this.selectedPais);
            
        }else{
            this.selectedEstado = 0;
            this.selectedCiudad = 0;
            this.estadoList = null;
            this.ciudadList = null;
        }
            
    }
    
    public void estadoChangeListener(){
        
        if (this.selectedEstado != 0 ){
            this.ciudadList = this.addressService.loadCiudadList(this.selectedEstado);
        }else{
            this.ciudadList = null;
        }
    }
}
