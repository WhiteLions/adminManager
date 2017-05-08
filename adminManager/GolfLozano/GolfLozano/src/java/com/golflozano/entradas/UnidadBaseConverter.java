/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import com.golflozano.catalogs.UnidadBase;
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
public class UnidadBaseConverter implements Converter{

    
    @ManagedProperty("#{nuevaEntradaView}")
    private NuevaEntradaView view;

    public NuevaEntradaView getView() {
        return view;
    }

    public void setView(NuevaEntradaView view) {
        this.view = view;
    }
    
    
    
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        Iterator<UnidadBase> it = this.view.getUnidadesEntradaList().iterator();
        
        while ( it.hasNext() ){
            
            UnidadBase unidad = it.next();
            
            if ( unidad.getId() == Integer.parseInt(string) ){
                return unidad;
            }
            
        }
        
        //Should never be reached
        return null;
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        UnidadBase unidad = (UnidadBase)o;
        
        return new Integer(unidad.getId()).toString();
    }
    
}
