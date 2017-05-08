/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.navigation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dideleo
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable{
    
    private String activeContent = "default";
   
    public String getActiveContent() {
        return activeContent;
    }
    
    public String setActiveContent(String activeContent) {
        this.activeContent = activeContent;
        //Destroy view scoped beans on menu change
        return "home.jsf";
    }
        
}
