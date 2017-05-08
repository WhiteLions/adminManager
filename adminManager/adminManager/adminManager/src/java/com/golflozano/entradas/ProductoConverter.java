/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import com.golflozano.products.ProductoBean;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ProductoConverter implements Converter{

    
    @ManagedProperty("#{nuevaEntradaView}")
    private NuevaEntradaView entradaView;

    public NuevaEntradaView getEntradaView() {
        return entradaView;
    }

    public void setEntradaView(NuevaEntradaView entradaView) {
        this.entradaView = entradaView;
    }
    
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        Iterator <ProductoBean> it = this.entradaView.getProductList().iterator();
        
        while( it.hasNext() ){
            
            ProductoBean product = it.next();
            
            if ( product.getId_producto() == Integer.parseInt(string) ){
                return product;
            }
            
        }
        
        
            //Should never be reached
            return null;
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        ProductoBean product = (ProductoBean)o;
        return new Integer(product.getId_producto()).toString();
    }
    
}
