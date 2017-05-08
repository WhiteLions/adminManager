/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class CreateSalidaView {
    
    @EJB
    private CreateSalidaEJB service;
    
    private List<CreateSalidaTipoOrdenBean> tipoOrdenList;
    private List<CreateSalidaClienteBean> clienteList;
    private List<CreateSalidaProductoBean> productosList;
    private List<CreateSalidaUnidadBean> unidadesList;
    
    private CreateSalidaRegistroBean selectedSalida;
    private CreateSalidaProductoBean selectedProducto;
    private CreateSalidaUnidadBean selectedUnidad;
    private int selectedCantidad;
    
    
    @PostConstruct
    public void initView(){
        
        this.clienteList = this.service.loadClientes();
        this.tipoOrdenList = this.service.loadTiposOrden();
        this.selectedSalida = new CreateSalidaRegistroBean();
        this.productosList = this.service.loadProductos();
        this.selectedCantidad = 0;
        
    }
    
    public void loadUnidades(){
        
        
        if ( this.selectedProducto != null && this.selectedProducto.getID() != -1){
            
            this.unidadesList = this.service.loadUnidades(this.selectedProducto.getID_UNIDAD());
         
        }
    }
    
    public void myAction(){
        
        System.out.println(this.selectedCantidad);
        System.out.println(this.selectedProducto.getNOMBRE());
        System.out.println(this.selectedUnidad.getNOMBRE());        
        
    }
    
    public void addProductToList(){
        
        if ( this.selectedProducto != null && this.selectedUnidad != null && this.selectedCantidad > 0 && this.selectedProducto.getID() > 0 && this.selectedUnidad.getID() > 0 ){
        
            CreateSalidaRegistroDetalleBean detalleBean = new CreateSalidaRegistroDetalleBean(this.selectedProducto,this.selectedUnidad,this.selectedCantidad);
            
            this.selectedSalida.getProductosList().add(detalleBean);
            
            //this.resetProducto();
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo agregar el producto a detalles."));     
        }
        
    }
    
    public void removeProductFromList(CreateSalidaRegistroDetalleBean product){
        
        this.selectedSalida.getProductosList().remove(product);
        
    }

    public List<CreateSalidaTipoOrdenBean> getTipoOrdenList() {
        return tipoOrdenList;
    }

    public List<CreateSalidaClienteBean> getClienteList() {
        return clienteList;
    }

    public CreateSalidaRegistroBean getSelectedSalida() {
        return selectedSalida;
    }

    public List<CreateSalidaProductoBean> getProductosList() {
        return productosList;
    }

    public List<CreateSalidaUnidadBean> getUnidadesList() {
        return unidadesList;
    }

    public CreateSalidaProductoBean getSelectedProducto() {
        return selectedProducto;
    }

    public int getSelectedCantidad() {
        return selectedCantidad;
    }

    public void setSelectedCantidad(int selectedCantidad) {
        if ( selectedCantidad >= 0.0d ){
            this.selectedCantidad = selectedCantidad;
        }
    }

    public CreateSalidaUnidadBean getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(CreateSalidaUnidadBean selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }    
    
    public void setSelectedProducto(CreateSalidaProductoBean selectedProducto) {
        if (selectedProducto.getID() == -1){
            
            this.selectedProducto = null;
            this.selectedCantidad = 0;
            this.selectedUnidad = null;
            this.unidadesList = null;
            
        }else{
            
        this.selectedProducto = selectedProducto;
        
        }
    }
    
    private void resetProducto(){
        
        this.selectedProducto = null;
        this.selectedCantidad = 0;
        this.selectedSalida = null;
        
    }
    
    
    
    
    
}
