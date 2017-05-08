/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.customer;

import com.golflozano.address.AddressBean;
import com.golflozano.address.CustomerAddressEJB;
import com.golflozano.contact.ContactCustomerBean;
import com.golflozano.contact.ContactCustomerEJB;
import com.golflozano.paiscombo.PaisComboSel;
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
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ModifyClienteView {
    
    private List<CustomerBean> customerList;
    private List<CustomerBean> filteredList;
    private CustomerBean selectedCustomer;
    private List<ContactCustomerBean> contactList;
    private List<AddressBean> addressList;
 
    
    private PaisComboSel paisCombo;
    
    @EJB
    private CustomerAddressEJB addressService;
    
    @EJB
    private CreateCustomerEJB customerService;
    
    @EJB
    private ContactCustomerEJB contactService;
    
    @PostConstruct
    public void initView(){
        this.customerList = this.customerService.loadCustomers();
        this.paisCombo = new PaisComboSel(this.addressService);
    }
   

    public void setAddressService(CustomerAddressEJB addressService) {
        this.addressService = addressService;
    }
   
    public PaisComboSel getPaisCombo() {
        return paisCombo;
    }
 
    public List<AddressBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressBean> addressList) {
        this.addressList = addressList;
    }
    

    public CustomerBean getSelectedCustomer() {
        return selectedCustomer;
    }

    public List<ContactCustomerBean> getContactList() {
        return contactList;
    }
    
    

    public void setSelectedCustomer(CustomerBean selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
   
    public List<CustomerBean> getCustomerList() {
        return customerList;
    }

    public List<CustomerBean> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<CustomerBean> filteredList) {
        this.filteredList = filteredList;
    }

    
    
    public void onRowEdit(RowEditEvent event){
        
        CustomerBean customer = (CustomerBean) event.getObject();
        
        if ( this.customerService.updateCustomer(customer) ){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente modificado con éxito.","El cliente fue modificado satisfactoriamente."));        
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Modificación de cliente falló.","Error al modificar cliente."));
        }
        
    }
    
    public void onRowSelect(SelectEvent event){
        CustomerBean selection = (CustomerBean)event.getObject();
        this.contactList = this.customerService.loadContacs(selection);
        this.addressList = this.customerService.loadAddresses(selection);
        
    }
    /*
    public void onRowUnselect(UnselectEvent event){
        CustomerBean selection = (CustomerBean)event.getObject();
    }
    */
    public void onContactRowEdit(RowEditEvent event){
        
        ContactCustomerBean contact= (ContactCustomerBean) event.getObject();
        
        if ( this.contactService.updateContactCustomer(contact)){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Contacto modificado con éxito.","El contacto fue modificado satisfactoriamente."));        
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Modificación de contacto falló.","Error al modificar contacto."));
        }      
        
    }
    
    
    public void onDirRowEdit(RowEditEvent event){
        
        AddressBean address = (AddressBean) event.getObject();
          
      
        if ( this.addressService.updateDireccionCliente(address, this.paisCombo.getSelectedPais(),this.paisCombo.getSelectedEstado(),this.paisCombo.getSelectedCiudad())){
            
            this.addressList = this.customerService.loadAddresses(this.selectedCustomer);
            
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
