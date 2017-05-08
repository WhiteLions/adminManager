/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.inventario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class ConsultaInventarioView {
    
    
    private List<InventarioProductoBean> listaProductos;
    private List<InventarioProductoBean> listaProductosFiltrado;
    
    @EJB
    private ConsultaInventarioEJB invSvc;
    
    
    @PostConstruct
    public void initView(){
        this.listaProductos = this.invSvc.loadProductos();
    }
   
    public List<InventarioProductoBean> getListaProductos() {
        return listaProductos;
    }

    public List<InventarioProductoBean> getListaProductosFiltrado() {
        return listaProductosFiltrado;
    }

    public void setListaProductosFiltrado(List<InventarioProductoBean> listaProductosFiltrado) {
        this.listaProductosFiltrado = listaProductosFiltrado;
    }
    
    public boolean filterByProductName(Object value, Object filter, Locale locale){
          
        String filterText = (filter == null) ? null : filter.toString().trim();
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        String  temp_value = value.toString().toLowerCase(locale);
        
        return temp_value.contains(filterText);
        
    }
   
    
}
