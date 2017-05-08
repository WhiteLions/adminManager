/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.contact;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dideleo
 */
@FacesConverter("autocustomerbeanconverter")
public class AutoCustomerBeanConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.out.println(string);
        System.out.println("Converter----------");
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }
    
}
