/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.salidas;

import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class CreateSalidaProductoBeanConverter implements Converter {
    
    @ManagedProperty("#{createSalidaView}")
    private CreateSalidaView createSalidaView;

    public CreateSalidaView getCreateSalidaView() {
        return createSalidaView;
    }

    public void setCreateSalidaView(CreateSalidaView createSalidaView) {
        this.createSalidaView = createSalidaView;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
     
        Iterator<CreateSalidaProductoBean> it = this.createSalidaView.getProductosList().iterator();
        
        while ( it.hasNext() ){
            
            CreateSalidaProductoBean product = (CreateSalidaProductoBean) it.next();
            
            if ( product.getID() == Integer.parseInt(string) ){            
                return product;
            }
            
        }
        
        //Should never be reached
        return null;
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    
        CreateSalidaProductoBean product = (CreateSalidaProductoBean)o;
        
        return new Integer(product.getID()).toString();
        
    }
    
    
    
}
