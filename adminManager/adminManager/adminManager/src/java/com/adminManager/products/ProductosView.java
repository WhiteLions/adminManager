/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.products;

import com.golflozano.catalogs.Almacen;
import com.golflozano.catalogs.Division;
import com.golflozano.catalogs.Familia;
import com.golflozano.catalogs.UnidadBase;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dideleo
 */

@ManagedBean
@ViewScoped
public class ProductosView {
    
    
//    private List<AutoProviderBean> providerList;
    private List<UnidadBase> unidadList;
    private List<Almacen> almacenList;
    private List<Division> divisionList;
    private List<Familia> familiaList;
    private ProductoBean product;
    private UploadedFile picture;
    private UIComponent foto_cpt;
    private UIComponent familia_cpt;
    private UIComponent division_cpt;
    private UIComponent almacen_cpt;
    private UIComponent unidad_cpt;
    private String statusFoto;
    
    @EJB
    private ProductosEJB productosService;
    
    
    @PostConstruct
    public void initView(){
        
        //this.providerList = this.productosService.loadProviders();
        this.unidadList = this.productosService.loadUnidadBase();
        this.almacenList = this.productosService.loadAlmacenes();
        this.divisionList = this.productosService.loadDivisiones();
        this.familiaList = this.productosService.loadFamilias();
        this.product = new ProductoBean();
        this.statusFoto = "No has agregado foto.";
        
    }

    public UIComponent getAlmacen_cpt() {
        return almacen_cpt;
    }

    public void setAlmacen_cpt(UIComponent almacen_cpt) {
        this.almacen_cpt = almacen_cpt;
    }

    public UIComponent getDivision_cpt() {
        return division_cpt;
    }

    public void setDivision_cpt(UIComponent division_cpt) {
        this.division_cpt = division_cpt;
    }

    public UIComponent getFamilia_cpt() {
        return familia_cpt;
    }

    public void setFamilia_cpt(UIComponent familia_cpt) {
        this.familia_cpt = familia_cpt;
    }

    public UIComponent getUnidad_cpt() {
        return unidad_cpt;
    }

    public void setUnidad_cpt(UIComponent unidad_cpt) {
        this.unidad_cpt = unidad_cpt;
    }
    
    

    public String getStatusFoto() {
        return statusFoto;
    }

    public void setStatusFoto(String statusFoto) {
        this.statusFoto = statusFoto;
    }
   
    
 //   public List<AutoProviderBean> getProviderList() {
 //       return providerList;
 //   }

    public ProductoBean getProduct() {
        return product;
    }

    public List<UnidadBase> getUnidadList(){     
        return unidadList;
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

    public UploadedFile getPicture() {
        return picture;
    }

    public void setPicture(UploadedFile picture) {
        this.picture = picture;
    }

    public UIComponent getFoto_cpt() {
        return foto_cpt;
    }

    public void setFoto_cpt(UIComponent foto_cpt) {
        this.foto_cpt = foto_cpt;
    }
    
    private void resetView(){
        
  //      this.providerList = this.productosService.loadProviders();
        this.unidadList = this.productosService.loadUnidadBase();
        this.almacenList = this.productosService.loadAlmacenes();
        this.divisionList = this.productosService.loadDivisiones();
        this.familiaList = this.productosService.loadFamilias();
        this.product = new ProductoBean();
        this.picture = null;
        this.statusFoto = "No has agregado foto.";
    }
    
    
    public void crearProducto(){
        
        
        
        if (this.product.getId_unidadAlmacenamiento() == 0){
            
            FacesContext.getCurrentInstance().addMessage(this.unidad_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selección inválida.",""));
            return;
            
        }else if(this.product.getId_almacen() == 0){
            
            FacesContext.getCurrentInstance().addMessage(this.almacen_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selección inválida.",""));
            return;
            
        }else if(this.product.getId_division() == 0){
            
             FacesContext.getCurrentInstance().addMessage(this.division_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selección inválida.",""));
             return;
             
        }else if(this.product.getId_familia() == 0){
            
            FacesContext.getCurrentInstance().addMessage(this.familia_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selección inválida.",""));
            return;
            
        }
                           
        Integer id = this.productosService.createProduct(product);
        
        if ( id != 0 ){
            
            
            if ( this.picture != null ){
                
            
            if ( this.productosService.savePictureToDisk(this.picture, id) ){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto creado con éxito.","El producto nuevo fue creado satisfactoriamente con foto."));
                this.resetView();
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al ligar foto producto.","Error al ligar foto producto nuevo, contacta al administrador."));
                
            }    
            
            }else{
                //NO HAY FOTO QUE AGREGAR
                
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto creado con éxito.","El producto nuevo fue creado satisfactoriamente sin foto."));
                this.resetView();
            }
            
            
            
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear producto.","Error al crear producto nuevo, contacta al administrador."));
            
        }
        
        
        
    }
       
    public void fileUploadLsnr(FileUploadEvent event){
        
        UploadedFile file = event.getFile();          
        
        this.picture = file;
                      
        if ( this.picture != null ){   
            
            String[] temp = this.picture.getFileName().split("\\.");
            this.product.setFoto(temp[temp.length - 1]);
            
            this.statusFoto = "Foto agregada: " + this.picture.getFileName();
            FacesContext.getCurrentInstance().addMessage(this.foto_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO,"Foto " + this.picture.getFileName() + " recibida correctamente.",""));           
            
        }else{
            // Picture is Null something went wrong
            FacesContext.getCurrentInstance().addMessage(this.foto_cpt.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al recibir foto : " + this.picture.getFileName() + ", vuelva a intentar.",""));
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
}
