/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import com.golflozano.catalogs.Divisa;
import com.golflozano.catalogs.UnidadBase;
import com.golflozano.contact.AutoProviderBean;
import com.golflozano.login.LoginBean;
import com.golflozano.products.ProductoBean;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class NuevaEntradaView {
    
    
    @EJB
    private NuevaEntradaEJB entradaSvc;
    
    private List<AutoProviderBean> providerList;
    private List<ProductoBean> productList;
    private List<UnidadBase> unidadesEntradaList;
    private List<Divisa> divisaList;
    
    private EntradaBean factura;
    private ProductoBean selectedProduct;
    private int selectedQty;
    private double preciouni;
    private UnidadBase selectedUnidadPojo;
    private String importacion;
    private UIComponent proveedor_combo;
    private Double total_importes;
    
    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    
    @PostConstruct
    public void initView(){
    
        this.factura = new EntradaBean();
        this.providerList = this.entradaSvc.loadProviders();
        this.productList = this.entradaSvc.loadProducts();
        this.divisaList = this.entradaSvc.loadDivisas();
        this.importacion = "NO";
        this.total_importes = new Double(0.0d);
        
    }
    
    private void resetFactura(){
        this.factura = new EntradaBean();
        this.providerList = this.entradaSvc.loadProviders();
        this.productList = this.entradaSvc.loadProducts();
        this.divisaList = this.entradaSvc.loadDivisas();
        this.importacion = "NO";
        this.total_importes = new Double(0.0d);
        this.selectedProduct = null;
        this.selectedQty = 0;
        this.selectedUnidadPojo = null;
        this.preciouni = 0.0d;
        this.total_importes = 0.0d;
    }
    
    
    private void resetProducto(){
        
        this.selectedProduct = null;
        this.selectedUnidadPojo = null;
        this.unidadesEntradaList = null;
        this.selectedQty = 0;
        this.preciouni = 0.0d;
        
    }
    
    public void loadUnidadesEntrada(){                    
        if ( this.selectedProduct != null ){          
                    this.unidadesEntradaList = this.entradaSvc.loadUnidadesEntrada(this.selectedProduct.getId_unidadAlmacenamiento());     
        }       
    }
    
    public void test(){
        System.out.println(this.factura.getProductListString());
    }
    
    public void crearFactura(){
        
        if ( this.isImport()  ){
            
            if ( this.factura.getTipocambio() == 0.0d ){
                //Es import y tipo cambio es 0.0
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.","Tipo de cambio no debe ser cero para una importación."));
                return;
            }
            
        }else{
            //no Es import OK to continue
        }
        
        
        if ( this.factura.getProvider() > 0 ){
            
            if ( this.checkTotal() ){
                
                if ( this.checkProductsTotal() ){
                    
                    //Todo est abien agrega la factura
                    
                    if ( this.entradaSvc.crearFactura(this.factura, this.loginBean.getUsername()) ){
                        //Factura creada con exito
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Factura ingresada al sistema.",""));
                        this.resetFactura();
                        
                    }else{
                        //Error al crear factura
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear factura.","Contacta a un administrador."));
                    }                  
                    
                }else{
                    //Total != total de importes en detalle de productos
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sub-total de factura no concuerda con importes en detalle de productos.",""));
                    
                }
                
                
            }else{
                //Total != subtotal + iva
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Total no concuerda con Sub-Total + I.V.A.",""));
                
            }
            
        }else{
            //Proveedor invalido
            
            FacesContext.getCurrentInstance().addMessage(this.proveedor_combo.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecciona un proveedor válido.",""));
            
        }
        
        
 
    }
    
    
    private Boolean isImport(){
            
            if ( this.factura.getPedimento() != null && !this.factura.getPedimento().equals("") ){
                
                return Boolean.TRUE;
                
            }
            
            return Boolean.FALSE;
            
    }
    
    
    private Boolean checkTotal(){
        
        double temp_total = this.factura.getSubtotal() + this.factura.getIva();
              
        return this.factura.getTotal() == temp_total; 
        
    }
    
    private Boolean checkProductsTotal(){
        
        return this.total_importes.equals(this.factura.getSubtotal());
        
    }
    
    
    public void agregarProducto(){
        
        if ( this.selectedProduct != null && this.selectedProduct.getId_producto() > 0 ){
            
            if ( this.selectedUnidadPojo != null && this.selectedUnidadPojo.getId() > 0 ){
                
                
                if ( this.selectedQty != 0 ){
                    //Todo en orden valido agregar producto a la lista de productos
                    
                    if ( this.factura.getProductosList().add(new EntradaEntry(this.selectedProduct,this.selectedUnidadPojo,this.selectedQty,this.preciouni)) ){
                        
                        this.total_importes += this.selectedQty * this.preciouni;
                        
                        this.resetProducto();
                        
                    }else{
                        //Fallo al agregar producto a la lista de productos en factura
                        
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al agregar producto.","El producto no se pudo agregar a la factura."));
                    }
                            
                    
                }else{
                    //Cantidad es 0 error
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cantidad inválida.","Cantidad debe ser mayor a cero."));
                }
                
                
            }else{
                
                //unidad invalida
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unidad inválida.","Selecciona una unidad válida."));
            }
            
        }else{
            
            //Producto invalido
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Producto inválido.","Selecciona un producto válido."));
            
        }
        
    }
    
    public void eliminarProducto(EntradaEntry toRemove){
        
        this.factura.getProductosList().remove(toRemove);
        this.total_importes -= toRemove.getCantidad() * toRemove.getPrecio_unitario();
        
    }

    public String getImportacion() {
        return importacion;
    }

    public void setImportacion(String importacion) {
        this.importacion = importacion;
    }

    public UIComponent getProveedor_combo() {
        return proveedor_combo;
    }

    public void setProveedor_combo(UIComponent proveedor_combo) {
        this.proveedor_combo = proveedor_combo;
    }

    public Double getTotal_importes() {
        return total_importes;
    }

    public void setTotal_importes(Double total_importes) {
        this.total_importes = total_importes;
    }
    
    
    
    

    public UnidadBase getSelectedUnidadPojo() {
        return selectedUnidadPojo;
    }

    public void setSelectedUnidadPojo(UnidadBase selectedUnidadPojo) {
        this.selectedUnidadPojo = selectedUnidadPojo;
    }
    
    public List<Divisa> getDivisaList() {
        return divisaList;
    }

    public double getPreciouni() {
        return preciouni;
    }

    public void setPreciouni(double preciouni) {
        this.preciouni = preciouni;
    }
    
    
    public List<UnidadBase> getUnidadesEntradaList() {
        return unidadesEntradaList;
    }
    
    

    public int getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(int selectedQty) {
        this.selectedQty = selectedQty;
    }
    
    public List<AutoProviderBean> getProviderList() {
        return providerList;
    }

    public ProductoBean getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoBean selectedProduct) {
             
        if (selectedProduct.getId_producto() <= 0){
            this.selectedProduct = null;
            this.unidadesEntradaList = null;
            this.selectedQty = 0;
        }else{
        
        this.selectedProduct = selectedProduct;
        
        }
    }

    
    
    public EntradaBean getFactura() {
        return factura;
    }

    public void setFactura(EntradaBean factura) {
        this.factura = factura;
    }

    public List<ProductoBean> getProductList() {
        return productList;
    }

    
    
    
    
}
