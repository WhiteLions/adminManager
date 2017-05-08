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
public class CreateSalidaUnidadBeanConverter implements Converter {
    
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
     
        Iterator<CreateSalidaUnidadBean> it = this.createSalidaView.getUnidadesList().iterator();
        
        while ( it.hasNext() ){
            
            CreateSalidaUnidadBean unidad = (CreateSalidaUnidadBean) it.next();
            
            if ( unidad.getID() == Integer.parseInt(string) ){            
                return unidad;
            }
            
        }
        
        //Should never be reached
        return null;
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    
        CreateSalidaUnidadBean unidad= (CreateSalidaUnidadBean)o;
        
        return new Integer(unidad.getID()).toString();
        
    }
    
    
    
}
