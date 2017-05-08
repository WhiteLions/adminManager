/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.provider;

import com.golflozano.address.AddressBean;
import com.golflozano.address.ProviderAddressEJB;
import com.golflozano.contact.ContactProviderBean;
import com.golflozano.contact.ContactProviderEJB;
import com.golflozano.paiscombo.PaisComboSel;
import com.golflozano.products.ProductoBean;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ModifyProviderView {
    
     private List<ProviderBean> providerList;
     private List<ProviderBean> filteredList;
     private List<ContactProviderBean> contactList;
     private List<AddressBean> addressList;
     private List<ProductoBean> provProdList;
     
     
     private ProviderBean selectedProvider;
     
     private PaisComboSel paisCombo;
     
     
     
    @EJB
    private createProviderEJB providerService;   
    
    @EJB
    private ProviderAddressEJB providerAddressService;
    
    @EJB
    private ContactProviderEJB providerContactService;
    
    @PostConstruct
    public void initView(){
        this.providerList = this.providerService.loadProviders();
        this.paisCombo = new PaisComboSel(this.providerAddressService);
    }

    public PaisComboSel getPaisCombo() {
        return paisCombo;
    }

    public List<ProductoBean> getProvProdList() {
        return provProdList;
    }
       
    public List<ContactProviderBean> getContactList() {
        return contactList;
    }

    public List<AddressBean> getAddressList() {
        return addressList;
    }   
    
    public ProviderBean getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(ProviderBean selectedProvider) {
        this.selectedProvider = selectedProvider;
    }
    
    
    public List<ProviderBean> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<ProviderBean> filteredList) {
        this.filteredList = filteredList;
    }
    
    
    public List<ProviderBean> getProviderList() {
        return providerList;
    }

    
   public void onRowEdit(RowEditEvent event){
       
       ProviderBean provider = (ProviderBean)event.getObject();
       
       if ( this.providerService.updateProvider(provider) ){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Proveedor modificado con éxito.","El proveedor fue modificado satisfactoriamente."));        
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Modificación de proveedor falló.","Error al modificar proveedor."));
        }
       
    }
    
    public void onRowSelect(SelectEvent event){
        
        ProviderBean provider = (ProviderBean) event.getObject();
        this.contactList = this.providerService.loadContacs(provider);
        this.addressList = this.providerService.loadAddresses(provider); 
        this.provProdList = this.providerService.loadProvProds(provider);
        
    }
    
    /*
    public void onRowUnselect(UnselectEvent event){
        System.out.println("ROWUNSELECT CONSULTA");
    }
    */
    
    public void onContactRowEdit(RowEditEvent event){
        
        ContactProviderBean contact= (ContactProviderBean) event.getObject();
              
        if ( this.providerContactService.updateContactProvider(contact)){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Contacto modificado con éxito.","El contacto fue modificado satisfactoriamente."));        
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Modificación de contacto falló.","Error al modificar contacto."));
        }     
        
    }
    
    public void onDirRowEdit(RowEditEvent event){
        
        AddressBean address = (AddressBean) event.getObject();
          
      
        if ( this.providerAddressService.updateDireccionProvider(address, this.paisCombo.getSelectedPais(),this.paisCombo.getSelectedEstado(),this.paisCombo.getSelectedCiudad())){
            
            this.addressList = this.providerService.loadAddresses(this.selectedProvider);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dirección modificada con éxito.","La dirección fue modificada satisfactoriamente."));        
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Modificación de dirección falló.","Error al modificar dirección."));
        }    
              
    }
    
    public void onDirRowCancel(RowEditEvent event) {
        
        this.paisCombo.reset();
        
    }
    
    
    
    public boolean filterbyName(Object value, Object filter, Locale locale){
          
        String filterText = (filter == null) ? null : filter.toString().trim();
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        String  temp_value = value.toString().toLowerCase(locale);
        
        return temp_value.contains(filterText);
        
    }
    
    public boolean filterbyLastName(Object value, Object filter, Locale locale){
       
        String filterText = ( filter == null ) ? null : filter.toString().trim();
        String  temp_value = ( value == null ) ? null : value.toString().toLowerCase(locale);
        
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        if ( value == null || value.equals("")){
            return false;
        }  
        
        return temp_value.contains(filterText);
        
    }
    
}
