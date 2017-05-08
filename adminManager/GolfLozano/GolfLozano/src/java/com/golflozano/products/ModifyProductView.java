/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.products;

import com.golflozano.catalogs.Almacen;
import com.golflozano.catalogs.Division;
import com.golflozano.catalogs.Familia;
import com.golflozano.contact.AutoProviderBean;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ModifyProductView {
    
    
    private List<ProductoBean> productList;
    private List<ProductoBean> productListFiltered;
    //Unidad base no deberia cambiar nunca desabhilitando el comportamiento
    //private List<UnidadBase> unidadList;
    private List<Almacen> almacenList;
    private List<Familia> familiaList;
    private List<Division> divisionList;
    private List<AutoProviderBean> providerList;
    private ProductoBean selectedProduct;
    private List<AutoProviderBean> productProviderList;
    private int SelectedProvider;
    
    @EJB
    private ProductosEJB productoSvc;
    
    
    
    @PostConstruct
    public void initView(){ 
      
        this.productList = this.productoSvc.loadProducts();
        this.selectedProduct = new ProductoBean();
        this.selectedProduct.setNombre("No hay un producto seleccionado.");
    }
    
    public void resetView(){
        
        this.productList = this.productoSvc.loadProducts();
        //Leaving modified product on after modified.
        //this.selectedProduct = new ProductoBean();
        //this.selectedProduct.setNombre("No hay un producto seleccionado.");
        
    }
    
 //   public List<UnidadBase> getUnidadList() {
 //       return unidadList;
 //   }

    public List<AutoProviderBean> getProductProviderList() {
        return productProviderList;
    }

    
    public int getSelectedProvider() {
        return SelectedProvider;
    }

    public void setSelectedProvider(int SelectedProvider) {
        this.SelectedProvider = SelectedProvider;
    }

    
    
    public List<AutoProviderBean> getProviderList() {
        return providerList;
    }
    

    public List<Almacen> getAlmacenList() {
        return almacenList;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public List<Familia> getFamiliaList() {
        return familiaList;
    }
    
    


    public List<ProductoBean> getProductListFiltered() {
        return productListFiltered;
    }

    public void setProductListFiltered(List<ProductoBean> productListFiltered) {
        this.productListFiltered = productListFiltered;
    }
    
    
    public List<ProductoBean> getProductList() {
        return productList;
    }

    public ProductoBean getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoBean selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    public void onRowSelect(SelectEvent event){
                     
     //   this.unidadList = this.productoSvc.loadUnidadBase();
        this.almacenList = this.productoSvc.loadAlmacenes();
        this.divisionList = this.productoSvc.loadDivisiones();
        this.familiaList = this.productoSvc.loadFamilias();
        this.providerList = this.productoSvc.loadProviders(this.selectedProduct.getId_producto());
        this.productProviderList = this.productoSvc.loadProductProviders(this.selectedProduct);
        
    }
    
     public boolean filterbyName(Object value, Object filter, Locale locale){
          
        String filterText = (filter == null) ? null : filter.toString().trim();
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        String  temp_value = value.toString().toLowerCase(locale);
        
        return temp_value.contains(filterText);
        
    }
    
    public boolean filterbyProveedor(Object value, Object filter, Locale locale){
          
        String filterText = (filter == null) ? null : filter.toString().trim();
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        String  temp_value = value.toString().toLowerCase(locale);
        
        return temp_value.contains(filterText);
        
    }
    
    public void updateProduct(){
        
        if ( this.selectedProduct.getId_producto() != -1 ){
            
            if ( this.productoSvc.updateProduct(this.selectedProduct) ){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto editado.","El producto ha sido editado satisfactoriamente."));
                this.resetView();
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al editar producto.","Si el problema persiste contacta a un administrador."));
            }
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No hay un producto seleccionado.","Selecciona un producto en consulta para edición."));
            
        }
        
        
    }
    
    
    public void fileUploadLsnr(FileUploadEvent event){
        
        UploadedFile file = event.getFile();    
        
        if (file != null && file.getSize() > 0){
            
            String[] temp = file.getFileName().split("\\.");
            
            this.selectedProduct.setFoto(temp[temp.length - 1]);
            
            if (this.productoSvc.savePictureToDisk(file, this.selectedProduct.getId_producto()) ){
                
                this.updateProduct();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Foto guardada.","Foto modificada satisfactoriamente."));
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al modificar foto.","Error al guardar nueva foto, vuelve a intentar."));
                
            }
            
        }
        
        
    }
    
    public void ligarProvProd(){
        
        int prodId = this.selectedProduct.getId_producto();
        int provId = this.SelectedProvider;
        
        if ( prodId > 0 && provId > 0 ){
            
            if ( this.productoSvc.ligar_proveedor_producto(prodId, provId) ){
                
                
                
                this.providerList = this.productoSvc.loadProviders(this.selectedProduct.getId_producto());
                this.productProviderList = this.productoSvc.loadProductProviders(this.selectedProduct);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Proveedor ligado satisfactoriamente.",""));
                
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al ligar proveedor y producto.","Asegurate que la relación no es pre-existente."));
                
            }
            
        }
        
    }
    
    public void borrarLigaProdProv(String idprov){
        
        Integer provid = Integer.parseInt(idprov);
        
        if ( this.productoSvc.borrar_proveedor_producto(this.selectedProduct.getId_producto(), provid) ){
            
            this.providerList = this.productoSvc.loadProviders(this.selectedProduct.getId_producto());
            this.productProviderList = this.productoSvc.loadProductProviders(this.selectedProduct);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Proveedor removido.",""));
            
        }else{
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al remover proveedor.",""));
            
        }
        
    }
    
}
